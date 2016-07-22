<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<% String contextPath = request.getContextPath(); %>

<style>
@import url(//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css);
.panel > .list-group .list-group-item:first-child {}
@media(max-width:767px){
    .visible-xs{display:inline-block !important;}
    .block{display:block !important;width:100%;height:1px !important;}
}
.c-list{padding:0px;min-height:44px;}
.title{display:inline-block;font-size:1.7em;font-weight:bold;padding:5px 15px;}
.name{font-size:1.7em;font-weight:700;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("#overview-page").addClass("active");
	});
</script>

<div class="panel panel-default">
	<div class="panel-heading" style="font-size:1.7em;font-weight:bold;">RDB to ONTO Project</div>
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
								<span>Fez pós em banco.</span><br />
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
	                            <span>Doutora, Universidade Federal do Paraná, Brasil.</span><br />
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
	                            <span>Doutor, Université de Nantes, França.</span><br />
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
	                            <span>Doutor, Universidade Federal do Paraná, Brasil.</span><br />
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