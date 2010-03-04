<%@ include file="/WEB-INF/jsp/includes/taglibs-spring.jsp"%>

	   <jmesa:tableFacade
	        id="systemInformationTable"
	        items="${systemProperties}"
	        maxRows="${fn:length(systemProperties)}"
	        var="systemProperty" 
	        stateAttr="systemInformationTableRestore"
	        view="org.jrecruiter.web.JmesaViewWithoutHeader">
	      <jmesa:htmlTable >
	        <jmesa:htmlRow >
	                <jmesa:htmlColumn property="propertyKey"   titleKey="jsp.show.jobs.table.job.title" />
	                <jmesa:htmlColumn property="propertyValue" titleKey="jsp.show.jobs.table.business.name"/>
	                <jmesa:htmlColumn property="description"   titleKey="jsp.show.jobs.table.job.location" filterable="false" sortable="false" />
	        </jmesa:htmlRow>
	        </jmesa:htmlTable>
	    </jmesa:tableFacade>
