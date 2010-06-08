package org.jrecruiter.spring;

import java.util.List;

import org.jasypt.digest.StringDigester;
import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.dao.SchemaMigrationDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.SchemaMigration;
import org.jrecruiter.service.DemoService;
import org.jrecruiter.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

public class ContextRefreshedEventListener implements
                                    ApplicationListener < ContextRefreshedEvent > {

    private @Autowired UserDao userDao;
    private @Autowired JobService  jobService;
    private @Autowired DemoService demoService;
    private @Autowired StringDigester stringDigester;
    private @Autowired RoleDao roleDao;
    private @Autowired SchemaMigrationDao schemaMigrationDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextRefreshedEventListener.class);

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //From: http://forum.springsource.org/showthread.php?t=84312&page=2

        if (event.getApplicationContext().getParent() == null) {

            LOGGER.info("Parent Spring Context started.");

            List<SchemaMigration> migrations = schemaMigrationDao.getAll();

            if (migrations.isEmpty()) {
                LOGGER.info("jRecruiter Database is not setup, yet. Initializing DB...");

                demoService.createDatabase();

                LOGGER.info("jRecruiter Database is not setup, yet. Populating Seed Data...");

                demoService.loadAndRestoreSeedData();

                LOGGER.info("jRecruiter Database is not setup, yet. Populating Test Data...");

            } else {
                LOGGER.info("jRecruiter Database is running version '%s'", migrations.get(0));
            }

//            final User user = new User();
//
//            user.setCompany("jRecruiter");
//            user.setEmail("demo@jrecruiter.org");
//            user.setRegistrationDate(new Date());
//            user.setEnabled(true);
//            user.setFirstName("Admin");
//            user.setLastName("Admin");
//            user.setUsername("admin");
//            user.setPassword(stringDigester.digest("demo"));
//            user.setUserAuthenticationType(UserAuthenticationType.USERNAME_PASSWORD);
//
//            Role adminRole = new Role(Roles.ADMIN.name()); //FIXME Map role directly to Enum
//
//            Role savedAdminRole   = roleDao.save(adminRole);
//
//            final Set<UserToRole>userRoles = CollectionUtils.getHashSet();
//
//            final UserToRole userToRole = new UserToRole();
//            userToRole.setRole(savedAdminRole);
//            userToRole.setUser(user);
//
//            userRoles.add(userToRole);
//
//            user.setUserToRoles(userRoles);
//
//            userDao.save(user);

        } else {
            System.out.println(">>>>>>>>  Child started; ignoring");
        }
    }

}
