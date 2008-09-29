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
package org.jrecruiter.service;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jrecruiter.common.Constants;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.statistics.JobCountPerDay;

/**
 *
 * @author Jerzy Puchala
 * @author Gunnar Hillert
 * @version $Id:JobService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface DataService {

    /**
     * Method for an images of a Google Map
     *
     * @param longitude
     * @param latitude
     * @param zoomLevel
     *
     */
    Image getGoogleMapImage(BigDecimal longitude, BigDecimal latitude, Integer zoomLevel);

}
