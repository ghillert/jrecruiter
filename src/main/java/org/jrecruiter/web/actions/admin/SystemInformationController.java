package org.jrecruiter.web.actions.admin;

import org.jrecruiter.common.SystemPropertyInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class SystemInformationController {

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    @RequestMapping(value="/admin/systemInformation", method=RequestMethod.GET)
    public void execute(final ModelMap model) {
        model.addAttribute("systemProperties", SystemPropertyInformation.getSystemPropertyValuesAsList());
    }

}
