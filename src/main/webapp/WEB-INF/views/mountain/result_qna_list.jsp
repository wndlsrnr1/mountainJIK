<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qnaList.jsp</title>


<style type="text/css">
.title {
	text-align: center;
	margin-top: 130px;
}

.table {
	border-top: 2px solid black;
	border-bottom: 1px solid black;
	border-collapse: collapse;
	width: 1000px;
	margin: auto;
	margin-top: 5px;
}

.searchbox {
	margin-left: 64.5%;
}

.pagination {
	display: block;
	text-align: center;
	position: absolute;
	left: 0;
	right: 0;
	bottom: 40px;
}

.pagination>li>a {
	float: none;
}

.write {
	margin-left: 73.5%;
	margin-top: 10px;
}

.htd1 {
	width: 50px;
	text-align: center;
	font-weight: bold;
	border-bottom: 1px solid black;
}

.htd2 {
	width: 650px;
	text-align: center;
	font-weight: bold;
	border-bottom: 1px solid black;
}

.htd3 {
	width: 150px;
	text-align: center;
	font-weight: bold;
	border-bottom: 1px solid black;
}

.htd4 {
	width: 150px;
	text-align: center;
	font-weight: bold;
	border-bottom: 1px solid black;
}

.td1 {
	width: 50px;
	text-align: center;
	border-bottom: 1px solid black;
}

.td2 {
	width: 650px;
	text-align: center;
	border-bottom: 1px solid black;
}

.td3 {
	width: 150px;
	text-align: center;
	border-bottom: 1px solid black;
}

.td4 {
	width: 150px;
	text-align: center;
	border-bottom: 1px solid black;
}

.pageInfo_wrap {
	text-align: center;
}

.pageInfo {
	list-style: none;
	display: inline-block;
	margin: 50px 0px 0 100px;
	margin-left: 0;
}

.pageInfo li {
	float: left;
	font-size: 20px;
	margin-left: 18px;
	padding: 7px;
	font-weight: 500;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: underline;
}

.active {
	background-color: #cdd5ec;
}
</style>
</head>
<body>
	<jsp:include page="fragment/head_ver2.jsp"></jsp:include>
	<jsp:include page="fragment/footer.jsp"></jsp:include>
	<div class="title">
		<h1>
			<a href="qnaBoard_list">문의하기</a>
		</h1>
	</div>
	<input type="text" class="searchbox">
	<input type="button" class="searchbtn" value="검색">
	<table class="table">
		<tr>
			<td class="htd1">No.</td>
			<td class="htd2">제목</td>
			<td class="htd3">작성자</td>
			<td class="htd4">작성일</td>
		</tr>
		<c:forEach var="qna" items="${List}">
			<tr>
				<td class="td1">${qna.id}</td>
				<td class="td2"><a href="qna_select?qnaId=${qna.id}">${qna.title}</a></td>
				<td class="td3">${qna.userId}</td>
				<td class="td4">${qna.writeDate.getYear()}-${qna.writeDate.getMonthValue()}-${qna.writeDate.getDayOfMonth()}</td>
			</tr>
		</c:forEach>

	</table>
	<a href="qna_write"><input type="button" class="write" value="글쓰기"></a>
	<div class="pageInfo_wrap">
		<div class="pageInfo_area">
			<ul class="pageInfo">
				<!-- 이전페이지 버튼 -->
				<c:if test="${pageMaker.prev}">
					<li class="pageInfo_btn previous"><a
						href="${pageMaker.startPage-1}">Previous</a></li>
				</c:if>
				<!-- 각 번호 페이지 버튼 -->
				<c:forEach var="num" begin="${pageMaker.startPage}"
					end="${pageMaker.endPage}">
					<li class="pageInfo_btn ${pageMaker.cri.pageNum == num ? "active":"" }"><a
						href="qnaBoard_list?pageNum=${num}">${num}</a></li>
				</c:forEach>
				<!-- 다음페이지 버튼 -->
				<c:if test="${pageMaker.next}">
					<li class="pageInfo_btn next"><a
						href="${pageMaker.endPage + 1 }">Next</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<form id="moveForm" method="get">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
	</form>

</body>
<script type="text/javascript">
	alert("${result}")
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	let moveForm = $("#moveForm");

	$(".pageInfo a").on("click", function(e) {
		e.preventDefault();
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		moveForm.attr("action", "/main/qnaBoard_list");
		moveForm.submit();
	});
</script>
</html>