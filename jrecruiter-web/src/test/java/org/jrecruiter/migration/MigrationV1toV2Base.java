package org.jrecruiter.migration;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.jrecruiter.service.migration.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Base class for Migration Test Cases.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
@ContextConfiguration(locations = { "classpath:org/jrecruiter/migration/applicationContextV1_jdbc.xml",
							        "classpath:spring/applicationContext.xml",
							        "classpath:spring/applicationContext-mail.xml",
							        "classpath:spring/applicationContext-resources.xml",
							        "classpath:spring/applicationContext-security.xml",
							        "classpath:spring/applicationContext-jpa.xml"
							       })
public abstract class MigrationV1toV2Base extends AbstractTransactionalJUnit4SpringContextTests {

    protected SimpleJdbcTemplate jdbcTemplateV1;
    protected @Autowired MigrationService migrationService;
    
    protected @PersistenceContext(unitName="base") EntityManager entityManager;
    
    @Autowired
    public void setDataSourceV1(@Qualifier("dataSourceV1") final DataSource dataSourceV1) {
        this.jdbcTemplateV1 = new SimpleJdbcTemplate(dataSourceV1);
    }
    
	@Autowired
	public void setDataSource(@Qualifier("dataSource") final DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
}
