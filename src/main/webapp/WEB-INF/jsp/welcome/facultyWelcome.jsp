<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	facultyWelcome.jsp
 * 
 * Date of Creation		:	23-Aug-2015
 *  
 * Summary				:	cssWelcome
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


<%-- <c:if test="${isCurrentSemesterViewable}" > --%>
	<div class="row show-grid divColGreen">
		<div class="col-xs-2 " >
			<spring:message code="prop.course.teaching.survey.course.code"/>
			<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
		</div>
		<div class="col-xs-4" >
			<spring:message code="prop.course.teaching.survey.course.name"/>
		</div>
		<div class="col-xs-6">
			<div class="row show-grid divColGreen">
				<div class="col-xs-3" align="${varAlignSection}" >
					<spring:message code="prop.course.teaching.survey.section.no"/>
					<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
				</div>
				<div class="${colxs42}" align="center" >
					<spring:message code="prop.course.teaching.survey.student.registered"/>
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
				</div>
				<div class="col-xs-4" align="center">
					<spring:message code="prop.course.teaching.survey.student.perticipate"/>
					<span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span>
				</div>
			</div>
		</div>
	</div>
	<c:forEach items="${currentSurvey}" var="cSurvey">
		<div class="row show-grid ">
			<div class="col-xs-2">
				${cSurvey.courseCode}
			</div>
			<div class="col-xs-4">
				${cSurvey.courseName}
			</div>
			<div class="${colxs65}">
				<div class="row show-grid ">
				<c:forEach items="${cSurvey.surveyResponses}" var="cRes">
							<div class="${colxs24}"  align="right">
									${cRes.sectionNo}
							</div>
							<div class="${colxs34}" align="right">
									${cRes.seatsTaken}
							</div>
							<div class="col-xs-4" align="right">
									${cRes.studentResponse}
							</div>
						<div class="clearfix"></div>
				</c:forEach>
						
							
				</div>
			</div>
		</div>
	</c:forEach>
<%-- </c:if> --%>


<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	<c:forEach items="${allSurvey}" var="aSurveyYear" varStatus="theCount">
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
							<div class="col-xs-2">
								<spring:message code="prop.course.teaching.survey.course.code"/>
							</div>
							<div class="col-xs-2">
								<spring:message code="prop.course.teaching.survey.section.no"/>
							</div>							
						</div>
					<c:forEach items="${aSurveyYear.surveys}" var="aSurvey">
						<c:if test="${ isCurrentSemesterViewable || !(aSurvey.yearSemester == currentSemester) }">
								<div  class="row show-grid" >
									<div class="col-xs-2">
										${aSurvey.yearSemester}
									</div>
									<div class="col-xs-2">
										${aSurvey.courseCode}
									</div>
									<div class="col-xs-7">
										<c:forEach items="${aSurvey.surveyResponses}" var="aSurveyResponse">
												<portlet:renderURL var="varSurveyAnalysis">
													<portlet:param name="action" value="surveyAnalysis"/>
													<portlet:param name="empNumber" value="${aSurvey.empNumber}"/>
													<portlet:param name="courseCode" value="${aSurvey.courseCode}"/>
													<portlet:param name="semesterCode" value="${aSurvey.yearSemester}"/>
													<portlet:param name="sectionNo" value="${aSurveyResponse.sectionNo}"/>
												</portlet:renderURL>
												<portlet:renderURL var="varOpenEndQuestion">
													<portlet:param name="action" value="openEndQuestions"/>
													<portlet:param name="empNumber" value="${aSurvey.empNumber}"/>
													<portlet:param name="courseCode" value="${aSurvey.courseCode}"/>
													<portlet:param name="semesterCode" value="${aSurvey.yearSemester}"/>
													<portlet:param name="sectionNo" value="${aSurveyResponse.sectionNo}"/>
												</portlet:renderURL>
											<div class="row show-grid">
												<div class="col-xs-2" align="right">${aSurveyResponse.sectionNo}</div>
												<div class="col-xs-3"><a href="${varSurveyAnalysis}"><spring:message code="prop.course.teaching.survey.link.survey.summary"/></a></div>
												<div class="col-xs-4"><a href="${varOpenEndQuestion}"><spring:message code="prop.course.teaching.survey.link.open.end.question.summary"/></a></div>
											</div>
										</c:forEach>
									</div>
								</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:forEach>
</div>


