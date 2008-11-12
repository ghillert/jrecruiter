<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

   <jmesa:tableFacade
        id="tag"
        items="${users}"
        limit="${limit}"
        var="user"
        stateAttr="restore"
        >
      <jmesa:htmlTable>
        <jmesa:htmlRow>
                <jmesa:htmlColumn  titleKey="user.username" filterable="false">
                    <s:url action="edit-user" id="editUserUrl">
                        <s:param name="userId" value="%{#attr.user.id}"/>
                    </s:url>
                    <a href="${editUserUrl}">${user.username}</a>
                </jmesa:htmlColumn>
              <jmesa:htmlColumn styleClass="userlist2" property="firstName" titleKey="user.firstName"    sortable="true"/>
              <jmesa:htmlColumn styleClass="userlist3" property="lastName"  titleKey="user.lastName"     sortable="true"/>
              <jmesa:htmlColumn styleClass="userlist4"                      titleKey="user.registerDate" sortable="true">
                <fmt:formatDate value="${user.updateDate}" type="date" pattern="MM/dd/yy"/>
              </jmesa:htmlColumn>
              <jmesa:htmlColumn styleClass="userlist5" titleKey="userList.th.delete" sortable="false">
                <s:checkbox name="userIds" fieldValue="%{#attr.user.id}" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';"/>
                <s:property value="%{#attr.user.id}"/>
              </jmesa:htmlColumn>

                <jmesa:htmlColumn property="jobTitle"     titleKey="jsp.show.jobs.job.title" />
                <jmesa:htmlColumn property="businessName" titleKey="jsp.show.jobs.business.name"/>
                <jmesa:htmlColumn property="region.name"  titleKey="jsp.show.jobs.job.location" filterable="false"/>
                <jmesa:htmlColumn property="updateDate"   titleKey="jsp.show.jobs.date" pattern="${datePattern}" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
        </jmesa:htmlRow>
        </jmesa:htmlTable>
    </jmesa:tableFacade>
