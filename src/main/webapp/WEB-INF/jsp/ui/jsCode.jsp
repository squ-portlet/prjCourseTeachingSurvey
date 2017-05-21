<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %> --%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<script type="text/javascript">

	$(document).ready(function() {
		
		 var $loading = $('#dvImgAjaxReload');
	
		 <c:if test="${!isPostSurveyAnalysisAvailable}">
			$('#chkSurveyVisibleAnalysis').bootstrapToggle('on');
			   $('#dvVisibleCommittee').show();
			   $('#dvVisibleFaculty').show();
		</c:if>
		 
		 
		 $(document).ajaxStart(function () {
		   $loading.show();
		 });
		 
		 $(document).ajaxStop(function () {
		   $loading.hide();
		 });
	
		 
		 $('#chkSurveyVisibleAnalysis').change(function() {
			 if($('#chkSurveyVisibleAnalysis').prop('checked'))
				 {
					$.ajax({
						
						url: "${urlAnalysisDataTransfer}",
						type: 'POST',
						 datatype:'json',
						 data: { 
							  fakeId: Math.random() 
							   },
						success : function(data){
							var obj = $.parseJSON(data);
							if(!obj)
								{
								$('#dvVisibleCommittee').show();
								$('#dvVisibleFaculty').show();
								}
							else
								{
									$('#chkSurveyVisibleAnalysis').bootstrapToggle('off');
								}
							},
							error	:	function(xhr, status, error)
							{
								$loading.hide();
								alert("Analysis process couldn't complete successfully! Please contact - support");
								
							}
	
					});
				 	
				 }
			 else
				 {
				 	// Make the control on and it should not be off
				 	$('#chkSurveyVisibleAnalysis').bootstrapToggle('on');
				 }
		 });
		 
	} );


</script>
