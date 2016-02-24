<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<script type="text/javascript">
	function processRdbToOnto(){
		if($("#databaseName").val() == "" && ($("#databaseStructure").val() == "" || $("#databaseRecords").val() == "")){
			$(".alert-danger").show();
			return;
		}
		var form = document.forms[0];
		form.action = "<html:rewrite page='/ProcessRdbToOnto.do'/>";
		form.submit();
	}
</script>

<div class="panel panel-default">
	<div class="panel-heading"><bean:message key="label.relational.database.to.ontology" /></div>
	<div class="panel-body">
		<form role="form" method="post" enctype="multipart/form-data">
		
			<div class="alert alert-danger" style="display: none;">
			  <strong><bean:message key="label.alert" /></strong> <bean:message key="label.mandatory.fields" />
			</div>
			
			<div class="form-group">
				<label for="databaseName"><bean:message key="label.database.name" />:</label>
				<html:text name="rdbToOntoForm" property="databaseName" styleClass="form-control" styleId="databaseName" />
			</div>
			
			<div class="form-group">
				<label for="databaseStructure"><bean:message key="label.database.structure" />:</label>
				<html:file name="rdbToOntoForm" property="databaseStructure" styleClass="form-control" styleId="databaseStructure" />
			</div>
			
			<div class="form-group">
				<label for="databaseRecords"><bean:message key="label.database.records" />:</label>
				<input type="file" class="form-control" id="databaseRecords">
			</div>
			
			<button type="button" onclick="javascript: processRdbToOnto();" class="btn btn-primary"><bean:message key="button.submit" /></button>
			
		</form>
	</div>
</div>