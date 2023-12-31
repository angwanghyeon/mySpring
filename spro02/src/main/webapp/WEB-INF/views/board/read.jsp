<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">글 상세 페이지</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">상세 페이지 씨봉@방꺼</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-10">
							<div class="form-group">
								<label>글 번호</label> 
								<input class="form-control" name="bno"
								value="${board.bno}" readonly="readonly">
							</div>
							<div class="form-group">
								<label>제목</label> 
								<input class="form-control" name="title"
								value="${board.title}" readonly="readonly">
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="5" style="resize: none;"
									name="content" readonly="readonly">${board.content}</textarea>
							</div>
							<div class="form-group">
								<label>닉네임</label> 
								<input class="form-control" name="writer" value="${board.writer}" readonly="readonly">
							</div>
							<div class="form-group">
								<label>작성일자</label> 
							 <input class="form-control" name="regdate" value="<fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.regdate}" />" readonly="readonly">
							</div>
							<div class="form-group">
								<label>수정일자</label> 
								<input class="form-control" name="updatedate" value="<fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.updatedate}"/>" readonly="readonly">
							</div>
							<button data-oper='modify' class="btn btn-default"
							>수정</button>
							<button data-oper='list' class="btn btn-info"
							>목록</button>
							
						<form id="operForm" action="/board/modify" method="get">
							<input type="hidden" id="bno" name="bno" value="${board.bno}">	
							<input type="hidden" name="pageNum" value="${criteria.pageNum}">	
							<input type="hidden" name="amount" value="${criteria.amount}">	
							<input type="hidden" name="type" value="${criteria.type}">	
							<input type="hidden" name="keyword" value="${criteria.keyword}">	
						</form>
						
					</div>
					<!-- /.col-lg-6 (nested) -->
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script type="text/javascript">
$(document).ready(function(){
	const operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		operForm.attr("action", "/board/modify").submit();		
	})
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list")
		operForm.submit();
	})
})

</script>
<%@include file="../includes/footer.jsp"%>
