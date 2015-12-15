<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	openEndQuestions.jsp
 * 
 * Date of Creation		:	27-Aug-2015
 *  
 * Summary				:	Open End Questions for faculty
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


<%@include file="ui/cssWelcome.jsp" %>
<%@include file="ui/jsCode.jsp" %>


<div class="panel panel-default">
  <div class="panel-heading">
    <h5 class="panel-title"><div align="center"><spring:message code="prop.course.teaching.survey.heading"/></div></h5>
  </div>
  
  <div class="panel-body">
    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.course"/></b></div>
    <div class="col-xs-4">${survey.courseCode} / ${survey.courseName}</div>
    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.analysis.college"/></b></div>
    <div class="col-xs-2">${survey.collegeName}</div>
    
    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.section"/></b></div>
    <div class="col-xs-2">
    	<c:forEach items="${survey.surveyResponses}" var="res">
    		${res.sectionNo}
    		<c:set value="${res.seatsTaken}" var="studentRegistered"/>
    	</c:forEach>
    </div>
    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.analysis.department"/></b></div>
    <div class="col-xs-4">${survey.departmentName}</div>
    
    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.instructor"/></b></div>
    <div class="col-xs-2">${survey.empName}</div>
    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.students.registered.no"/></b></div>
    <div class="col-xs-4">${studentRegistered}</div>
  </div>
  <div class="panel-footer"><div align="center"><b><spring:message code="prop.course.teaching.survey.analysis.heading.summary" arguments="${semesterYear}"/></b></div></div>
</div>	

<table class="table table-bordered">
	<thead>
		<tr>
			<td><spring:message code="prop.course.teaching.survey.analysis.open.end.q1"/></td>
			<td><spring:message code="prop.course.teaching.survey.analysis.open.end.q2"/></td>
			<td><spring:message code="prop.course.teaching.survey.analysis.open.end.q3"/></td>
		</tr>
	</thead>

	<c:forEach items="${questions}" var="que">
		<tr>
			<td>${que.question1}</td>
			<td>${que.question2}</td>
			<td>${que.question3}</td>
		</tr>
	</c:forEach>
</table>
			  
