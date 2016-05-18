<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<script type="text/javascript">
	function submitForm(){
		var form = document.forms[0];
		form.action = "<html:rewrite page='/DownloadOWLFile.do' />";
		form.submit();
	}
</script>

<div class="panel panel-default">
	<div class="panel-heading">Gerar o arquivo OWL</div>
	<div class="panel-body">
		<form role="form" method="post" enctype="multipart/form-data">
		
			<div class="alert alert-danger" style="display: none;">
			  <strong><bean:message key="label.alert" /></strong> <bean:message key="label.mandatory.fields" />
			</div>
			
			<logic:messagesPresent message="message">
            	<html:messages id="message">
					<div class="alert alert-success">
						<bean:write name="message" />
					</div>
              	</html:messages>
 			</logic:messagesPresent>
			
			<button type="button" onclick="javascript:submitForm();" class="btn btn-primary"><bean:message key="button.submit" /></button>
			
		</form>
	</div>
</div>