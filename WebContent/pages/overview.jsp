<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<% String contextPath = request.getContextPath(); %>

<style>
@import url(//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css);

.panel > .list-group .list-group-item:first-child {
    /*border-top: 1px solid rgb(204, 204, 204);*/
}
@media (max-width: 767px) {
    .visible-xs {
        display: inline-block !important;
    }
    .block {
        display: block !important;
        width: 100%;
        height: 1px !important;
    }
}
#back-to-bootsnipp {
    position: fixed;
    top: 10px; right: 10px;
}


.c-search > .form-control {
   border-radius: 0px;
   border-width: 0px;
   border-bottom-width: 1px;
   font-size: 1.3em;
   padding: 12px 12px;
   height: 44px;
   outline: none !important;
}
.c-search > .form-control:focus {
    outline:0px !important;
    -webkit-appearance:none;
    box-shadow: none;
}
.c-search > .input-group-btn .btn {
   border-radius: 0px;
   border-width: 0px;
   border-left-width: 1px;
   border-bottom-width: 1px;
   height: 44px;
}


.c-list {
    padding: 0px;
    min-height: 44px;
}
.title {
    display: inline-block;
    font-size: 1.7em;
    font-weight: bold;
    padding: 5px 15px;
}
ul.c-controls {
    list-style: none;
    margin: 0px;
    min-height: 44px;
}

ul.c-controls li {
    margin-top: 8px;
    float: left;
}

ul.c-controls li a {
    font-size: 1.7em;
    padding: 11px 10px 6px;   
}
ul.c-controls li a i {
    min-width: 24px;
    text-align: center;
}

ul.c-controls li a:hover {
    background-color: rgba(51, 51, 51, 0.2);
}

.c-toggle {
    font-size: 1.7em;
}

.name {
    font-size: 1.7em;
    font-weight: 700;
}

.c-info {
    padding: 5px 10px;
    font-size: 1.25em;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("#overview-page").addClass("active");
	});
</script>

<div class="panel panel-default">
	<div class="panel-heading" >RDB to ONTO Project</div>
	<div class="panel-body">
		
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eleifend nulla a nisl rhoncus varius. Etiam luctus massa nisi, eget congue arcu blandit et. Fusce iaculis, metus in ultricies facilisis, diam enim dapibus massa, consequat sodales augue massa sed felis. Mauris eget sapien commodo, eleifend lacus vitae, cursus turpis. Fusce sed mi id tellus hendrerit gravida. Fusce vitae egestas mauris, id viverra lacus. Quisque consectetur vestibulum urna quis consequat. Vivamus luctus neque odio, gravida mattis erat pretium at. Sed molestie vel risus sit amet accumsan.</p>
		<p>Proin sed sagittis tellus, eu pulvinar odio. In posuere diam id purus egestas, eu fringilla turpis mollis. Cras rutrum efficitur pellentesque. Curabitur bibendum molestie risus, eget tincidunt magna sodales vel. Phasellus scelerisque tellus nibh, a efficitur lorem pellentesque quis. Vivamus in tincidunt mauris, quis tristique urna. Suspendisse quis augue sit amet ipsum placerat bibendum. Morbi est neque, viverra ac consectetur sit amet, venenatis sit amet tortor. Praesent finibus nisl at sagittis consequat. Praesent non lobortis sem.</p>
		
		<hr />
		
		<div class="row">
	        <div class="col-xs-12">
	            <div class="panel panel-default">
	                <div class="panel-heading c-list">
	                    <span class="title">Members</span>
	                </div>
	                <ul class="list-group" id="contact-list">
	                    <li class="list-group-item">
	                        <div class="col-xs-12 col-sm-3">
	                            <img src="<%=contextPath%>/pages/img/member01.jpg" class="img-responsive img-circle" />
	                        </div>
	                        <div class="col-xs-12 col-sm-9">
	                            <span class="name">Cristiane Huve</span><br />
		                            <span>Fez pós em banco.</span><br/>
		                            <span>caghuve@inf.ufpr.br</span>
	                        </div>
	                        <div class="clearfix"></div>
	                    </li>
	                    <li class="list-group-item">
	                        <div class="col-xs-12 col-sm-3">
	                            <img src="<%=contextPath%>/pages/img/member02.jpg" class="img-responsive img-circle" />
	                        </div>
	                        <div class="col-xs-12 col-sm-9">
	                            <span class="name">Leticia Mara Peres</span><br />
	                            <span>Doutora, Universidade Federal do Paraná, Brasil.</span><br/>
	                            <span>lmperes@inf.ufpr.br</span>
	                        </div>
	                        <div class="clearfix"></div>
	                    </li>
	                    <li class="list-group-item">
	                        <div class="col-xs-12 col-sm-3">
	                            <img src="<%=contextPath%>/pages/img/member03.jpg" class="img-responsive img-circle" />
	                        </div>
	                        <div class="col-xs-12 col-sm-9">
	                            <span class="name">Marcos Didonet Del Fabro</span><br />
	                            <span>Doutor, Université de Nantes, França.</span><br/>
	                            <span>didonet@inf.ufpr.br</span>
	                        </div>
	                        <div class="clearfix"></div>
	                    </li>
	                    <li class="list-group-item">
	                        <div class="col-xs-12 col-sm-3">
	                            <img src="<%=contextPath%>/pages/img/member04.jpg" class="img-responsive img-circle" />
	                        </div>
	                        <div class="col-xs-12 col-sm-9">
	                            <span class="name">Hegler Tissot</span><br/>
	                            <span>Doutor, Universidade Federal do Paraná, Brasil.</span><br/>
	                            <span>hctissot@inf.ufpr.br</span>
	                        </div>
	                        <div class="clearfix"></div>
	                    </li>
	                </ul>
	            </div>
	        </div>
		</div>
			
	</div>
</div>