<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">글 작성하는 페이지</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">씨봉@방꺼 글을 작성해라 니@거</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-10">
						<form action="/board/register" method="post">
							<div class="form-group">
								<label>제목을 입력해주세요</label> 
								<input class="form-control"	placeholder="입력하세요!!" name="title">
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="5" style="resize: none;"
									name="content" placeholder="입력하세요!!"></textarea>
							</div>
							<div class="form-group">
								<label>닉네임</label> 
								<input class="form-control" placeholder="입력하세요!!" name="writer">
							</div>
							<button type="submit" class="btn btn-default">등록</button>
							<button type="reset" class="btn btn-default">취소</button>
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
<%@include file="../includes/footer.jsp"%>
