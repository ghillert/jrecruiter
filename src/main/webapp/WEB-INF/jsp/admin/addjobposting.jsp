<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<h2><fmt:message key="admin.add.job.title" /></h2>
<form:form method="post" id="addJobForm" focus="jobTitle">
	<form:errors path="*" cssClass="formError"/>

	<table class="jobposting">
	    <tr>
	      	<td><label for="jobTitle"><fmt:message key="field.jobTitle" /></label>*</td>
	      	<td>
	      		<form:input  path="jobTitle" id="jobTitle" size="11" tabindex="1" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
	      		<form:errors path="jobTitle" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessName"><fmt:message key="field.businessName" /></label>*</td>
	      	<td>
	      		<form:input  path="businessName" id="businessName" size="11" tabindex="2" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessName" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td><label for="businessLocation"><fmt:message key="field.location" /></label></td>
	      	<td>
	      		<form:input  path="businessLocation" id="businessLocation" size="11" tabindex="3" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessLocation" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessAddress1"><fmt:message key="field.address" /></label></td>
	      	<td>
	      		<form:input  path="businessAddress1" id="businessAddress1" size="11" tabindex="4" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessAddress1" cssClass="fieldError"/>
	      	</td>
	    </tr>
	    <tr>
	      	<td><label for="industry"><fmt:message key="field.industry" /></label></td>
	      	<td>
	      		<form:input  path="industry" id="industry" size="11" tabindex="5" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="industry" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessAddress2"><fmt:message key="field.address" /></label></td>
	      	<td>
	      		<form:input  path="businessAddress2" id="businessAddress2" size="11" tabindex="6" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessAddress2" cssClass="fieldError"/>
	      	</td>
	    </tr>
	    <tr>
	      	<td><label for="salary"><fmt:message key="field.salary" /></label></td>
	      	<td>
	      		<form:input  path="salary" id="salary" size="11" tabindex="7" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="salary" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessCity"><fmt:message key="field.city" /></label></td>
	      	<td>
	      		<form:input  path="businessCity" id="businessCity" size="11" tabindex="8" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessCity" cssClass="fieldError"/>
	      	</td>
	    </tr>
	    <tr>
	      	<td><label for="businessPhone"><fmt:message key="field.phone" /></label></td>
	      	<td>
	      		<form:input  path="businessPhone" id="businessPhone" size="11" tabindex="9" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessPhone" cssClass="fieldError"/>
	      	</td>
	      	<td><label for="businessState"><fmt:message key="field.state" /></label></td>
	      	<td>
	      		<form:input  path="businessState" id="businessState" size="11" tabindex="10" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessState" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td><label for="businessEmail"><fmt:message key="field.email" /></label>*</td>
	      	<td>
	      		<form:input  path="businessEmail" id="businessEmail" size="11" tabindex="11" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessEmail" cssClass="fieldError"/>
	      	<td><label for="businessZip"><fmt:message key="field.zip" /></label></td>
	      	<td>
	      		<form:input  path="businessZip" id="businessZip" size="11" tabindex="12" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="businessZip" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	      	<td><label for="website"><fmt:message key="field.website" /></label></td>
	      	<td>
	      		<form:input  path="website" id="website" size="11" tabindex="13" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors path="website" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	      	<td colspan="2"><fmt:message key="field.website.note" /></td>
	    </tr>
	    <tr>
	      	<td><label for="description"><fmt:message key="field.jobDescription" /></label>*</td>
	      	<td colspan="3">
	      		<form:textarea  path="description" id="description" rows="15" tabindex="14" style="width: 500px" onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors    path="description" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td><label for="jobRestrictions"><fmt:message key="field.jobRestrictions" /></label></td>
	      	<td colspan="3">
	      		<form:textarea  path="jobRestrictions" id="jobRestrictions" rows="10" tabindex="15" style="width: 500px"  onfocus="javascript:this.className='selected';" onblur="javascript:this.className='';" cssErrorClass="error"/>
				<form:errors    path="jobRestrictionsy" cssClass="fieldError"/>
			</td>
	    </tr>
	    <tr>
	      	<td>&nbsp;</td>
	      	<td colspan="3"><fmt:message key="jobposting.texarea.note" /></td>
	    </tr>
	    <tr>
	      	<td>&nbsp;</td>
	      	<td>
	      		<input type="button" name="submit" value="<fmt:message key='jobposting.button.add'/>"    onClick="$('addJobForm').submit();">
	      		<input type="button" name="cancel" value="<fmt:message key='jobposting.button.cancel'/>" onClick="$('addJobForm').submit();"/>
		  	</td>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	    </tr>
	    <tr>
	      	<td>&nbsp; </td>
	      	<td colspan="3"><fmt:message key="jobposting.add.label.mandatory" /></td>
	    </tr>
	  </table>
  </form:form>
</div>