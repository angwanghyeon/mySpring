<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">글 수정하는 페이지</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">씨봉@방꺼 글을 수정해라 니@거</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-10">
						<form action="/board/modify" method="post">
							<input type="hidden" name="pageNum" value="${criteria.pageNum}">	
							<input type="hidden" name="amount" value="${criteria.amount}">	
							<input type="hidden" name="type" value="${criteria.type}">	
							<input type="hidden" name="keyword" value="${criteria.keyword}">	
							<div class="form-group">
								<label>글 번호</label> 
								<input class="form-control" name="bno" readonly="readonly"
								value="${board.bno}">
							</div>
							<div class="form-group">
								<label>제목</label> 
								<input class="form-control"	placeholder="입력하세요!!" name="title"
								value="${board.title}">
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="5" style="resize: none;"
									name="content" placeholder="입력하세요!!">${board.content}</textarea>
							</div>
							<div class="form-group">
								<label>닉네임</label> 
								<input class="form-control" placeholder="입력하세요!!" name="writer"
								value="${board.writer}" readonly="readonly">
							</div>
							<div class="form-group">
								<label>작성일자</label> 
							 <input class="form-control" name="regdate" value="<fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.regdate}"/>" readonly="readonly">
							</div>
							<div class="form-group">
								<label>수정일자</label> 
								<input class="form-control" name="updatedate" value="<fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.updatedate}"/>" readonly="readonly">
							</div>
							<button type="button" data-oper="modify" class="btn btn-default">수정 완료</button>
							<button type="button" data-oper="remove"  class="btn btn-danger">삭제</button>
							<button type="button" data-oper="list"  class="btn btn-info">목록</button>
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
<script>
	$(document).ready(function(){
		const formObj = $("form");
		
		$('button').on('click',function(e){
			
			e.preventDefault();
			
			const operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove")
				.attr("method","POST")
			}else if(operation === 'modify'){
				formObj.attr("action", "/board/modify")
				.attr("method","POST")
			}else if(operation === "list"){
				formObj.attr("action", "/board/list")
				.attr("method", "GET")
				
				/*태그를 복제해오는 함수 clone */
				const pageNumTag = $("input[name='pageNum']").clone();
				const amountTag = $("input[name='amount']").clone();
				const typeTag = $("input[name='type']").clone();
				const keywordTag = $("input[name='keyword']").clone();
				
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(typeTag);
				formObj.append(keywordTag);
				
			}
			//폼에 속성을 부여하고 나서 끝나면 이제 제출을 하면 된다.
			formObj.submit();
		})
	})
</script>
<%@include file="../includes/footer.jsp"%>
