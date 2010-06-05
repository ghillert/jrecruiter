/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.xml.transform.stream.StreamSource;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.dao.IndustryDao;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.dao.RegionDao;
import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;
import org.jrecruiter.model.export.Backup;
import org.jrecruiter.service.DemoService;
import org.jrecruiter.service.JobService;
import org.jrecruiter.service.UserService;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Service("demoService")
@Transactional
public class DemoServiceImpl implements DemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    private @Autowired JobService  jobService;
    private @Autowired UserService userService;
    private @Autowired RegionDao   regionDao;
    private @Autowired RoleDao     roleDao;
    private @Autowired UserDao     userDao;
    private @Autowired JobDao      jobDao;
    private @Autowired IndustryDao industryDao;
    private @Autowired Jaxb2Marshaller marshaller;
    private @PersistenceContext EntityManager em;
    private @Autowired DataSource dataSource;
    private @Autowired LocalContainerEntityManagerFactoryBean fb;

    /** {@inheritDoc} */
    @Override
    public void createDemoJobs(final User user, final Integer numberOfJobsToCreate) {
        final LoremIpsum loremIpsum = new LoremIpsum();

        Random random = new Random();

        final Industry demoIndustry = industryDao.get(1L);

        if (demoIndustry == null) {
            throw new IllegalStateException(String.format("No industry found for Id %s", 1L));
        }

        final Region demoRegion = regionDao.get(1L);

        if (demoRegion == null) {
            throw new IllegalStateException(String.format("No regions found for Id %s", 2L));
        }

        for (int i = 0; i <= numberOfJobsToCreate; i++) {

            final Job job = new Job();
            job.setBusinessAddress1(loremIpsum.getWords(2));
            job.setBusinessAddress2(loremIpsum.getWords(3));
            job.setBusinessCity(loremIpsum.getWords(2));
            job.setBusinessEmail(loremIpsum.getWords(1) + "@" + loremIpsum.getWords(1) + ".com");
            job.setBusinessName(loremIpsum.getWords(2));
            job.setBusinessPhone("111-111-1111");
            job.setBusinessPhoneExtension("111-111-1111");
            job.setBusinessState(loremIpsum.getWords(1));
            job.setBusinessZip(loremIpsum.getWords(1));
            job.setDescription(loremIpsum.getParagraphs(2));
            job.setIndustry(demoIndustry);
            job.setJobRestrictions(loremIpsum.getWords(30));
            job.setJobTitle(loremIpsum.getWords(2));
            job.setLatitude(BigDecimal.TEN);
            job.setLongitude(BigDecimal.TEN);
            job.setOfferedBy(OfferedBy.RECRUITER);

            if (i % 2 == 0) {
                job.setRegion(demoRegion);
            } else {
                job.setRegionOther("Some Other Region");
            }

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_YEAR, random.nextInt(200));

            job.setRegistrationDate(cal.getTime());
            job.setUpdateDate(cal.getTime());
            job.setSalary("100000.50");
            job.setStatus(JobStatus.ACTIVE);

            job.setUser(user);
            job.setUsesMap(Boolean.TRUE);
            job.setWebsite("http://www.google.com/");

            this.jobService.addJob(job);
        }
    }


    /** {@inheritDoc} */
    @Override
    public User createDemoUser() {
        User demoUser = getUser();

        User userFromDb = userService.getUser(demoUser.getEmail());

        if (userFromDb != null) {
            return userFromDb;
        } else {

            try {
                demoUser = userService.addUser(demoUser);
            } catch (DuplicateUserException e) {
                throw new IllegalStateException(e);
            }

        }

        return demoUser;
    }



    /* (non-Javadoc)
     * @see org.jrecruiter.service.DemoService#restore(java.io.InputStream)
     */
    @Override
    public void restore(final InputStream inputStream) {

        StreamSource source = new StreamSource(inputStream);
        Backup backup = (Backup) marshaller.unmarshal(source);

        LOGGER.info("Restoring: " + backup.getUsers().size() + " users, "
                                  + backup.getRoles().size()      + " roles, "
                                  + backup.getJobs().size()       + " jobs, "
                                  + backup.getIndustries().size() + " industries, and "
                                  + backup.getRegions().size()    + " regions.");

        this.restore(backup);

    }

    /** {@inheritDoc} */
    @Override
    public void loadAndRestoreSeedData() {
        final InputStream is = DemoServiceImpl.class.getResourceAsStream("/org/jrecruiter/server/seeddata/seeddata.xml");
        restore(is);
    }

    /** {@inheritDoc} */
    @Override
    public void restore(final Backup backup) {

        for (final Region region : backup.getRegions()) {
            regionDao.replicate(region);
        }

        for (final Industry industry : backup.getIndustries()) {
            industryDao.replicate(industry);
        }

        for (final Role role : backup.getRoles()) {
            roleDao.replicate(role);
        }


        final List<User> usersFromDb = userDao.getAll();

        Set<String> userNames = CollectionUtils.getHashSet();

        for (User user : usersFromDb) {
            userNames.add(user.getUsername());
        }



        for (final User userFromBackup : backup.getUsers()) {

            if (userFromBackup == null) {
                LOGGER.error("User is null.");
            } else if (userFromBackup.getUsername() == null) {
                LOGGER.error("Username is null for user '" + userFromBackup + "'");
            } else {

                if (!userNames.contains(userFromBackup.getUsername())) {
                    LOGGER.info("Saving user '" + userFromBackup.getUsername() + "'");

                    final User user = new User();
                    user.setCompany(userFromBackup.getCompany());
                    user.setEmail(userFromBackup.getEmail());
                    user.setEnabled(userFromBackup.isEnabled());
                    user.setFax(userFromBackup.getFax());
                    user.setFirstName(userFromBackup.getFirstName());
                    user.setLastLoginDate(userFromBackup.getLastLoginDate());
                    user.setLastName(userFromBackup.getLastName());
                    user.setPassword(userFromBackup.getPassword());
                    user.setPhone(userFromBackup.getPhone());
                    user.setRegistrationDate(userFromBackup.getRegistrationDate());
                    user.setUpdateDate(userFromBackup.getUpdateDate());
                    user.setUserAuthenticationType(userFromBackup.getUserAuthenticationType());
                    user.setUsername(userFromBackup.getUsername());
                    user.setUserToRoles(userFromBackup.getUserToRoles());
                    user.setVerificationKey(userFromBackup.getVerificationKey());

                    userDao.save(user);

                    userNames.add(userFromBackup.getUsername());

                } else {
                    LOGGER.warn("User '" + userFromBackup.getUsername() + "' already exists in the database. Ignoring...");
                }
            }
        }

        //restore


//        backup.setJobCountPerDay(jobCountPerDayDao.getAll());
//        backup.setIndustries(jobService.getIndustries());
//        backup.setRegions(jobService.getRegions());
//        backup.setRoles(roleDao.getAll());
//        backup.setUsers(userService.getAllUsers());
//        backup.setJobs(jobService.getJobs());

    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.DemoService#createDatabase()
     */
    @Override
    public void createDatabase() {

        final Ejb3Configuration cfg = new Ejb3Configuration();
        final Ejb3Configuration configured = cfg.configure( fb.getPersistenceUnitInfo(), fb.getJpaPropertyMap() );
        final Configuration configuration = configured.getHibernateConfiguration();

        final SchemaExport schemaExport;

        try {
            schemaExport = new SchemaExport(configuration, dataSource.getConnection());
        } catch (HibernateException e) {
            throw new IllegalStateException(e);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        schemaExport.create(true, true);

    }

    private static class HibernateHack {
        public static DataSource dataSource;
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.DemoService#createDatabase()
     */
    @Override
    public void updateDatabase() {

        final Ejb3Configuration cfg = new Ejb3Configuration();
        final Ejb3Configuration configured = cfg.configure( fb.getPersistenceUnitInfo(), fb.getJpaPropertyMap() );
        final Configuration configuration = configured.getHibernateConfiguration();

        HibernateHack.dataSource = dataSource;

        Properties props = new Properties();
        props.put("hibernate.connection.provider_class",
         "org.jrecruiter.service.impl.DemoServiceImpl.HibernateHack");


        final org.hibernate.tool.hbm2ddl.SchemaUpdate schemaUpdate;

        try {
            schemaUpdate = new SchemaUpdate(configuration, props);
        } catch (HibernateException e) {
            throw new IllegalStateException(e);
        }

        schemaUpdate.execute(true, true);

    }

    //~~~~~Helper Method~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private User getUser() {

        User user = new User();
        user.setUsername("demo44");
        user.setEmail("demo@demo.com");
        user.setFirstName("Demo First Name");
        user.setLastName("Demo Last Name");
        user.setPassword("demo");
        user.setPhone("123456");
        user.setRegistrationDate(new Date());

        return user;

    }
}
