<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<% String contextPath = request.getContextPath(); %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#projectdoc-page").addClass("active");
	});
</script>

<div class="panel panel-default">
	<div class="panel-heading">Project Documentation</div>
	<div class="panel-body">
		<b>Project:</b> <a href="https://github.com/cristianehuve/rdb-to-onto">https://github.com/cristianehuve/rdb-to-onto</a><br />
		<b>Guide:</b> <a href="<%=contextPath%>/pages/files/file01.txt">download</a><br />
		<b>Templates:</b> <a href="<%=contextPath%>/pages/files/file02.txt">download</a><br />
		<b>Dissertation:</b> <a href="<%=contextPath%>/pages/files/file03.txt">download</a>
	</div>
</div>