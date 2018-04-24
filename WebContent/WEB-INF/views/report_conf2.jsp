 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-12 report_div" id="report_div_2">
	<form:form modelAttribute="Reportdto" action="addrep.html?page=1" class="form-horizontal" role="form" method="POST">
		<div class="col-sm-12 lr0pad">
			<div class="col-sm-5">
		        <div class="form-group">
		            <form:input path="" name="param_name" type="text" class="form-control" value="${list}" disabled="true"/>
		        </div>
		    </div>
		    <form:input path="report_name" type="hidden" value="${ Reportdto.report_name }"/>
		    <form:input path="query_id" type="hidden" value="${ Reportdto.query_id }"/>
		    <form:input path="db_id" type="hidden" value="${ Reportdto.db_id }"/>
			<br/><br/><br/>
			<div class="col-sm-5">
				<div class="form-group">
		            <form:input path="display_name" name="display_name" type="text" class="form-control" placeholder="Display Name"/>
		        </div>
		    </div>
		</div>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> 
		<div class="form-group">
				<div class="pull-left">
					<button type="button" id="goto_1" class="btn btn-danger submit">
						<span class="glyphicon glyphicon-chevron-left"></span> Back
					</button>
				</div>
				<div class="pull-right">
					<button type="submit" id="goto_3" class="btn btn-info submit">
						Add report <span class="glyphicon glyphicon-chevron-right"></span>
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>