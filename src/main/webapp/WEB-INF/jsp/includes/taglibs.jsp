<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>


<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<c:set var="datePattern"><s:text name="date.format"/></c:set>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>


