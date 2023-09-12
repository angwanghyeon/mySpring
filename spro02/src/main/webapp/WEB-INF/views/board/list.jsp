<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시판</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">스프링 게시판이다 씨봉@방꺼
				<button id="regBtn" type="button" class="btn btn-xs pull-right btn-warning"
				>게시글 등록하기</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>글 번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="board">
							<tr class="odd gradeX">
								<td><c:out value="${board.bno}"></c:out></td>
								<td><c:out value="${board.title}"></c:out></td>
								<td><c:out value="${board.writer}"></c:out></td>
								<td class="center">
								<fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.regdate}"/>
								</td>
								<td class="center"><fmt:formatDate pattern="yyyy-MM-dd / hh:mm:ss" value="${board.updatedate}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- /.table-responsive -->
				
				<!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">챱챱!</h4>
                                        </div>
                                        <div class="modal-body">
                                        	처리가 완료 되었습니다 ><
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script type="text/javascript">
$(document).ready(
	function(){
		const result = '<c:out value="${result}" />';
	
		checkModal(result);
	
		function checkModal(result){
			if(result === ''){ return; }
			if(parseInt(result) > 0){
				$(".modal.body").html("게시글 "+parseInt(result)+"번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		
	$("#regBtn").on("click", function(){
		self.location = "/board/register"
	})
	})
	
</script>
<%@include file="../includes/footer.jsp"%>