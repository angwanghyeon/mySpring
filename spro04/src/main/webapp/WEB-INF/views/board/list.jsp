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
			<div class="panel-heading">
				스프링 게시판이다 씨봉@방꺼
				<button id="regBtn" type="button"
					class="btn btn-xs pull-right btn-warning">게시글 등록하기</button>
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
								<td><a class="move" href="${board.bno}"> <c:out
											value="${board.title}"></c:out> <c:if
											test='${board.regdate != board.updatedate}'>
											<span style="color: red;">new!</span>
										</c:if>
								</a></td>
								<td><c:out value="${board.writer}"></c:out></td>
								<td class="center"><fmt:formatDate
										pattern="yyyy-MM-dd / hh:mm:ss" value="${board.regdate}" /></td>
								<td class="center"><fmt:formatDate
										pattern="yyyy-MM-dd / hh:mm:ss" value="${board.updatedate}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- search -->
				<div class='row'>
                    <div class="col-lg-12">
                        <form class="form-inline" id="searchForm" action="/board/list"
                            method="get">
                           <select name="type" class="form-control form-select-lg mb-3" aria-label=".form-select-lg example">
                                <option value="T" selected
                                <c:out value="${pageMaker.criteria.type eq 'T' ? 'selected' : '' }" />
                                >제목</option>
                                <option value="C"
                                <c:out value="${pageMaker.criteria.type eq 'C' ? 'selected' : '' }" />
                                >내용</option>
                                <option value="W"
                                <c:out value="${pageMaker.criteria.type eq 'W' ? 'selected' : '' }" />
                                >글쓴이</option>
                                <option value="TC"
                                <c:out value="${pageMaker.criteria.type eq 'TC' ? 'selected' : '' }" />
                                >제목 OR 내용</option>
                                <option value="TW"
                                <c:out value="${pageMaker.criteria.type eq 'TW' ? 'selected' : '' }" />
                                >제목 OR 글쓴이</option>
                                <option value="TWC"
                                <c:out value="${pageMaker.criteria.type eq 'TWC' ? 'selected' : '' }" />
                                >제목 OR 내용 OR 글쓴이 </option>
                            </select> 
                            <input name="keyword" class="form-control mr-sm-2" type="text"
                                placeholder="검색조건" aria-label="Search" 
                                value="${pageMaker.criteria.keyword}"
                                >
                            <input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
                            <input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
                            <button class="btn btn-warning btn-outline-success my-2 my-sm-0"
                                >검색</button>
                        </form>
                    </div>
                </div>
				<!-- paging -->
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previos"><a
								href="${pageMaker.startPage-1}">Previos</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li
								class="paginate_button ${pageMaker.criteria.pageNum == num ? 'active' : '' }">
								<a href="${num}">${num}</a>
							<li>
						</c:forEach>
						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage + 1}">Next</a></li>
						</c:if>
					</ul>
				</div>

				<form id="actionForm" action="/board/list" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.criteria.pageNum}"> <input
						type="hidden" name="amount" value="${pageMaker.criteria.amount}">
					<input type="hidden" name="type" value="${pageMaker.criteria.type}">
					<input type="hidden" name="keyword" value="${pageMaker.criteria.keyword}">
				</form>
				<!-- /.table-responsive -->

				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">챱챱!</h4>
							</div>
							<div class="modal-body">처리가 완료 되었습니다 ><</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
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
	$(document)
			.ready(
					function() {
						const result = '<c:out value="${result}" />';

						checkModal(result);
						console.log(history);
						history.replaceState({}, null, null);

						function checkModal(result) {
							if (result === '' || history.state) {
								return;
							}
							if (parseInt(result) > 0) {
								$(".modal.body").html(
										"게시글 " + parseInt(result)
												+ "번이 등록되었습니다.");
							}
							$("#myModal").modal("show");
						}

						$("#regBtn").on("click", function() {
							self.location = "/board/register"
						})

						const actionForm = $("#actionForm");
						$(".paginate_button a").on(
								"click",
								function(e) {
									e.preventDefault();
									console.log("-----pageNum 하윙---");
									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.attr("action", "/board/list");
									/* actionForm.find("input[name='amount']").val($(this).attr("href")); */
									actionForm.submit();
								})

						$(".move")
								.on(
										"click",
										function(e) {
											e.preventDefault();
											actionForm
													.append("<input type='hidden' name='bno' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/board/read");
											actionForm.submit();
											console
													.log("-------주소를 쏘겠다 가자----");

										})
										
					const searchForm = $("#searchForm");
						
					$("#searchForm button").on("click", function(e){
						
						if(!searchForm.find("option:selected").val()){
							alert("검색 조건을 설정해야지 >,<");
							return false;
						}
						
						if(!searchForm.find("input[name='keyword']").val()){
							alert("검색어를 입력해야지!!");
							return false;
						}
						
						searchForm.find("input[name='pageNum']").val("1")
						searchForm.submit();
						
					})
					})
</script>
<%@include file="../includes/footer.jsp"%>