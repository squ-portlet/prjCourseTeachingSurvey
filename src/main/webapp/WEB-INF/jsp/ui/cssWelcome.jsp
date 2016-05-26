<!--  
 * Project 				:	prjCourseTeachingSurvey
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	cssWelcome.jsp
 * 
 * Date of Creation		:	17-Aug-2015
 *  
 * Summary				:	cssWelcome
 *
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

-->
<!-- UI side validation from http://bootstrapvalidator.com/ -->
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

<spring:message code="url.squ.cdn" var="urlCdn"/>

<!-- **** Bootstrap Glyphicons Halflings **** -->


<c:url value="${urlCdn}/bootstrap/3.1.1/dist/fonts/glyphicons-halflings-regular.eot" var="urlGlyphRegularEot"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/dist/fonts/glyphicons-halflings-regular.woff" var="urlGlyphregularWoff"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/dist/fonts/glyphicons-halflings-regular.ttf" var="urlGlyphregularTtf"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/dist/fonts/glyphicons-halflings-regular.svg" var="urlGlyphregularSvg"/>

<!-- **** Image - icons **** -->

<c:url value="/ui/gen/img/reload.gif" var="urlImgAjaxReload"/>
<c:url value="/ui/bootstrap-3.3.1/images/calendar52.png" var="urlImgGlyphLeaveAnnual"/>
<c:url value="/ui/bootstrap-3.3.1/images/university2.png" var="urlImgGlyphScholarship"/>
<c:url value="/ui/bootstrap-3.3.1/images/glyphicons_190_circle_plus.png" var="urlImgGlyphAdd"/>
<c:url value="/ui/bootstrap-3.3.1/images/glyphicons_191_circle_minus.png" var="urlImgGlyphMinus"/>
<c:url value="/ui/bootstrap-3.3.1/images/glyphicons_150_edit.png" var="urlImgGlyphUpdate"/>
<c:url value="/ui/bootstrap-3.3.1/images/glyphicons_388_exit.png" var="urlImgGlyphExit"/>
<c:url value="/ui/bootstrap-3.3.1/images/glyphicons_256_delete.png" var="urlImgGlyphDelete"/>
<c:url value="/ui/bootstrap-3.3.1/images/open182.png" var="urlImgGlyphBook"/>
<c:url value="/ui/bootstrap-3.3.1/images/glyphicons_054_clock.png" var="urlImgGlyphClock"/>
<c:url value="/ui/bootstrap-3.3.1/images/Training-24.png" var="urlImgGlyphTraining"/>
<c:url value="/ui/bootstrap-3.3.1/images/flatIcon_dollar178.png" var="urlImgGlyphDollar"/>
<c:url value="/ui/bootstrap-3.3.1/images/glyphicons_308_share_alt.png" var="urlImgGlyphShare"/>

<!-- ******************** CSS Declaration************************ --> 
<c:url value="${urlCdn}/bootstrap/3.1.1/dist/css/bootstrap.css" var="urlCssBootstrap"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/dist/css/bootstrap.min.css" var="urlCssBootstrapMin"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/dist/css/bootstrap-theme.min.css" var="urlCssBootstrapThemeMin"/>
<c:url value="/ui/gen/css/normalize.css" var="urlCssNormalize"/>
<c:url value="${urlCdn}/jqueryui/1.11.4/jquery-ui.css" var="urlCssJQueryUiCustom"/>

<c:url value="/ui/gen/css/squ_custom_css.css" var="urlCssSquCustomCss"/>

<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/dist/css/bootstrap-arabic.css" var="urlCssBootstrapArabic"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/dist/css/bootstrap-arabic-theme.css" var="urlCssBootstrapArabicTheme"/>

	<!-- ------------ jqwidget css declaration -->
<c:url value="${urlCdn}/jqwidgets/3.8.2/css/jqx.base.css" var="urlCssJqxBase"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/css/jqx.bootstrap.css" var="urlCssJqxBootStrap"/>


<!-- ------------ bootstrap toggle css declaration -->
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css" var="urlCssBootStrapToggle"/>

<!-- ******************** JS Declaration************************ -->

<c:url value="${urlCdn}/jquery/1.11.3/jquery-1.11.3.min.js" var="urlJsJqueryMin"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/dist/js/bootstrap.min.js" var="urlJsBootStrapMin"/>
<c:url value="/ui/gen/js/html5shiv.js" var="urlJsHtml5shiv"/>
<c:url value="/ui/gen/js/respond.min.js" var="urlJsRespond"/>
<c:url value="/ui/gen/js/css3-mediaqueries.js" var="urlJsCss3MediaQueries"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/js/tooltip.js" var="urlJsBSToolTip"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/js/popover.js" var="urlJsBSPopover"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/js/tab.js" var="urlJsBSTab"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/js/collapse.js" var="urlJsBSCollapse"/>
<c:url value="${urlCdn}/bootstrap/3.1.1/js/transition.js" var="urlJsBSTransition"/>
<c:url value="${urlCdn}/jqueryui/1.11.4/jquery-ui.min.js" var="urlJsJqueryUIMin"/>



