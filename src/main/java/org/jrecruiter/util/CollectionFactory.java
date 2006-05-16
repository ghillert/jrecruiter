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
package org.jrecruiter.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jerzy Puchala
 * @version $Revision: 1.2 $, $Date: 2006/02/06 04:09:47 $, $Author: ghillert $
 */
public class CollectionFactory {
    public static List getListInstance() {
        return new ArrayList();
    }
}
