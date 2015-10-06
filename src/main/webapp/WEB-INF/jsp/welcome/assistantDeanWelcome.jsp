<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	assistantDeanWelcome.jsp
 * 
 * Date of Creation		:	31-Aug-2015
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

<portlet:renderURL var="urlCoursesForAsstDean">
	<portlet:param name="action" value="listCollegeCoursesForAsstDean"/>
</portlet:renderURL>


<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title"><spring:message code="prop.course.teaching.survey.title"/></h3>
	  </div>
	  <div class="panel-body">
	   
	   	<div class="row">
	   	
	   	 <div class="col-xs-4">
		    <div class="thumbnail">
			<h1> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span></h1>
		      <div class="caption">
		        <h3><spring:message code="prop.course.teaching.survey.courses.list"/></h3>
		        <p>&nbsp;</p>
		        <p><a href="${urlCoursesForAsstDean}" class="btn btn-primary" role="button"><spring:message code="prop.course.teaching.survey.button.submit" /></a></p>
		      </div>
		    </div>
		  </div>
	   	
		  <div class="col-xs-4">
		    <div class="thumbnail">
			<h1> <span class="glyphicon glyphicon-th" aria-hidden="true"></span></h1>
		      <div class="caption">
		        <h3><spring:message code="prop.course.teaching.survey.summary.self"/></h3>
		        <p>&nbsp;</p>
		        <p><a href="${urlFacultySummary}" class="btn btn-primary" role="button"><spring:message code="prop.course.teaching.survey.button.submit" /></a></p>
		      </div>
		    </div>
		  </div>
		  
		</div>

	  </div>
	   <div class="panel-footer"><spring:message code="prop.course.teaching.survey.options"/></div>
</div>