<c:url value="${urlCdn}/jqueryvalidation/1.13.1/jquery.validate.min.js" var="urlJsValidatorJquery"/>
<c:url value="${urlCdn}/jqueryvalidation/1.13.1/additional-methods.min.js" var="urlJsValidatorJqueryAddl"/>

<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/dist/js/bootstrap-arabic.js" var="urlJsBootstrapArabic"/>

	<!-- ------------ jqwidget js declaration -->
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxcore.js" var="urlJsJqxCore"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxbuttons.js" var="urlJsJqxButtons"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdata.js" var="urlJsJqxData"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdatatable.js" var="urlJsJqxDataTable"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxscrollbar.js" var="urlJsJqxScrollbar"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxtreegrid.js" var="urlJsJqxTreeGrid"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxlistbox.js" var="urlJsJqxlistbox"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdropdownlist.js" var="urlJsJqxdropdownlist"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdata.export.js" var="urlJsJqxDataExport"/>

<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.columnsreorder.js" var="urlJsJqxGridColumnsreorder"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.columnsresize.js" var="urlJsJqxGridColumnsresize"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.edit.js" var="urlJsJqxGridEdit"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.filter.js" var="urlJsJqxGridFilter"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.grouping.js" var="urlJsJqxGridGrouping"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.js" var="urlJsJqxGrid"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.pager.js" var="urlJsJqxGridPager"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.selection.js" var="urlJsJqxGridSelection"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.sort.js" var="urlJsJqxGridSort"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxmenu.js" var="urlJsJqxMenu"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdocking.js" var="urlJsJqxDocking"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxpanel.js" var="urlJsJqxPanel"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxsplitter.js" var="urlJsJqxSplitter"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxtabs.js" var="urlJsJqxTabs"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxtooltip.js" var="urlJsJqxToolTip"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxwindow.js" var="urlJsJqxWindow"/>

<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdatetimeinput.js" var="urlJsJqxDateTimeInput"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxcalendar.js" var="urlJsJqxCalendar"/>


<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.js" var="urlJsJqxLocaleGlobal"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.culture.ar.js" var="urlJsJqxLocaleAr"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.culture.ar.js" var="urlJsJqxLocaleAr"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.culture.ar-OM.js" var="urlJsJqxLocaleArOm"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js" var="urlJsBootstrapToggle"/>




<!-- ******************** CSS implementation************************ --> 
<c:if test="${rc.locale.language == 'en'}" > 
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrap}" />
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrapThemeMin}" />
</c:if>
<c:if test="${rc.locale.language == 'ar'}" >
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrapArabic}" />
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrapArabicTheme}" />
</c:if>
 <!-- link rel="stylesheet" type="text/css" href="${urlCssBootstrapMin}" /-->

 <link rel="stylesheet" type="text/css" href="${urlCssNormalize}" />
 <link rel="stylesheet" type="text/css" href="${urlCssJQueryUiCustom}" />
 <link rel="stylesheet" type="text/css" href="${urlCssSquCustomCss}" />
	<!-- ------------ jqwidget css implementation --> 
 <link rel="stylesheet" type="text/css" href="${urlCssJqxBase}" />


 <!-- ------------ bootstrap toggle css implementation -->
  <link rel="stylesheet" type="text/css" href="${urlCssBootStrapToggle}" />

<!-- ******************** JS implementation************************ --> 
 <script type="text/javascript" src="${urlJsJqueryMin}"></script>
 <c:if test="${rc.locale.language == 'en'}" > 
 	<script type="text/javascript" src="${urlJsBootStrapMin}"></script>
 </c:if>
 <c:if test="${rc.locale.language == 'ar'}" >
 	<script type="text/javascript" src="${urlJsBootstrapArabic}"></script> 
 </c:if>
 <script type="text/javascript" src="${urlJsBSToolTip}"></script>
 <script type="text/javascript" src="${urlJsBSPopover}"></script>
 <script type="text/javascript" src="${urlJsBSCollapse}"></script>
  <script type="text/javascript" src="${urlJsBSTransition}"></script>
 <script type="text/javascript" src="${urlJsJqueryUIMin}"></script>



<script type="text/javascript" src="${urlJsValidatorJquery}"></script>
<script type="text/javascript" src="${urlJsValidatorJqueryAddl}"></script>

	<!-- ------------ jqwidget js implementation -->
