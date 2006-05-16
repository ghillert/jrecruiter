/**
 * 
 */
package org.jrecruiter.dao;
import org.jrecruiter.persistent.dao.JobsDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 *
 */
public class TestJobs {

	ApplicationContext ctx;
	
	  @Configuration(beforeTestClass = true)
	  public void configure() {
	    System.out.println("Initialization");
	    ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
	  }

	  @Test(groups = {"exec-group"})
	    public void exec() {
	    System.out.println("exec");
	    JobsDAO jobs = (JobsDAO) ctx.getBean("jobsDAO");
	    jobs.searchByKeyword("java");
	    assert 1 == 1;
	  }

}
