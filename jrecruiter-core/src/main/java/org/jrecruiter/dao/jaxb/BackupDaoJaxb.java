/**
 *
 */
package org.jrecruiter.dao.jaxb;

import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.jrecruiter.dao.BackupDao;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.export.Backup;
import org.jrecruiter.service.system.impl.SystemSetupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Repository;

/**
 * @author hillert
 *
 */
@Repository("backupDao")
public class BackupDaoJaxb implements BackupDao {

	/** Logger declaration */
	private static final Logger LOGGER = LoggerFactory.getLogger(BackupDaoJaxb.class);

    private @Autowired Jaxb2Marshaller marshaller;

	/**
	 *
	 */
	public BackupDaoJaxb() {
	}

    /* (non-Javadoc)
     * @see org.jrecruiter.service.DemoService#restore(java.io.InputStream)
     */
    @Override
    public Backup convertToBackupData(final InputStream inputStream) {

        final StreamSource source = new StreamSource(inputStream);
        final Backup backup = (Backup) marshaller.unmarshal(source);

        LOGGER.info("Restoring: " + backup.getUsers().size() + " users, "
                                  + backup.getRoles().size()      + " roles, "
                                  + backup.getJobs().size()       + " jobs, "
                                  + backup.getIndustries().size() + " industries, and "
                                  + backup.getRegions().size()    + " regions.");

        return backup;

    }

}
