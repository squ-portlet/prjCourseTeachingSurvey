<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	surveyReportSummaryNotValid.jsp
 * 
 * Date of Creation		:	03-Sep-2015
 *  
 * Summary				:	valid survey report summary result for list of faculties 
                            of a particular semester
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


<script type="text/javascript">
        	var dataMap	=	{};
        	<c:forEach	items="${notValidSurveyReport}" var="report">
        			<portlet:renderURL var="urlSurveyAnalysis" >
						<portlet:param name="action" value="surveyAnalysis"/>
						<portlet:param name="empNumber" value="${report.empNumber}"/>
						<portlet:param name="courseCode" value="${report.courseCode}"/>
						<portlet:param name="semesterCode" value="${report.yearSemester}"/>
						<portlet:param name="sectionNo" value="${report.sectionNo}"/>
					</portlet:renderURL>
				dataMap['${report.empNumber}']= {url : '${urlSurveyAnalysis}'};
        	</c:forEach>

        $(document).ready(function () {
        
            var reportData = ${notValidSurveyReportJSON};
            // prepare the data
            var source =
            {
                dataType: "json",
                dataFields: [
                    { name: 'empNumber', type: 'string' },
                    { name: 'empName', type: 'string' },
                    { name: 'collegeCode', type: 'string' },
                    { name: 'departmentName', type: 'string' },
                    { name: 'courseCode', type: 'string' },
                    { name: 'sectionNo', type: 'string' },
                    { name: 'registeredStudent', type: 'number' },
                    { name: 'studentNoResponse', type: 'number' },
                    { name: 'teachingMean', type: 'number' },
                    { name: 'teachingPercentageFavor', type: 'number' },
                    { name: 'questionMean', type: 'number' },
                    { name: 'questionPercentageFavor', type: 'number' }
                ],
                localData: reportData
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            $("#dataTable").jqxDataTable(
            {
                <c:if test="${rc.locale.language == 'ar'}">
            	 rtl:true,
            	</c:if>
            	
                width: 1400,
                height: 500,
               //  columnsheight: 60,
                pageable: true,
                pagerMode: 'advanced',
                altRows: true,
                filterable: true,
                sortable: true,
                pagerButtonsCount: 10,
                pageSizeOptions: ['10','20','50','100'],
                source: dataAdapter,
                columnsResize: true,
                columns: [

                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.instructor.id"/>', dataField: 'empNumber', width:80, align: '${colAlign}', cellsAlign: 'right',
                  	cellsRenderer: function (row, column, value, rowData) {
                  	     var aref01 = "<a href='";
                  	     var alink =dataMap[value].url;
                  	     var aref02 = "'>"
                  	     var adata  = value;
                  	     var aref03="</a>";
                  	     
                  	     var aref = aref01+alink+aref02+adata+aref03;
                         return aref;
                      }, 
                      renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span></div>';
                      } 
                  	
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.instructor.name"/>', dataField: 'empName',width:200,align: '${colAlign}',cellsAlign:'${cellsAlign}',
                  	renderer : function(text,align,height)
                  	{
                  		return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>';
                  	} 
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.college"/>', dataField: 'collegeCode', width:200, align: '${colAlign}',cellsAlign:'${cellsAlign}',
                  	renderer : function(text,align,height)
                  	 { 
                  	  return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-home" aria-hidden="true"></span></div>';
                  	 }
                  },
                <c:if test='${staffRole.staffRole != "HOD"}'>
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.department"/>', dataField: 'departmentName',width:200,align: '${colAlign}', cellsAlign:'${cellsAlign}',
                  	renderer : function(text,align,height)
                  	{
                  		return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-home" aria-hidden="true"></span></div>';	
                  	}
                  	  
                  },
                </c:if>
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.course.code"/>', dataField: 'courseCode',width:80,align: '${colAlign}',cellsAlign:'${cellsAlign}',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span></div>';
                      }  
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.section"/>', dataField: 'sectionNo',width:80, cellsAlign: 'right',align: '${colAlign}',cellsAlign:'${cellsAlign}',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-tag" aria-hidden="true"></span></div>';
                      }  
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.student.registered"/>', dataField: 'registeredStudent', width:80, align: 'right',cellsAlign: 'right',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span></div>';
                      }  
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.response.number"/>', dataField: 'studentNoResponse',width:80,align: 'right', cellsAlign: 'right',
                  	 renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-volume-down" aria-hidden="true"></span></div>';
                      }  
                  },
                  { text: '<spring:message code="prop.course.teaching.survey.analysis.mean"/>', dataField: 'teachingMean',columngroup: 'teachingItem',width:100,align: 'right', cellsAlign: 'right' },
                  { text: '<spring:message code="prop.course.teaching.survey.analysis.response.percentage"/>', dataField: 'teachingPercentageFavor',columngroup: 'teachingItem',width:80,align: 'right', cellsAlign: 'right' },
                  { text: '<spring:message code="prop.course.teaching.survey.analysis.mean"/>', dataField: 'questionMean',columngroup: '${statement}',width:100,align: 'right', cellsAlign: 'right' },
                  { text: '<spring:message code="prop.course.teaching.survey.analysis.response.percentage"/>', dataField: 'questionPercentageFavor',columngroup: '${statement}',width:80,align: 'right', cellsAlign: 'right' }
              ],
              columnGroups: 
                [
                	{ text: '<spring:message code="prop.course.teaching.survey.analysis.teaching.items"/>', align: 'center', name: 'teachingItem' },
                	{ text: '${statement}', align: 'center', name: '${statement}' }
                ]
            });
            

        });
    </script>
    
    <div id="dataTable"></div>
	<portlet:renderURL var="urlSurveyAnalysis" >
		<portlet:param name="action" value="surveyAnalysis"/>
		<portlet:param name="empNumber" value="${report.empNumber}"/>
		<portlet:param name="courseCode" value="${report.courseCode}"/>
		<portlet:param name="semesterCode" value="${report.yearSemester}"/>
		<portlet:param name="sectionNo" value="${report.sectionNo}"/>
	</portlet:renderURL>
