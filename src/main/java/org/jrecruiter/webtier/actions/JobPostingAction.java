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
package org.jrecruiter.webtier.actions;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobServiceInterface;
import org.jrecruiter.webtier.Util;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Struts action class for handling job postings.
 * 
 * @author Gunnar Hillert
 * @version $Revision: 1.6 $, $Date: 2006/03/01 05:26:55 $, $Author: ghillert $
 */
public class JobPostingAction extends DispatchAction {

    public static final Logger logger = Logger.getLogger(JobPostingAction.class);

    public ActionForward openAddJobPosting(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        return mapping.findForward("success");
    }

    public ActionForward addJobPosting(ActionMapping mapping, ActionForm form,
                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();

        ApplicationContext context = WebApplicationContextUtils.
            getRequiredWebApplicationContext(servlet.getServletContext());

        if (isCancelled(request)) {

            messages.add("info", new ActionMessage("errors.cancel"));
            saveMessages(request, messages);

            return mapping.findForward("cancel");
        }

        if (request.getUserPrincipal() != null) {
            Job job = new Job();
            BeanUtils.copyProperties(job, form);

            JobServiceInterface service = (JobServiceInterface) context.
            getBean("jobService");

            job.setStatus(1);
            job.setUsername(request.getRemoteUser());
            job.setRegisterDate(new Date(new java.util.Date().getTime()));
            job.setUpdateDate(new Date(new java.util.Date().getTime()));
            
            User owner = new User();
            owner.setUsername(job.getUsername());
            job.setOwner(owner);
            service.addJob(job);
            service.sendJobPostingToMailingList(job);

            messages.add("info", new ActionMessage("jobposting.add.success"));
            saveMessages(request, messages);

            return mapping.findForward("success");
        }
        return mapping.findForward("sessionTimeout");
    }

    public ActionForward listJobPostings(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        if (request.getUserPrincipal() != null) {

            ApplicationContext context = WebApplicationContextUtils.
            getRequiredWebApplicationContext(servlet.getServletContext());

            JobServiceInterface service = (JobServiceInterface) context.
            getBean("jobService");

            List jobs = service.
                    getUsersJobs(request.getRemoteUser());

            request.setAttribute("JobList", jobs);

            return mapping.findForward("showJobList");
        }
        return mapping.findForward("sessionTimeout");
    }

    public ActionForward openEditJobPosting(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {

        Long id = Long.valueOf(request.getParameter("id"));

        ApplicationContext context = WebApplicationContextUtils.
        getRequiredWebApplicationContext(servlet.getServletContext());

        JobServiceInterface service = (JobServiceInterface) context.
        getBean("jobService");

        Job job = service.getJobForId(id);
        BeanUtils.copyProperties(form, job);
        
        request.setAttribute("statistics", job.getStatistics());
        
        return mapping.findForward("success");
    }

    public ActionForward editJobPosting(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();

        if (isCancelled(request)) {

            messages.add("info", new ActionMessage("errors.cancel"));
            saveMessages(request, messages);

            return mapping.findForward("cancel");

        }

        if (request.getUserPrincipal() != null) {

            ApplicationContext context = WebApplicationContextUtils.
            getRequiredWebApplicationContext(servlet.getServletContext());

            JobServiceInterface service = (JobServiceInterface) context.
            getBean("jobService");

            DynaValidatorForm dvf = (DynaValidatorForm) form;

            Job job = service.getJobForId(new Long((String)dvf.get("id")));
            
            if (job != null) {
                
                String jobDescription = (String) dvf.get("description");
                
                job.setBusinessName((String) dvf.get("businessName"));
                job.setBusinessLocation((String) dvf.get("businessLocation"));
                job.setJobTitle((String) dvf.get("jobTitle"));
                job.setSalary((Double) dvf.get("salary"));
                job.setDescription(Util.stripTags(jobDescription, "<b>"));
                job.setWebsite((String) dvf.get("website"));
                job.setBusinessAddress1((String) dvf.get("businessAddress1"));
                job.setBusinessAddress2((String) dvf.get("businessAddress2"));
                job.setBusinessCity((String) dvf.get("businessCity"));
                job.setBusinessState((String) dvf.get("businessState"));
                job.setBusinessZip((String) dvf.get("businessZip"));
                job.setBusinessPhone((String) dvf.get("businessPhone"));
                job.setBusinessEmail((String) dvf.get("businessEmail"));
                job.setIndustry((String) dvf.get("industry"));
                job.setJobRestrictions((String) dvf.get("jobRestrictions"));
                job.setUpdateDate(new Date(new java.util.Date().getTime()));
                service.updateJob(job);
            } else {
                throw new IllegalArgumentException("Job with id " + dvf.get("id") + " does not exist.");
            }
            
            messages.add("info", new ActionMessage("jobposting.edit.success"));
            saveMessages(request, messages);

            return mapping.findForward("success");
        }
        return mapping.findForward("sessionTimeout");
    }

    public ActionForward deleteJobPosting(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        ActionMessages messages = new ActionMessages();

        ApplicationContext context = WebApplicationContextUtils.
        getRequiredWebApplicationContext(servlet.getServletContext());

        JobServiceInterface service = (JobServiceInterface) context.
        getBean("jobService");
        
        if (isCancelled(request)) {

            messages.add("info", new ActionMessage("errors.cancel"));
            saveMessages(request, messages);

            return mapping.findForward("cancel");
        }

        DynaBean dynaForm = (DynaBean) form;

        List ids = (List) dynaForm.get("job");
        if(ids != null){
            for (int i = 0; i < ids.size(); i++) {
                LazyDynaBean aLong = (LazyDynaBean) ids.get(i);
                String delete = (String) aLong.get("delete");
                if (StringUtils.isNumeric(delete)) {
                    service.deleteJobForId(Long.valueOf(delete));
                }
            }
        }
        messages.add("info", new ActionMessage("jobposting.delete.success"));
        saveMessages(request, messages);

        return mapping.findForward("success");
    }

}
