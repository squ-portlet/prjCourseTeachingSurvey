<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	surveyReportSummary.jsp
 * 
 * Date of Creation		:	01-Sep-2015
 *  
 * Summary				:	survey report 
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

 


<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	<c:forEach items="${surveyYears}" var="aSurveyYear" varStatus="theCount">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="heading${theCount.count}">
				<h4 class="panel-title">
					<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${theCount.count}" aria-expanded="false" aria-controls="collapse${theCount.count}">
				          ${aSurveyYear.academicYear}
		    	    </a>
				</h4>
			</div>
			
			<div id="collapse${theCount.count}" class="panel-collapse collapse" role="tabpanel" aria-expanded="false" aria-labelledby="heading${theCount.count}">
					<div class="panel-body">
						<div class="row show-grid divColGreen">
							<div class="col-xs-2">
								<spring:message code="prop.course.teaching.survey.year"/> / <spring:message code="prop.course.teaching.survey.semester"/>
							</div>
						</div>
					<c:forEach items="${aSurveyYear.surveys}" var="aSurvey">
					<portlet:renderURL var="urlValidSurveyReportSummary">
						<portlet:param name="action" value="validSurveyReportSummary"/>
						<portlet:param name="semesterCode" value="${aSurvey.yearSemester}"/>
					</portlet:renderURL>
					<portlet:renderURL var="urlNotValidSurveyReportSummary">
						<portlet:param name="action" value="notValidSurveyReportSummary"/>
						<portlet:param name="semesterCode" value="${aSurvey.yearSemester}"/>
					</portlet:renderURL>
					<c:if test="${ isCurrentSemesterViewable || !(aSurvey.yearSemester == currentSemester) }">
						<div class="row show-grid">
							<div class="col-xs-2">
								${aSurvey.yearSemester}
							</div>
							<div class="col-xs-2">
								<a href="${urlValidSurveyReportSummary}"><spring:message code="prop.course.teaching.survey.report.survey.valid"/></a>
							</div>
							<div class="col-xs-2">
								<a href="${urlNotValidSurveyReportSummary}"><spring:message code="prop.course.teaching.survey.report.survey.invalid"/></a>
							</div>
						</div>
					</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:forEach>
</div>


 