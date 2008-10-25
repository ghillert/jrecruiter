package org.jrecruiter.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.dao.GenericDao;

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
public class GenericDaoJpa<T, PK extends Serializable> implements GenericDao<T, PK> {


    protected EntityManager entityManager;



    private final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;

    public GenericDaoJpa(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return this.entityManager.createQuery(
                "select obj from " + this.persistentClass.getName() + " obj")
                .getResultList();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);
        return entity;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);
        return entity != null;
    }

    /** {@inheritDoc} */
    public T save(T object) {
        return this.entityManager.merge(object);
    }

    /** {@inheritDoc} */
    public void remove(PK id) {
        this.entityManager.remove(this.get(id));
    }
    @PersistenceContext(unitName="base")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
