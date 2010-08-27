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
package org.jrecruiter.service.system.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.dao.BackupDao;
import org.jrecruiter.dao.IndustryDao;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.dao.RegionDao;
import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.dao.SchemaMigrationDao;
import org.jrecruiter.dao.SystemDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.SchemaMigration;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.model.export.Backup;
import org.jrecruiter.scala.Region;
import org.jrecruiter.service.SystemSetupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Service("systemSetupService")
@Transactional
public class SystemSetupServiceImpl implements SystemSetupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemSetupServiceImpl.class);

    private @Autowired RegionDao   regionDao;
    private @Autowired RoleDao     roleDao;
    private @Autowired UserDao     userDao;
    private @Autowired JobDao      jobDao;
    private @Autowired IndustryDao industryDao;
    private @Autowired SystemDao   systemDao;
    private @Autowired BackupDao   backupDao;
    private @Autowired SchemaMigrationDao   schemaMigrationDao;

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

            this.jobDao.save(job);
        }
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.DemoService#restore(java.io.InputStream)
     */
    @Override
    public void restore(final InputStream inputStream) {

        final Backup backup = backupDao.convertToBackupData(inputStream);

        this.restore(backup);

    }

    /** {@inheritDoc} */
    @Override
    public void loadAndRestoreSeedData() {
        final InputStream is = SystemSetupServiceImpl.class.getResourceAsStream("/org/jrecruiter/server/seeddata/seeddata.xml");
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

                    if (userFromBackup.getUserToRoles() != null && !userFromBackup.getUserToRoles().isEmpty()) {

                    	for (UserToRole userToRole : userFromBackup.getUserToRoles()) {
                    		final Role role = roleDao.getRole(userToRole.getRoleName());

                    		if (role == null) {
                    			throw new IllegalStateException("No role found for rolename: " + userToRole.getRoleName());
                    		}

                    		user.getUserToRoles().add(new UserToRole(null, role, user));
                    	}

                    }

                    user.setVerificationKey(userFromBackup.getVerificationKey());

                    userFromBackup.getUserToRoles().iterator().next().getRole();

                    userDao.save(user);

                    userNames.add(userFromBackup.getUsername());

                } else {
                    LOGGER.warn("User '" + userFromBackup.getUsername() + "' already exists in the database. Ignoring...");
                }
            }
        }

        //restore

      //  backup.setJobCountPerDay(jobCountPerDayDao.getAll());
        backup.setIndustries(industryDao.getAllIndustriesOrdered());
        backup.setRegions(regionDao.getAllRegionsOrdered());
        backup.setRoles(roleDao.getAll());
        backup.setUsers(userDao.getAllUsers());
        backup.setJobs(jobDao.getAllJobs());

    }

	@Override
	public void createDatabase() {
		systemDao.createDatabase();
	}

	@Override
	public void updateDatabase() {
		systemDao.updateDatabase();
	}

	@Override
	public boolean isDatabaseSetup() {
		final List<SchemaMigration> migrations = schemaMigrationDao.getAll();

		if (migrations.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public Backup convertToBackupData(InputStream inputStream) {
		return backupDao.convertToBackupData(inputStream);
	}



}
