<%@ include file="/taglibs.jsp"%>
    <ajax:displayTag id="displayTagFrame" ajaxFlag="displayAjax" baseUrl="${ctx}/userList.do" postFunction="h()">
        <display:table name="userList" pagesize="15" requestURI="" id="user" class="displaytag" export="false" sort="list">
          <display:column class="userlist1" property="username" titleKey="user.username" sortable="true" media="html csv xml excel pdf"/>
          <display:column class="userlist2" property="firstName" titleKey="user.firstName" sortable="true" href="editUser.do?method=openEditJobPosting" paramId="username" paramProperty="username" media="html csv xml excel pdf"/>
          <display:column class="userlist3" property="lastName" titleKey="user.lastName" sortable="true" media="html csv xml excel pdf"/>
          <display:column class="userlist4" titleKey="user.registerDate" sortable="true" media="html csv xml excel pdf" sortProperty="updateDate">
            <fmt:formatDate value="${user.updateDate}" type="date" pattern="MM/dd/yy"/>
          </display:column>
          <display:column class="userlist5" titleKey="userList.th.delete" sortable="false" media="html csv xml excel pdf" sortProperty="updateDate">
            <input type="checkbox" name="UserListForm[${user_rowNum}].delete" value="${user.username}" onblur="javascript:this.className='';" onfocus="javascript:this.className='selected';" errorStyleClass="error"></input>
          </display:column>
        </display:table>
    </ajax:displayTag>

<script type="text/javascript">

     function h() {

     window.setTimeout("highlightTableRows('user')", 1000);

     }
</script>