<script type="text/javascript" src="${urlJsJqxCore}"></script>
<script type="text/javascript" src="${urlJsJqxButtons}"></script>
<script type="text/javascript" src="${urlJsJqxData}"></script>
<script type="text/javascript" src="${urlJsJqxDataTable}"></script>
<script type="text/javascript" src="${urlJsJqxScrollbar}"></script>
<script type="text/javascript" src="${urlJsJqxTreeGrid}"></script>
<script type="text/javascript" src="${urlJsJqxlistbox}"></script>
<script type="text/javascript" src="${urlJsJqxdropdownlist}"></script>
<script type="text/javascript" src="${urlJsJqxDataExport}"></script>

<script type="text/javascript" src="${urlJsJqxGridColumnsreorder}"></script>
<script type="text/javascript" src="${urlJsJqxGridColumnsresize}"></script>
<script type="text/javascript" src="${urlJsJqxGridEdit}"></script>
<script type="text/javascript" src="${urlJsJqxGridFilter}"></script>
<script type="text/javascript" src="${urlJsJqxGridGrouping}"></script>
<script type="text/javascript" src="${urlJsJqxGrid}"></script>
<script type="text/javascript" src="${urlJsJqxGridPager}"></script>
<script type="text/javascript" src="${urlJsJqxGridSelection}"></script>
<script type="text/javascript" src="${urlJsJqxGridSort}"></script>
<script type="text/javascript" src="${urlJsJqxMenu}"></script>

<script type="text/javascript" src="${urlJsJqxDocking}"></script>
<script type="text/javascript" src="${urlJsJqxPanel}"></script>
<script type="text/javascript" src="${urlJsJqxSplitter}"></script>
<script type="text/javascript" src="${urlJsJqxTabs}"></script>
<script type="text/javascript" src="${urlJsJqxToolTip}"></script>
<script type="text/javascript" src="${urlJsJqxWindow}"></script>

<script type="text/javascript" src="${urlJsJqxDateTimeInput}"></script>
<script type="text/javascript" src="${urlJsJqxCalendar}"></script>

<c:if test="${rc.locale.language == 'ar'}">
	<script type="text/javascript" src="${urlJsJqxLocaleGlobal}"></script>
	<script type="text/javascript" src="${urlJsJqxLocaleAr}"></script>
	<script type="text/javascript" src="${urlJsJqxLocaleArOm}"></script>
</c:if>

<script type="text/javascript" src="${urlJsBootstrapToggle}"></script>

<%--  <script type="text/javascript" src="${urlJsBSTab}"></script> --%>
     <!--[if lt IE 9]>
     <link href="http://externalcdn.com/respond-proxy.html" id="respond-proxy" rel="respond-proxy" />
      <script type="text/javascript" src="${urlJsHtml5shiv}"></script>
      <script type="text/javascript" src="${urlJsRespond}"></script>
      <script type="text/javascript" src="${urlJsCss3MediaQueries}"></script>
    <![endif]-->
    
<style>
 .portlet-body
{
	overflow: auto;
} 


/*********************************************/
</style>

<script>

    var getLocalization = function () {
                var localizationobj = {};
                //localizationobj.pagerGoToPageString = "اذهب:";
                localizationobj.pagerGoToPageString='<spring:message code="localizationobj.pagerGoToPageString"/>';
                localizationobj.pagerShowRowsString = '<spring:message code="localizationobj.pagerShowRowsString"/>';
                localizationobj.pagerRangeString = ' <spring:message code="localizationobj.pagerRangeString"/> ';
                localizationobj.pagerNextButtonString = '<spring:message code="localizationobj.pagerNextButtonString"/>';
                localizationobj.pagerFirstButtonString = '<spring:message code="localizationobj.pagerFirstButtonString"/>';
                localizationobj.pagerLastButtonString = '<spring:message code="localizationobj.pagerLastButtonString"/>';
                localizationobj.pagerPreviousButtonString = '<spring:message code="localizationobj.pagerPreviousButtonString"/>';
                localizationobj.sortAscendingString = '<spring:message code="localizationobj.sortAscendingString"/>';
                localizationobj.sortDescendingString = '<spring:message code="localizationobj.sortDescendingString"/>';
                localizationobj.sortRemoveString = '<spring:message code="localizationobj.sortRemoveString"/>';
                localizationobj.emptydatastring = '<spring:message code="localizationobj.emptydatastring"/>';
                localizationobj.firstDay = 1;
                localizationobj.percentSymbol = "%";
                localizationobj.currencySymbol = "€";
                localizationobj.currencySymbolPosition = '<spring:message code="localizationobj.currencySymbolPosition"/>';
                localizationobj.decimalSeparator = ".";
                localizationobj.thousandsSeparator = ",";
                var days = {
                    // full day names
                    names: [
		                    "Sonntag", 
		                    "Montag", 
		                    "Dienstag", 
		                    "Mittwoch", 
		                    "Donnerstag", 
		                    "Freitag", 
		                    "Samstag"
		                    ],
                    // abbreviated day names
                    namesAbbr: [
	                    		"Sonn", 
	                    		"Mon", 
	                    		"Dien", 
	                    		"Mitt", 
	                    		"Donn", 
	                    		"Fre", 
	                    		"Sams"
                    		],
                    // shortest day names
                    namesShort: [
	                    		"So", 
	                    		"Mo", 
	                    		"Di", 
	                    		"Mi", 
	                    		"Do", 
	                    		"Fr", 
	                    		"Sa"
                    		]
                };
                localizationobj.days = days;
                var months = {
                    // full month names (13 months for lunar calendards -- 13th month should be "" if not lunar)
                    names: [
                    		"Januar", 
                    		"Februar", 
                    		"März", 
                    		"April", 
                    		"Mai", 
                    		"Juni", 
                    		"Juli", 
                    		"August", 
                    		"September", 
                    		"Oktober", 
                    		"November", 
                    		"Dezember", 
                    		""
                    		],
                    // abbreviated month names
                    namesAbbr: [
                    			"Jan", 
                    			"Feb", 
                    			"Mär", 
                    			"Apr", 
                    			"Mai", 
                    			"Jun", 
                    			"Jul", 
                    			"Aug", 
                    			"Sep", 
                    			"Oct", 
                    			"Nov", 
                    			"Dez", 
                    			""
                    		   ]
                };
                var patterns = {
                    d: "dd.MM.yyyy",
                    D: "dddd, d. MMMM yyyy",
                    t: "HH:mm",
                    T: "HH:mm:ss",
                    f: "dddd, d. MMMM yyyy HH:mm",
                    F: "dddd, d. MMMM yyyy HH:mm:ss",
                    M: "dd MMMM",
                    Y: "MMMM yyyy"
                }
                localizationobj.patterns = patterns;
                localizationobj.months = months;
                return localizationobj;
            };


