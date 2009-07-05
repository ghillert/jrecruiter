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
package org.jrecruiter.dao.jpa;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Repository("userDao")
public final class UserDaoJpa extends GenericDaoJpa< User, Long>
implements UserDao {

    /**
     * Constructor.
     *
     */
    private UserDaoJpa() {
        super(User.class);
    }

    /**
     * Get a user from persistence store.
     * @param username
     * @return A single user
     */
    public User getUser(String username) {

        User user;

        final Query query = entityManager.createQuery(
                "select user from User user " +
                "left join fetch user.userToRoles utr " +
                "left join fetch utr.role "
                + "where user.username= :username ");
        query.setParameter("username", username);
        query.getResultList();

        try {
            user = (User) query.getSingleResult();
        } catch(NoResultException e) {
            user = null;
        }

        return user;
    }

    @Override
    public User getUserByUsernameOrEmail(final String usernameOrEmail) {
        User user;

        final Query query = entityManager.createQuery(
                "select user from User user " +
                "left join fetch user.userToRoles utr " +
                "left join fetch utr.role "
                + "where user.username = :usernameOrEmail or user.email = :usernameOrEmail ");
        query.setParameter("usernameOrEmail", usernameOrEmail);
        query.getResultList();

        try {
            user = (User) query.getSingleResult();
        } catch(NoResultException e) {
            user = null;
        }

        return user;
    }

    /**
     * Return all users from persistence store.
     * @return List of users
     */
    @SuppressWarnings("unchecked")
    public List < User > getAllUsers() {

        List < User >  users = entityManager.createQuery(
        "from User user order by user.username ASC").getResultList();

        return users;
    }

    /**
     * Delete an array of users from persistence store.
     *
     * @param usernameList list of user names.
     */
    @SuppressWarnings("unchecked")
    public void deleteUser(final String[] usernameList) {

        Session session = (Session)entityManager.getDelegate();

        List<User> list = (List<User>) session.createCriteria(User.class).add(
                Expression.in("username", usernameList)).list();

        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);

            this.remove(user.getId());
        }
    }

    /**
     * Method for returning list of available job postings.
     * @param pageSize Max number of results returned
     * @param pageNumber Which page are you one?
     * @param fieldSorted Which field shall be sorted
     * @param sortOrder What is the sort order?
     * @return List of jobs.
     */
    @SuppressWarnings("unchecked")
    public List < User > getUsers(final Integer pageSize,
                                final Integer pageNumber,
                                Map<String, String> sortOrders,
                                Map<String, String> userFilters) {
        List < User > users;

        if (pageSize == null) {
            throw new IllegalStateException("pageSize must not be null.");
        }
        if (pageNumber == null) {
            throw new IllegalStateException("pageNumber must not be null.");
        }

        if (sortOrders == null) {
            sortOrders = CollectionUtils.getHashMap();
        }

        if (sortOrders.isEmpty()) {
            sortOrders.put("lastName", "ASC");
        }

        if (userFilters == null) {
            userFilters = CollectionUtils.getHashMap();
        }


        Session session = (Session)entityManager.getDelegate();
        final Criteria criteria = session.createCriteria(User.class);

        for (Entry<String, String> entry : sortOrders.entrySet()) {
            if (entry.getValue().equalsIgnoreCase("DESC")) {
                criteria.addOrder(Order.desc(entry.getKey()));
            } else if (entry.getValue().equalsIgnoreCase("ASC")) {
                criteria.addOrder(Order.asc(entry.getKey()));
            } else {
                throw new IllegalStateException("SortOrder " + entry.getValue() + " is not supported.");
            }
        }

        for (Entry<String, String> entry : userFilters.entrySet()) {
                criteria.add(Restrictions.ilike(entry.getKey(), entry.getValue()));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);

        users = criteria.list();

        return users;
    }


    /**
     * Returns the number of totally available jobs in the system.
     *
     * @return Total number of jobs
     * @see org.jrecruiter.dao.JobsDao#getJobsCount()
     */
    public Long getUsersCount() {

        Long numberOfUsers = null;

        Session session = (Session)entityManager.getDelegate();
        org.hibernate.Query query = session.createQuery("select count(*) from User");
        numberOfUsers = (Long) query.uniqueResult();

        return numberOfUsers;
    }

    /** {@inheritDoc} */
    public User getUserByVerificationKey(final String key) {

        User user;

        final Query query = entityManager.createQuery(
                "select user from User user "
              + "where user.verificationKey = :key ");
        query.setParameter("key", key);
        query.getResultList();

        try {
            user = (User) query.getSingleResult();
        } catch(NoResultException e) {
            user = null;
        }

        return user;
    }
}
