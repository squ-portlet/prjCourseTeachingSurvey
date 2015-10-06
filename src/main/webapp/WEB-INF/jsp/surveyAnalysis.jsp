<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	surveyAnalysis.jsp
 * 
 * Date of Creation		:	24-Aug-2015
 *  
 * Summary				:	
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

<c:set value="${0}" var="cTotal"/>
<c:set value="${0}" var="cPctVal"/>
<c:set value="${0}" var="cSectionMean"/>
<c:set value="${0}" var="cCourseMean"/>
<c:set value="${0}" var="cDepart"/>
<c:set value="${0}" var="cCollege"/>

<c:set value="${0}" var="tTotal"/>
<c:set value="${0}" var="tPctVal"/>
<c:set value="${0}" var="tSectionMean"/>
<c:set value="${0}" var="tCourseMean"/>
<c:set value="${0}" var="tDepart"/>
<c:set value="${0}" var="tCollege"/>

<c:if test="${not empty survey}" >

				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h5 class="panel-title"><div align="center"><spring:message code="prop.course.teaching.survey.heading"/></div></h5>
				  </div>
				  
				  <div class="panel-body">
				    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.course"/></b></div>
				    <div class="col-xs-4">${survey.courseCode} / ${survey.courseName}</div>
				    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.analysis.college"/></b></div>
				    <div class="col-xs-4">${survey.collegeName}</div>
				    
				    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.section"/></b></div>
				    <div class="col-xs-4">
				    	<c:forEach items="${survey.surveyResponses}" var="res">
				    		${res.sectionNo}
				    		<c:set value="${res.seatsTaken}" var="studentRegistered"/>
				    	</c:forEach>
				    </div>
				    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.analysis.department"/></b></div>
				    <div class="col-xs-4">${survey.departmentName}</div>
				    
				    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.instructor"/></b></div>
				    <div class="col-xs-4">${survey.empName}</div>
				    <div class="col-xs-2"><b><spring:message code="prop.course.teaching.survey.students.registered.no"/></b></div>
				    <div class="col-xs-4">${studentRegistered}</div>
				  </div>
				  <div class="panel-footer"><div align="center"><b><spring:message code="prop.course.teaching.survey.analysis.heading.summary" arguments="${semesterYear}"/></b></div></div>
				</div>
				
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<td rowspan="2"> &nbsp;</td>
								<td rowspan="2" style="${alnright}"><spring:message code="prop.course.teaching.survey.analysis.course.teaching.items"/></td>
								<td colspan="6" align="center"><div align="center"><spring:message code="prop.course.teaching.survey.analysis.response.number"/></div></td>
								<td rowspan="2"><spring:message code="prop.course.teaching.survey.analysis.response.percentage"/></td>
								<td colspan="4" align="center"><div align="center"><spring:message code="prop.course.teaching.survey.analysis.mean"/></div></td>
							</tr>
							<tr>
								<td><spring:message code="prop.course.teaching.survey.analysis.disagree.strong"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.disagree"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.agree"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.agree.strong"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.applicable.not"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.total"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.section"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.course"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.department"/></td>
								<td><spring:message code="prop.course.teaching.survey.analysis.college"/></td>
							</tr>
						</thead>
							<tr class="success">
							<td>&nbsp;</td>
				                <td colspan="12" style="${alnright}"><spring:message code="prop.course.teaching.survey.analysis.course.items"/></td>
							</tr>
							
							<c:set var="rowCount" value="${0}"/>
							<c:forEach items="${survey.surveyResponses}" var="resp"  >
								<c:forEach items="${resp.analysisList}" var="analysis" varStatus="theCount">
										<c:if test="${(analysis.question == 'Q2') || (analysis.question == 'Q14') || analysis.question == questionByYear}">
												<tr>
													<td>${analysis.question}</td>
													<td style="${alnright}"><spring:message code="prop.course.teaching.survey.analysis.question${analysis.questionLabel}"/></td>
													<td class="active">${analysis.strongDisagree}</td>
													<td>${analysis.disAgree}</td>
													<td class="active">${analysis.agree}</td>
													<td>${analysis.strongAgree}</td>
													<td class="active">${analysis.notApplicable}</td>
													<td>${analysis.total}</td>
													<td class="active">${analysis.percentageResponse}</td>
													<td>${analysis.sectionMean}</td>
													<td class="active">${analysis.courseMean}</td>
													<td>${analysis.departmentMean}</td>
													<td class="active">${analysis.collegeMean}</td>
												</tr>
												<c:set var="cTotal" value="${cTotal + analysis.total}"/>
												<c:set var="cPctVal" value="${cPctVal + analysis.percentageResponse}"/>
												<c:set var="cSectionMean" value="${cSectionMean + analysis.sectionMean}"/>
												<c:set var="cCourseMean" value="${cCourseMean + analysis.courseMean}"/>
												<c:set var="cDepart" value="${cDepart + analysis.departmentMean}"/>
												<c:set var="cCollege" value="${cCollege + analysis.collegeMean}"/>
												<c:set var="rowCount" value="${rowCount + 1}"/>
										</c:if>
								</c:forEach>
	
							</c:forEach>
							
							<tr class="danger">
							<td>&nbsp;</td>
				                <td  style="${alnright}"><b><spring:message code="prop.course.teaching.survey.analysis.summary"/></b></td>
				                <td colspan="5">&nbsp;</td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="0"  value="${cTotal}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${cPctVal/rowCount}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${cSectionMean/rowCount}" /></b></td>               
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${cCourseMean/rowCount}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${cDepart/rowCount}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${cCollege/rowCount}" /></b></td>
							</tr>
				
							<tr >
				                <td colspan="13">&nbsp;</td>
							</tr>
							
							<tr class="success">
								<td>&nbsp;</td>
				                <td colspan="12" style="${alnright}" ><spring:message code="prop.course.teaching.survey.analysis.teaching.items"/></td>
							</tr>
							<c:set var="rowCount" value="${0}"/>
							<c:forEach items="${survey.surveyResponses}" var="resp">
								<c:forEach items="${resp.analysisList}" var="analysis" varStatus="theCount">
										<c:if test="${!((analysis.question == 'Q2') || (analysis.question == 'Q14') || (analysis.question == questionByYear))}">
												<tr>
													<td >${analysis.question}</td>
													<td style="${alnright}"><spring:message code="prop.course.teaching.survey.analysis.question${analysis.questionLabel}"/></td>
													<td class="active">${analysis.strongDisagree}</td>
													<td>${analysis.disAgree}</td>
													<td class="active">${analysis.agree}</td>
													<td>${analysis.strongAgree}</td>
													<td class="active">${analysis.notApplicable}</td>
													<td>${analysis.total}</td>
													<td class="active">${analysis.percentageResponse}</td>
													<td>${analysis.sectionMean}</td>
													<td class="active">${analysis.courseMean}</td>
													<td>${analysis.departmentMean}</td>
													<td class="active">${analysis.collegeMean}</td>
												</tr>
												<c:set var="tTotal" value="${tTotal + analysis.total}"/>
												<c:set var="tPctVal" value="${tPctVal + analysis.percentageResponse}"/>
												<c:set var="tSectionMean" value="${tSectionMean + analysis.sectionMean}"/>
												<c:set var="tCourseMean" value="${tCourseMean + analysis.courseMean}"/>
												<c:set var="tDepart" value="${tDepart + analysis.departmentMean}"/>
												<c:set var="tCollege" value="${tCollege + analysis.collegeMean}"/>
												<c:set var="rowCount" value="${rowCount + 1}"/>
										</c:if>
								</c:forEach>
							</c:forEach>
							
							<tr class="danger">
							<td>&nbsp;</td>
				                <td  style="${alnright}"><b><spring:message code="prop.course.teaching.survey.analysis.summary"/></b></td>
				                <td colspan="5">&nbsp;</td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="0"  value="${tTotal}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${tPctVal/rowCount}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${tSectionMean/rowCount}" /></b></td>               
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${tCourseMean/rowCount}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${tDepart/rowCount}" /></b></td>
				                <td align="center"><b><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"  value="${tCollege/rowCount}" /></b></td>
							</tr>
				
							
					 </table>
				</div>
				
				<p>
					<c:if test="${not empty survey.message}">
						<div class="alert alert-danger" role="alert">
						<b>
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							${survey.message}
						</b></div>
					</c:if>
				</p>
				<p>
					<ul>
					  <li><spring:message code="prop.course.teaching.survey.analysis.note1"/></li>
					  <li><spring:message code="prop.course.teaching.survey.analysis.note2"/></li>
					</ul>
				</p>

</c:if>



