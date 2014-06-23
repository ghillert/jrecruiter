<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

   <jmesa:tableModel
        id="tag"
        items="${jobs}"
        var="job"
        stateAttr="restore"
        >
      <jmesa:htmlTable>
        <jmesa:htmlRow>
                <jmesa:htmlColumn styleClass="column1" property="id"                     titleKey="field.jobNumber"         filterable="false"/>
                <jmesa:htmlColumn styleClass="column2" property="jobTitle"               titleKey="jsp.show.jobs.job.title" filterable="false"/>
                <jmesa:htmlColumn styleClass="column3" property="statistic.counter"      titleKey="jsp.showStatistics.hits" filterable="false"/>
                <jmesa:htmlColumn styleClass="column5" property="statistic.lastAccess"   titleKey="jsp.showStatistics.last.access" pattern="${datePattern}" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
        </jmesa:htmlRow>
        </jmesa:htmlTable>
    </jmesa:tableModel>


