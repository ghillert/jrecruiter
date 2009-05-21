<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

   <jmesa:tableFacade
        id="userListTable2"
        items="${users}"
        limit="${limit}"
        var="user"
        stateAttr="restore"
        >
      <jmesa:htmlTable>
          <jmesa:htmlRow>
              <jmesa:htmlColumn property="2" filterable="false" title="&nbsp;">
                  <s:url action="edit-user" id="editUserUrl">
                      <s:param name="userId" value="%{#attr.user.id}"/>
                  </s:url>
                  <s:url id="editJobPostingUrl" action="edit-job"><s:param name="id" value="#attr.row.job.id"/></s:url>
                  <a href="${editUserUrl}"><img alt="Edit this User." title="Edit this User." src="${ctx}/images/icons/crystal/edit.png" style="border-width: 0;"/></a>
              </jmesa:htmlColumn>
            <jmesa:htmlColumn styleClass="userlist5" titleKey="userList.th.delete" sortable="false" property="1" filterable="false">
                <input type="checkbox" name="userIds[${rowcount-1}]" value="${user.id}" class="checkbox"/>
            </jmesa:htmlColumn>
            <jmesa:htmlColumn styleClass="userlist2" property="username" sortable="true">
                    <s:property value="%{#attr.user.username}" escape="true"/>
            </jmesa:htmlColumn>
            <jmesa:htmlColumn styleClass="userlist2" property="firstName"         titleKey="user.firstName"    sortable="true"/>
            <jmesa:htmlColumn styleClass="userlist3" property="lastName"          titleKey="user.lastName"     sortable="true"/>
            <jmesa:htmlColumn styleClass="userlist4" property="registrationDate"  titleKey="user.registerDate" sortable="true" filterable="false">
                <fmt:formatDate value="${user.registrationDate}" type="date" pattern="MM/dd/yy"/>
            </jmesa:htmlColumn>
            <jmesa:htmlColumn property="company"     titleKey="user.company"/>
        </jmesa:htmlRow>
        </jmesa:htmlTable>
    </jmesa:tableFacade>
