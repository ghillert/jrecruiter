/*
 *  http://www.jrecruiter.org
 *
 *  Disclaimer of Warranty.
 *
 *  Unless required by applicable law or agreed to in writing, Licensor provides
 *  the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 *  including, without limitation, any warranties or conditions of TITLE,
 *  NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
 *  solely responsible for determining the appropriateness of using or
 *  redistributing the Work and assume any risks associated with Your exercise of
 *  permissions under this License.
 *
 */
package org.jrecruiter.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * This Exception Resolver will handle any uncaught exceptions that were
 * bubbling up. It is extending Spring's SimpleMappingExceptionResolver and 
 * adds additional error logging.
 * 
 * see also: http://forum.springframework.org/showthread.php?t=27296
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver {

    public static final Logger LOGGER = Logger
            .getLogger(CustomMappingExceptionResolver.class);

    /**
     * Constructor.
     */
    public CustomMappingExceptionResolver() {
        super();
    }

	public ModelAndView resolveException(
		    HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
	{
		LOGGER.error("Unhandled Exception", ex);
		return super.resolveException(request, response, handler, ex);
	}

}
