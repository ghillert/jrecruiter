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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.jrecruiter.Constants;
import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.service.UserService;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Dorota Puchala
 * @author Gunnar Hillert
 *
 * @version $Id$
 */
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    /**
     *   Used for creating the Apache-Velocity-based Email template.
     */
    private @Autowired VelocityEngine velocityEngine;

    /**
     * Mailsender.
     */
    private @Autowired MailSender mailSender;

    /**
     * Email message.
     */
    private @Autowired SimpleMailMessage message;

    /**
     * User Dao.
     */
    private @Autowired UserDao userDao;

    /**
     * UserRole Dao.
     */
    private @Autowired RoleDao roleDao;

    /**
     * Access to settings.
     */
    private @Autowired ConfigurationDao configurationDao;

    /**
     * {@inheritDoc}
     */
    public User addUser(User user) throws DuplicateUserException{

        Date registerDate = new Date();
        user.setRegistrationDate(registerDate);
        user.setUpdateDate(registerDate);

        User duplicateUser = userDao.getUser(user.getUsername());

        if (duplicateUser!= null) {
            throw new DuplicateUserException("User " + duplicateUser.getUsername()
                                           + "(Id="  + duplicateUser.getId()
                                           + ") already exists!");
        }

        Role role = roleDao.getRole(Constants.Roles.MANAGER.name());

        if (role == null) {
            throw new IllegalStateException("Role was not found but is required.");
        }

        Set<UserToRole> userToRoles = user.getUserToRoles();

        UserToRole utr = new UserToRole();
        utr.setRole(role);
        utr.setUser(user);

        userToRoles.add(utr);

        return this.saveUser(user);
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        return userDao.save(user);
    }


    public User getUser(String username) {

        return userDao.getUser(username);
    }

    public void updateUser(User user) {
        Date updateDate = new Date();
        user.setUpdateDate(updateDate);
        userDao.save(user);
    }

    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    public void deleteUser(User user){
        userDao.remove(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    public void sendPassword(User user) {

        final SimpleMailMessage msg = new SimpleMailMessage(this.message);
        msg.setSubject(configurationDao.get("mail.password.subject").getMessageText());
        msg.setFrom(configurationDao.get("mail.from").getMessageText());
        msg.setTo(user.getFirstName() + " " + user.getLastName()
                + "<" + user.getEmail() + ">");

        final Map < String, Object > model = new HashMap < String, Object > ();
        model.put("password", user.getPassword());

        String result = null;
        try {

            result = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "mail.password.body", model);
        } catch (VelocityException e) {
            e.printStackTrace();
        }
        msg.setText(result);

        //JavaMailSenderImpl sender=(JavaMailSenderImpl) this.mailSender;
        try {
            //JavaMailSenderImpl r = (JavaMailSenderImpl)mailSender;
            //r.getSession().setDebug(true);
            mailSender.send(msg);
        } catch (MailException ex) {
            LOGGER.error(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        final UserDetails user = this.getUser(username);

        if (user==null){

            LOGGER.warn("loadUserByUsername() - No user with id " + username + " found.");
            throw new UsernameNotFoundException("loadUserByUsername() - No user with id " + username + " found.");
        }

        LOGGER.info(user.getAuthorities());

        return user;
    }

    /**
     * {@inheritDoc}
     */
    public User getUser(Long userId) {
        return userDao.get(userId);
    }

}
