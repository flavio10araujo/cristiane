<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<% String contextPath = request.getContextPath(); %>

<link rel="shortcut icon" href="<%=contextPath%>/pages/img/favicon.ico">

<link rel="stylesheet" href="<%=contextPath%>/pages/css/bootstrap-3.3.5.min.css">

<script src="<%=contextPath%>/pages/js/jquery-1.11.3.min.js"></script>
<script src="<%=contextPath%>/pages/js/bootstrap-3.3.5.min.js"></script>
<script src="<%=contextPath%>/pages/js/bootbox-4.0.0.min.js"></script>

<title>.:: <bean:message key="label.rdb.to.onto" /> ::.</title>