<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
<head>
	<tiles:insert attribute="header" />
</head>
<body>
	<tiles:insert attribute="menu" />

	<div style="margin-left: 20px; max-width: 800px">
		<tiles:insert attribute="body" />
	</div>
	<tiles:insert attribute="footer" />
</body>
</html>