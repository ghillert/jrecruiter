<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://code.google.com/p/jmesa" prefix="jmesa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<c:set var="datePattern"><spring:message code="date.format"/></c:set>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>


