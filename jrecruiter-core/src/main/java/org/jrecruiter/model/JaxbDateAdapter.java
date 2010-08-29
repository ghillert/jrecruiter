package org.jrecruiter.model;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jrecruiter.common.CalendarUtils;


/**
 * The date information stored for throughout the JRecruiter domain model is
 * using @see {@link java.util.Date} which in most situation is fine as we only
 * care about UTC times. However, when serializing out XML data using {@link java.util.Date}
 * Jaxb by default uses the {@link XMLGregorianCalendar} to serialize the date.
 *
 * Since {@link java.util.Date} does not store Timezone information, Jaxb by
 * default uses the Timezone information from the JVM. Therefore, with the default
 * behavior using JAXB, JRecruiter instance running in different timezone would yield
 * different XML serialized data.
 *
 * Hence, the {@link JaxbDateAdapter} that ensures that all dates serialized out
 * will conform to UTC.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class JaxbDateAdapter extends XmlAdapter<String, Date> {

	    public String marshal(Date date) throws Exception {
	    	return CalendarUtils.getXmlFormatedDate(date);
	    }

	    public Date unmarshal(String dateString) throws Exception {

	    	final DatatypeFactory dataTypeFactory;

	        try {
	            dataTypeFactory = DatatypeFactory.newInstance();
	        } catch (DatatypeConfigurationException e) {
	            throw new IllegalStateException(e.getMessage(),e);
	        }

	        final XMLGregorianCalendar xmlCalendar = dataTypeFactory.newXMLGregorianCalendar(dateString);

	        return xmlCalendar.toGregorianCalendar().getTime();

	    }


}
