package org.jrecruiter.dao.hibernate;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
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

		PersistenceUnitInfo persistenceUnitInfo = fb.getPersistenceUnitInfo();
		Map<String, Object> props = fb.getJpaPropertyMap();

		try (FileWriter out = new FileWriter("schema-komplett.sql")) {
		   //Map<String, Object> props = new HashMap<>();
		   props.put("javax.persistence.schema-generation.scripts.action", "update");
		   props.put("javax.persistence.schema-generation.scripts.create-target", out);
		   props.put("hibernate.connection.provider_class", "org.jrecruiter.service.impl.DemoServiceImpl.HibernateHack");
		   Persistence.generateSchema(persistenceUnitInfo.getPersistenceUnitName(), props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see org.jrecruiter.service.DemoService#createDatabase()
	 */
	@Override
	public void createDatabase() {

		CustomPersistenceProviderResolver.persistenceProvider = fb.getPersistenceProvider();
		PersistenceProviderResolverHolder.setPersistenceProviderResolver(new CustomPersistenceProviderResolver());
		HibernateHack.dataSource = this.dataSource;

		PersistenceUnitInfo persistenceUnitInfo = fb.getPersistenceUnitInfo();
		//Map<String, Object> props = fb.getJpaPropertyMap();
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("hibernate.id.new_generator_mappings", true);
		props.put("hibernate.query.substitutions", "true '1', false '0'");
		props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.put("hibernate.ejb.naming_strategy", "org.jrecruiter.hibernate.ImprovedPluralizedNamingStrategy");
		//fb.getPersistenceProvider()
		for (Entry<String, Object> entry : props.entrySet()) {
			System.out.println(">>>>" + entry.getKey());
			System.out.println(">>>>" + entry.getValue());
		}

		try (FileWriter out = new FileWriter("/tmp/schema-komplett-create.sql")) {
		   //Map<String, Object> props = new HashMap<>();
		   props.put("javax.persistence.schema-generation.database.action", "create");
		   props.put("javax.persistence.schema-generation.connection", this.dataSource);
		   props.put("javax.persistence.schema-generation.scripts.create-target", out);
		   //props.put("hibernate.connection.provider_class", "org.jrecruiter.service.impl.DemoServiceImpl.HibernateHack");

		   fb.getPersistenceProvider().generateSchema(persistenceUnitInfo, props);

		   //Persistence.generateSchema(persistenceUnitInfo.getPersistenceUnitName(), props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class HibernateHack {
		public static DataSource dataSource;
	}

	static class CustomPersistenceProviderResolver implements PersistenceProviderResolver {

		public static PersistenceProvider persistenceProvider;

		@Override
		public List<PersistenceProvider> getPersistenceProviders() {
			return Arrays.<PersistenceProvider> asList(persistenceProvider);
		}

		@Override
		public void clearCachedProviders() {}
	}

}
