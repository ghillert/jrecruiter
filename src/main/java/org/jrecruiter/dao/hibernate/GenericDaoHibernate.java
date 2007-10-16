package org.jrecruiter.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.jrecruiter.dao.GenericDao;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="org.appfuse.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="org.appfuse.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 */
@Repository
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected SessionFactory sf;

	private final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;

    public GenericDaoHibernate(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public void setSessionFactory(SessionFactory factory) {
		this.sf = factory;
	}

	@SuppressWarnings("unchecked")
    public List<T> getAll() {
		Criteria criteria = sf.getCurrentSession().createCriteria(this.persistentClass);
		return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public T get(PK id) {
        T entity = (T) sf.getCurrentSession().get(this.persistentClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        T entity = (T) sf.getCurrentSession().get(this.persistentClass, id);
        if (entity == null) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    public void save(T object) {
    	sf.getCurrentSession().save(object);
    }

    @SuppressWarnings("unchecked")
    public void update(T object) {
    	sf.getCurrentSession().update(object);
    }

    public void remove(PK id) {
    	sf.getCurrentSession().delete(this.get(id));
    }
}
