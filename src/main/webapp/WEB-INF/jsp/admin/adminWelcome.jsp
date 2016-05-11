<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	adminWelcome.jsp
 * 
 * Date of Creation		:	31-Aug-2015
 *  
 * Summary				:	Welcome page for Commitee members, HODs and Deans 
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
<%-- <%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %> --%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- link href="http://externalcdn.com/respond-proxy.html" id="respond-proxy" rel="respond-proxy" /-->


<%@include file="../ui/cssWelcome.jsp" %>
<%@include file="../ui/jsCode.jsp" %>

<c:url value="/AccessVisibilitySurveyServlet" var="servletSurveyAccess"/>

 <script type="text/javascript">

            $(document).ready(function () {  
                       
                // Create a jqxDateTimeInput
                $("#dateCommittee").jqxDateTimeInput({ width: '100px', height: '25px' });
               
                
                 $("#dateFaculty").jqxDateTimeInput({ width: '100px', height: '25px' });

                 
                <c:if test="${viewRights.booViewCommitteeSurvey}">
                 	$('#chkSurveyVisibleCommittee').bootstrapToggle('on');
                 	var dtCommunity = new Date("${viewRights.dateCommitteeView}");
                 	$('#dateCommittee ').jqxDateTimeInput('setDate', dtCommunity);
                </c:if>
                 <c:if test="${viewRights.booViewOtherSurvey}">
                 	$('#chkSurveyVisibleFaculty').bootstrapToggle('on');
                 	var dtFaculty = new Date("${viewRights.dateOthersView}");
                 	$('#dateFaculty').jqxDateTimeInput('setDate', dtFaculty);
                 </c:if>
                 
                 
                 
                 if ($('#chkSurveyVisibleCommittee').prop('checked'))
                 {
                 	$('#dateCommittee').show();
                 }
                 else
                 {
                 	$('#dateCommittee').hide();
                 }

                 if ($('#chkSurveyVisibleFaculty').prop('checked'))
                 {
                 	$('#dateFaculty').show();
                 }
                 else
                 {
                 	$('#dateFaculty').hide();
                 }
                 
                 
            });
            
            
 		$(function () {   
	 		var urlAccess	= '${servletSurveyAccess}';
	 		var chkSurveyVisibleCommittee="na";
	 		var chkSurveyVisibleFaculty="na";
	 		var dateCommittee="na";
	 		var dateFaculty="na";
	 		
 		       
           $('#chkSurveyVisibleCommittee').change(function() {
           		if ($('#chkSurveyVisibleCommittee').prop('checked'))
                 {
                 	$('#dateCommittee').show();
                 	chkSurveyVisibleCommittee = "on";
                 	dateCommittee= $("#dateCommittee").val();
                 	
                 }
                 else
                 {
                 	$('#dateCommittee').hide();
                 	chkSurveyVisibleCommittee = "off";
                 	dateCommittee="na"
                 }
                  ajaxSurveyAccess(urlAccess,chkSurveyVisibleCommittee,dateCommittee,chkSurveyVisibleFaculty,dateFaculty);
           }); 
           
           $('#chkSurveyVisibleFaculty').change(function() {
           
           		if ($('#chkSurveyVisibleFaculty').prop('checked'))
                 {
                 	$('#dateFaculty').show();
                 	chkSurveyVisibleFaculty = "on";
                 	dateFaculty = $("#dateFaculty").val();
                 }
                 else
                 {
                 	$('#dateFaculty').hide();
                 	chkSurveyVisibleFaculty = "off";
                 	dateFaculty = "na";
                 }
					ajaxSurveyAccess(urlAccess,chkSurveyVisibleCommittee,dateCommittee,chkSurveyVisibleFaculty,dateFaculty);           
			});
			
			
			 $('#dateCommittee').on('close', function (event) {
                dateCommittee= $("#dateCommittee").val();
                chkSurveyVisibleCommittee = "on";
                ajaxSurveyAccess(urlAccess,chkSurveyVisibleCommittee,dateCommittee,chkSurveyVisibleFaculty,dateFaculty);
            });
			 
			$('#dateFaculty').on('close', function (event) {
                dateFaculty= $("#dateFaculty").val();
                chkSurveyVisibleFaculty = "on";
                ajaxSurveyAccess(urlAccess,chkSurveyVisibleCommittee,dateCommittee,chkSurveyVisibleFaculty,dateFaculty);
            });
			 
		});
           
                     
        
   
   function ajaxSurveyAccess(urlAccess,chkSurveyVisibleCommittee,dateCommittee,chkSurveyVisibleFaculty,dateFaculty)
   {
   		           	
           $.ajax({
			url : urlAccess,
			data: { chkSurveyVisibleCommittee: chkSurveyVisibleCommittee, dateCommittee: dateCommittee, chkSurveyVisibleFaculty:chkSurveyVisibleFaculty, dateFaculty:dateFaculty, yrSem:"${yrSem}"},
			type : "GET",
			success : function(data) {
				
			},
			error : function(data) {
				
			}
			});
   }
            
        </script>

