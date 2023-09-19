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

					<!-- 댓글 시작라인임 -->

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
<div class="row">
	<div class="col-lg-10">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i>댓글
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글
					달기</button>
			</div>
			<div class="panel-body">
				<ul class="chat list-group">
				</ul>
			</div>
			<!-- 댓글 페이징 처리 -->
			<div class="panel-footer">
				
			</div>
			<!-- end reply -->



		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">댓글 작성</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>댓글 내용</label> <input class="form-control" name="reply"
						value="댓글 내용쓰">
				</div>
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name="replyer"
						value="나야나 빵빵이" >
				</div>
				<div class="form-group">
					<label>작성일자</label> <input class="form-control" name="replyDate"
						value="오늘의 날짜" >
				</div>
				
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">수정</button>
				<button id="modalDelBtn" type="button" class="btn btn-danger">삭제</button>
				<button id="modalRegBtn" type="button" class="btn btn-primary">등록</button>
				<button id="modalCloBtn" type="button" class="btn btn-success"
				data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

	const bnoValue = '<c:out value="${board.bno}" />'
	const replyUL = $(".chat");
	showList(1);
	
	function showList(page) {
	replyService.getList(
	{bno : bnoValue,page : page || 1},
	function(list) {
	let str = "";
	if (list == null || list.length == 0) {
	replyUL.html("");
	return;
	}
	for (let i = 0, len = list.length || 0; i < len; i++) {
		str += "<li class='left clearfix list-group-item' data-rno='"+list[i].rno+"'>";
		str += " <div><div class='header'><strong class='primary-font'>"+ list[i].replyer+ "</strong>";
		str += " <small class='pull-right text-muted'>"	+ replyService.displayTime(list[i].replyDate)+ "</small></div>";
		str += " <p>"+ list[i].reply+ "</p></div></li>"
		}
		replyUL.html(str);
		//콜백함수 끝나기전에 페이징 스근하게 나와라잉
		showReplyPage(replyCnt);
	 }); //end function 콜백함수
	}// end showList
	
	let pageNum = 1;
	let replyPageFooter = $(".panel-footer");
	
	
	function showReplyPage(replyCnt){
		let endNum = Math.ceil(pageNum / 10.0) * 10;
		let startNum = endNum - 9;
		const prev = startNum != 1;
		let next = false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt / 10.0);
		}
		if(endNum * 10 < replyCnt){
			next = true;
		}
		
		let str = "<ul class='pagination pull-right'>";
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'> 이전</a></li>";
		}
		for(let i=startNum; i<endNum; i++){
			const active = pageNum == i ? "active" : "";
			str += "<li class='page-item "+active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>"
		}
		
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>다음</a></li>";
		}
		
		str += "</ul>";
		console.log(str);
		replyPageFooter.html(str);
	}
	
	
	const modal = $("#myModal");
	const modalInputReply = modal.find("input[name='reply']");
	const modalInputReplyer = modal.find("input[name='replyer']");
	const modalInputReplyDate = modal.find("input[name='replyDate']");
	
	const modalModBtn = $("#modalModBtn");
	const modalDelBtn = $("#modalDelBtn");
	const modalRegBtn = $("#modalRegBtn");
	
	$("#addReplyBtn").on("click", function(e){
		
		modal.find("input").val("");
		modalInputReplyer.removeAttr("readonly");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id !='modalCloBtn']").hide();
		modalRegBtn.show();
		$(".modal").modal("show");
		$("#myModalLabel").html("댓글 작성");
	});
	
	$(".chat").on("click", "li", function(e){
		const rno = $(this).data("rno");
		console.log("rno는? ㄷㄱㄷㄱ", rno);
		replyService.read(rno, function(result){
		
			modalInputReply.val(result.reply);
			modalInputReplyer.val(result.replyer)
							.attr("readonly","readonly");
			modalInputReplyDate.val(replyService.displayTime(result.replyDate))
							.attr("readonly","readonly");
			modal.data("rno", result.rno);
			modal.find("button[id !='modalCloBtn']").hide();
			modalInputReplyDate.closest("div").show();
			modalModBtn.show();
			modalDelBtn.show();
			$(".modal").modal("show");
			$("#myModalLabel").html("댓글 조회");
		})
	});
	
	
	
	modalRegBtn.on("click", function(e){
		const reply = {
			reply: modalInputReply.val(),
			replyer:modalInputReplyer.val(),
			bno:bnoValue
		}
		replyService.add(reply,	function(result){
			alert("댓글 동록에 성공하였습니당");	
		modal.find("input").val("");
		modal.modal("hide");
		showList(1);
		})
	});
	
	modalModBtn.on("click", function(e){
		
		const reply = {rno:modal.data("rno"), reply:modalInputReply.val()};
		
		replyService.update(reply, function(result){
			alert("업데이트를 성공했어용 >,<");
		modal.modal("hide");
		showList(1);
		})
	});
	
	modalDelBtn.on("click", function(e){
		const rno = modal.data("rno");
		replyService.remove(rno, function(result){
			alert("삭제를 성공했슴당 ><")
		modal.modal("hide");
		showList(1);
		})
	});
	
})
</script>
<script type="text/javascript">
	$(document).ready(function() {

		/* console.log("============================")
		console.log("==========JS TEST===========")

		const bnoValue = '<c:out value="${board.bno}" />' */

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
		});

		replyService.update({
		rno : 56,
		bno : bnoValue,
		reply : "update from read.jsp"
		}, function(result) {
		alert("댓글 수정 완료...");
		})  

		replyService.read(56, function(reply) {
		console.log(reply);
		}, function(err) {
		alert("error get");
		}) 

		console.log(replyService); */

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
