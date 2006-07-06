/*
 * http://www.jrecruiter.org
 *
 * Disclaimer of Warranty.
 *
 * Unless required by applicable law or agreed to in writing, Licensor provides
 * the Work (and each Contributor provides its Contributions)
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied, including, without limitation, any warranties or
 * conditions of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
 * PARTICULAR PURPOSE. You are solely responsible for determining the
 * appropriateness of using or redistributing the Work and assume any risks
 * associated with Your exercise of permissions under this License.
 *
 */
package org.jrecruiter.webtier.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter that make sure that data is processed as UTF-8.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public final class CharsetFilter implements Filter {

    /**
     * The encoding to be used.
     */
    private transient String encoding;

    /**
     * Filter initialization.
     *
     * @param config Configuration paramters
     * @throws ServletException
     */
    public void init(final FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");

        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    /**
     * The actual filtering.
     *
     * @param request ServletRequest
     * @param response ServletResponse
     * @param next FilterChain
     *
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain next)
                throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        next.doFilter(request, response);
    }

    /**
     * Called when shutting down.
     */
    public void destroy() {
    }
}
