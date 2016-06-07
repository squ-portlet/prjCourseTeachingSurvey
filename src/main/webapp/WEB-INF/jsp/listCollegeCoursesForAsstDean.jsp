<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	listCollegeCoursesForAsstDean.jsp
 * 
 * Date of Creation		:	31-Aug-2015
 *  
 * Summary				:	List of College Courses with faculties took part in survey.
                            This page is for Assistant Dean.
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
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- link href="http://externalcdn.com/respond-proxy.html" id="respond-proxy" rel="respond-proxy" /-->


<%@include file="ui/cssWelcome.jsp" %>
<%@include file="ui/jsCode.jsp" %>

  <script type="text/javascript">
      
        $(document).ready(function () {
            var courses = ${colCourseList};
            // prepare the data
            var source =
            {
                dataType: "json",
                dataFields: [
                    { name: 'departmentCode', type: 'string'},
                    { name: 'departmentName', type: 'string' },
                    { name: 'courseCode', type: 'string' },
                    { name: 'sectionNo', type: 'string' },
                    { name: 'empNumber', type: 'string' },
                    { name: 'empName', type: 'string' },
                   	{ name: 'seatsTaken', type: 'number' }, 
                   	{ name: 'studentResponse', type: 'number' }, 
                    { name: 'includeExclude', type: 'string' },
                    { name: 'children', type: 'array' }
                ],
                hierarchy:
                {
                    root: 'children'
                },
                id: 'departmentCode',
                localData: courses
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            // create Tree Grid
            $("#treeGrid").jqxTreeGrid(
            {
               	<c:if test="${rc.locale.language == 'ar'}">
            	 rtl:true,
            	</c:if>
                width: 1150,
                height: 400,
                source: dataAdapter,
                sortable: true,
                columnsResize: true,
                //filterable: true,
                pageable: true,
                //pagerMode: 'advanced',
                pageSizeMode: 'root',
                pageSize: 7,
                pageSizeOptions: ['4','5','6','7','8','10','20','50','100'],
               
                columns: [
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.analysis.department"/>', dataField: 'departmentName', width: 200, align: '${colAlign}', cellsAlign:'${cellsAlign}',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-home" aria-hidden="true"></span></div>';
                      }
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.course"/>', dataField: 'courseCode', width: 120, align: '${colAlign}', cellsAlign: 'right',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span></div>';
                      }
                  	},
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.section"/>', dataField: 'sectionNo', width: 120, align: '${colAlign}', cellsAlign: 'right',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-tag" aria-hidden="true"></span></div>';
                      } 
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.committee.member.number"/>', dataField: 'empNumber', width: 120, align: '${colAlign}', cellsAlign: 'right',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span></div>';
                      } 
                   },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.committee.member.name"/>', dataField: 'empName', width: 200, align: '${colAlign}', cellsAlign:'${cellsAlign}',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>';
                      } 
                   },
                  { 
                    text: '<spring:message code="prop.course.teaching.survey.seats.taken"/>', dataField: 'seatsTaken', width: 120, align: 'right',cellsAlign: 'right', 
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span></div>';
                      } 
                  },
                  { 
                  
                  	text: '<spring:message code="prop.course.teaching.survey.response.students"/>', dataField: 'studentResponse', width: 150, align: 'right', cellsAlign: 'right',
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-volume-down" aria-hidden="true"></span></div>';
                      } 
                  	 
                  },
                  { 
                  	text: '<spring:message code="prop.course.teaching.survey.include.exclude"/>', dataField: 'includeExclude', width: 120,
                  	renderer: function (text, align, height) {
                         return '<div align="'+align+'">'+text+'  <span class="glyphicon glyphicon-flag" aria-hidden="true"></span></div>';
                      } 
                  }
                ]
            });
            
            $("#excelExport").jqxButton();
             $("#excelExport").click(function () {
                $("#treeGrid").jqxTreeGrid('exportData', 'xls');
            });
            
            $("#print").jqxButton({ width: 80 });
         
           $("#print").click(function () {
                var gridContent = $("#treeGrid").jqxTreeGrid('exportData', 'html');
                var newWindow = window.open('', '', 'width=800, height=500'),
                document = newWindow.document.open(),
                pageContent =
                    '<!DOCTYPE html>' +
                    '<html>' +
                    '<head>' +
                    '<meta charset="utf-8" />' +
                    '<title>College Course List - for Asst. Dean</title>' +
                    '</head>' +
                    '<body>' + gridContent + '</body></html>';
                document.write(pageContent);
                document.close();
                newWindow.print();
            });
            
            
            //$("#treeGrid").jqxTreeGrid('setColumnProperty', 'departmentName', 'align', 'right');
            
            
        });
        
        
    </script>


    <div id="treeGrid"></div>
    
   <portlet:resourceURL id="excelCollegeCoursesForAsstDean" var="urlExcelCollegeCoursesForAsstDean"></portlet:resourceURL> 
	 <div style='float: ${cellsAlign};'>
	     <a class="btn btn-default" href="${urlExcelCollegeCoursesForAsstDean}">
	     		<div dir="${varDirection}">
	     			<spring:message code="prop.course.teaching.survey.export.excel"/> 
	     			<span class="glyphicon glyphicon-export" aria-hidden="true"></span>
	     		</div>
	     </a>
     </div>