<portlet:renderURL var="urlAnalysisStart">
	<portlet:param name="action" value="analysisDataTransfer"/>
</portlet:renderURL>

<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title"><spring:message code="prop.course.teaching.survey.title"/></h3>
	  </div>
	  <div class="panel-body">
	  <div class="row">
	   	
	   	 <div class="col-xs-5">
		    <div class="thumbnail">
		      <div class="caption">
		      	
			    <div class="col-xs-1"><h1> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span></h1></div>
			    <div class="col-xs-7"><h3><spring:message code="prop.course.teaching.survey.committee.members"/></h3></div>
		        
		        <table class="table table-boardered">
		        	<thead>
		        		<tr>
		        			<th><spring:message code="prop.course.teaching.survey.committee.member.number"/> <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span></th>
		        			<th><spring:message code="prop.course.teaching.survey.committee.member.name"/> <span class="glyphicon glyphicon-user" aria-hidden="true"></span></th>
		        		</tr>
		        	</thead>
		        	<tbody>
		        		<c:forEach items="${committeeMembers}" var="member">
		        				<tr>
		        					<td>${member.empNo}</td>
		        					<td>
		        						<div>${member.empName} &nbsp; 
		        						<c:if test="${member.role == 'admin'}">
		        							<font color="red">
		        								<span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
		        							</font>
		        						</c:if>
		        						</div>
		        					</td>
		        				</tr>
		        		</c:forEach>
		        	</tbody>
		        </table>
	
		      </div>
		    </div>
		  </div>
		  
			<div class="col-xs-5">
				<div class="thumbnail">
					<div class="caption">
							<div class="col-xs-1"><h1> <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span></h1></div>
							<div class="col-xs-7"><h3><spring:message code="prop.course.teaching.survey.committee.member.control"/></h3></div>
							
							<div class="clearfix"></div>
							
							<div>
								<spring:message code="prop.course.teaching.survey.committee.member.control.survey.week" />	
							</div>
							
							
							<div class="clearfix"></div>
							
							<p>&nbsp;</p>						
							<div class="well col-xs-11">
								<div>
									<div class="col-xs-12"><spring:message code="prop.course.teaching.survey.current.semester" arguments="${yrSem}"/>  </div>
								</div>
							
								<div class="col-xs-12">
						        	<div class="col-xs-6"><spring:message code="prop.course.teaching.survey.committee.member.control.survey.visible.committee" />  </div>
						        	<div class="col-xs-2"><input id="chkSurveyVisibleCommittee" type="checkbox" data-toggle="toggle" data-on='<spring:message code="prop.course.teaching.survey.committee.member.button.yes"/>' data-off='<spring:message code="prop.course.teaching.survey.committee.member.button.no"/>' data-onstyle="success" data-offstyle="danger"></div>
						        	<div  class="col-xs-4"> <div id='dateCommittee'></div> </div>
					        	</div>
								<div class="col-xs-12">&nbsp;</div>
								<div class="col-xs-12">
						        	<div class="col-xs-6"><spring:message code="prop.course.teaching.survey.committee.member.control.survey.visible.faculty" />  </div>
						        	<div class="col-xs-2"><input id="chkSurveyVisibleFaculty" type="checkbox" data-toggle="toggle" data-on='<spring:message code="prop.course.teaching.survey.committee.member.button.yes"/>' data-off='<spring:message code="prop.course.teaching.survey.committee.member.button.no"/>' data-onstyle="success" data-offstyle="danger"></div>
						        	<div  class="col-xs-4"><div id='dateFaculty'></div></div>
					        	</div>
					        	
				   		 	</div>
						
						<p>&nbsp;</p>
						
						<a href="${urlAnalysisStart}">Test Analysis</a>
						
				    </div>
			   </div>
			</div>
		  
		  
		</div>
	  
	  
	  </div>
	  
	  
	  
	  
	  
</div>

