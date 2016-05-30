<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	committeeMemberWelcome.jsp
 * 
 * Date of Creation		:	14-Sep-2015
 *  
 * Summary				:	Welcome page for Assistant Deans 
 *
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- link href="http://externalcdn.com/respond-proxy.html" id="respond-proxy" rel="respond-proxy" /-->


<%@include file="../ui/cssWelcome.jsp" %>
<%@include file="../ui/jsCode.jsp" %>

<portlet:renderURL var="urlFacultySummary">
	<portlet:param name="action" value="facultyWelcome"/>
</portlet:renderURL>

<portlet:renderURL var="urlSurveyReport">
	<portlet:param name="action" value="surveyReport"/>
</portlet:renderURL>

<portlet:renderURL var="varAdminWelcome">
	<portlet:param name="action" value="adminWelcome"/>
</portlet:renderURL>

<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title"><spring:message code="prop.course.teaching.survey.title"/></h3>
	  </div>
	  <div class="panel-body">
	   
	   	<div class="row">
	   	
	   	 <div class="col-xs-${colAdjust}">
		    <div class="thumbnail">
			<h1> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span></h1>
		      <div class="caption">
		        <h3><a href="${urlSurveyReport}"><spring:message code="prop.course.teaching.survey.report" /></a></h3>
		        <p>&nbsp;</p>
		      </div>
		    </div>
		  </div>

		  <div class="col-xs-${colAdjust}">
		    <div class="thumbnail">
			<h1> <span class="glyphicon glyphicon-random" aria-hidden="true"></span></h1>
		      <div class="caption">
		        <h3><a href="https://sslportal.squ.edu.om/portal/page/portal/SQUPortal_Faculty_Page_Group/rep_chart_teaching_survey"><spring:message code="prop.course.teaching.survey.statistics"/></a></h3>
		        <p>&nbsp;</p>
		      </div>
		    </div>
		  </div>

	   	
		  <div class="col-xs-${colAdjust}">
		    <div class="thumbnail">
			<h1> <span class="glyphicon glyphicon-th" aria-hidden="true"></span></h1>
		      <div class="caption">
		        <h3><a href="${urlFacultySummary}"><spring:message code="prop.course.teaching.survey.summary.self"/></a></h3>
		        <p>&nbsp;</p>
		      </div>
		    </div>
		  </div>
	<c:if test="${isAdmin}">
		  <div class="col-xs-${colAdjust}">
		    <div class="thumbnail">
			<h1><font color="red"> <span class="glyphicon glyphicon-cog" aria-hidden="true"></span></font></h1>
		      <div class="caption">
		        <h3><a href="${varAdminWelcome}"><spring:message code="prop.course.teaching.survey.committee.member.admin"/></a></h3>
		        <p>&nbsp;</p>
		      </div>
		    </div>
		  </div>
	</c:if>	  
		</div>

	  </div>
	   <div class="panel-footer"><spring:message code="prop.course.teaching.survey.options"/></div>
</div>