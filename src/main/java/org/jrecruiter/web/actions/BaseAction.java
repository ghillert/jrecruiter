package org.jrecruiter.web.actions;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jrecruiter.common.AcegiUtil;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.jrecruiter.service.UserService;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public abstract class BaseAction extends ActionSupport {

    //~~~~~Services~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * User related services.
     */
    protected UserService userService;

    private String backTo;

    /**
     * Job related services.
     */
    protected JobService jobService;

    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @SkipValidation
    public String cancel() {

        if (this.backTo != null) {
            return "backTo";
        }

        return SUCCESS;
    }

    //~~~~~Security Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    protected SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    protected Authentication getAuthentication() {
        SecurityContext securityContext = getSecurityContext();
        if (securityContext != null) {
            return securityContext.getAuthentication();
        } else {
            return null;
        }
    }

    protected User getLoggedInUser() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            return (User) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    protected Boolean hasRole(String role) {
        User getLoggedInUser = getLoggedInUser();
        if (getLoggedInUser != null) {
            return AcegiUtil.hasRole(role);
        } else {
            return Boolean.FALSE;
        }
    }

    public String getBackTo() {
        return backTo;
    }

    public void setBackTo(String backTo) {
        this.backTo = backTo;
    }
}
