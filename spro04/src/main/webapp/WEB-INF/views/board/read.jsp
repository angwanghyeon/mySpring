<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
							<label>글 번호</label> <input class="form-control" name="bno"
								value="${board.bno}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>제목</label> <input class="form-control" name="title"
								value="${board.title}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="5" style="resize: none;"
								name="content" readonly="readonly">${board.content}</textarea>
						</div>
						<div class="form-group">
							<label>닉네임</label> <input class="form-control" name="writer"
								value="${board.writer}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>작성일자</label> <input class="form-control" name="regdate"
								value="<fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.regdate}" />"
								readonly="readonly">
						</div>
						<div class="form-group">
							<label>수정일자</label> <input class="form-control" name="updatedate"
								value="<fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.updatedate}"/>"
								readonly="readonly">
						</div>
						<button data-oper='modify' class="btn btn-default">수정</button>
						<button data-oper='list' class="btn btn-info">목록</button>

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
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		console.log("============================")
		console.log("==========JS TEST===========")

		const bnoValue = '<c:out value="${board.bno}" />'

		/* replyService.add({
			reply : "JS TEST",
			replyer : "tester",
			bno : bnoValue
		}, function(result) {
			alert("RESULT : " + result);
		});

		replyService.getList({
			bno : bnoValue,
			page : 1
		}, function(list) {
			for (let i = 0, len = list.length || 0; i < len; i++) {
				console.log(list[i]);
			}
		})

		replyService.remove(42, function(count) {
			console.log("remove.......", count);

			if (count === "success") {
				alert("씨봉@방꺼 잘 지워졌다");
			}
		}, function(er) {
			alert("error");
		}); */

		/* replyService.update({
			rno : 56,
			bno : bnoValue,
			reply : "update from read.jsp"
		}, function(result) {
			alert("댓글 수정 완료...");
		})  */

		replyService.read(56, function(reply) {
			console.log(reply);
		}, function(err) {
			alert("error get");
		}) 

		console.log(replyService);

		const operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {
			operForm.attr("action", "/board/modify").submit();
		})
		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list")
			operForm.submit();
		})
	})
</script>
<%@include file="../includes/footer.jsp"%>
