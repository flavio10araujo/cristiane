<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("#rdbtoonto-page").addClass("active");
	});
	
	$(document).ready(function(){
		$("#btnRdbToOnto").click(function(){
			$(this).button('loading');
			if(!processRdbToOnto()){
				$(this).button('reset');
			}
		});
		
		$("#btnCleanDatabase").click(function(){
			$(this).button('loading');
			cleanDatabase();
		});
	});

	function processRdbToOnto(){
		if($("#databaseName").val()=="" && ($("#databaseStructure").val()=="" || $("#databaseRecords").val()=="")){
			$(".alert-danger").show();
			return false;
		}
		var form=document.forms[0];
		form.action="<html:rewrite page='/ProcessRdbToOnto.do' />";
		form.submit();
		return true;
	}
	
	function cleanDatabase(){
		var form=document.forms[0];
		form.action="<html:rewrite page='/CleanDataBase.do' />";
		form.submit();
	}
	
	function downloadOwl(){
		var form=document.forms[0];
		form.action="<html:rewrite page='/DownloadOWLFile.do' />";
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
			
			<logic:messagesPresent message="message">
            	<html:messages id="message">
					<div class="alert alert-success">
						<bean:write name="message" />
					</div>
              	</html:messages>
 			</logic:messagesPresent>
			
			<div class="form-group">
				<label for="databaseName"><bean:message key="label.database.name" />:</label>
				<html:text name="rdbToOntoForm" property="databaseName" styleClass="form-control" styleId="databaseName" />
			</div>
			
			<div class="form-group">
				<label for="databaseStructure"><bean:message key="label.database.structure" />:</label>
				<html:file name="rdbToOntoForm" property="databaseStructure" styleClass="form-control" styleId="databaseStructure" />
			</div>
			
			<button id="btnRdbToOnto" type="button" class="btn btn-primary"><bean:message key="button.submit" /></button>
			
			<hr />
			
			<button id="btnCleanDatabase" type="button" onclick="javascript: cleanDatabase();" class="btn btn-primary"><bean:message key="label.clean.db" /></button>
			<button id="btnDownloadOwl" type="button" onclick="javascript: downloadOwl();" class="btn btn-primary"><bean:message key="label.download.owl" /></button>
			
		</form>
	</div>
</div>