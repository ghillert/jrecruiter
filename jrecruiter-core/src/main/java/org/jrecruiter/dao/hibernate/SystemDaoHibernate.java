package org.jrecruiter.dao.hibernate;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.jrecruiter.dao.SystemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

@Repository("systemDao")
public class SystemDaoHibernate implements SystemDao {

    private @Autowired DataSource dataSource;
    private @Autowired LocalContainerEntityManagerFactoryBean fb;

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


}