</script>



<c:if test="${rc.locale.language == 'en'}" >
	<c:set value="ltr" var="varDirection"/>
	<c:set value="center" var="varAlignSection"/>
	<c:set value="left" var="cellsAlign"/>
	<c:set value="left" var="colAlign"/>
	<c:set value="col-xs-6" var="colxs65"/>
	<c:set value="col-xs-4" var="colxs45"/>
	<c:set value="col-xs-2" var="colxs24"/>
	<c:set value="col-xs-3" var="colxs34"/>
	<c:set value="col-xs-4" var="colxs42"/>
	<c:set value="col-xs-3" var="colxs32"/>
	<c:set value="col-xs-3" var="colxs33"/>
	<c:set value="col-xs-2" var="colxs23"/>
	<c:set value="col-xs-2" var="colxs22"/>
	<c:set value="text-align: left !important" var="txtAlign"/>
	
	
</c:if>
<c:if test="${rc.locale.language == 'ar'}">
	<c:set value="rtl" var="varDirection"/>
	<c:set value="right" var="varAlignSection"/>
	<c:set value="right" var="cellsAlign"/>
	<c:set value="right" var="colAlign"/>
	<c:set value="col-xs-offset-6" var="varOffset6"/>
	<c:set value="col-xs-offset-9" var="varOffset9_3"/>
	<c:set value="col-xs-offset-3" var="varOffset3_9"/>
	<c:set value="col-xs-5" var="colxs65"/>
	<c:set value="col-xs-5" var="colxs45"/>
	<c:set value="col-xs-4" var="colxs24"/>
	<c:set value="col-xs-4" var="colxs34"/>
	<c:set value="col-xs-2" var="colxs42"/>
	<c:set value="col-xs-2" var="colxs32"/>
	<c:set value="col-xs-3" var="colxs33"/>
	<c:set value="col-xs-3" var="colxs23"/>
	<c:set value="col-xs-2" var="colxs22"/>
	<c:set value="text-align: right !important" var="alnright"/>
	<c:set value="text-align: right !important" var="txtAlign"/>
	
</c:if>

<!-- Genaration of bread crumb -->

 <ol class="breadcrumb">
	 <c:forEach items="${track}" var="track" varStatus="status" >
	 	<c:choose>
	 		<c:when test="${!status.last}">
			 	<portlet:renderURL var="varUrl">
			 		<portlet:param name="action" value="${track.key}"/>
				 		<c:if test="${not empty track.value.params}" >
				 			<c:forEach items="${track.value.params}" var="prm">
				 				<portlet:param name="${prm.pname}" value="${prm.pvalue}"/>
				 			</c:forEach>
				 		</c:if>
			 	</portlet:renderURL>
				<li><a href="${varUrl}">${track.value.linkName}</a> / </li>
	 		</c:when>
	 		<c:otherwise>
				<li class="active">${track.value.linkName}</li>	
			</c:otherwise> 	
	 	</c:choose>
	</c:forEach>
</ol>