<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../fragment/uri.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qnaPost.jsp</title>

<style type="text/css">
.title {
	text-align: center;
	margin-top: 130px;
}

.table1 {
	border: 1px solid black;
	text-align: center;
	width: 1000px;
	margin: auto;
	border-collapse: collapse;
}

.td1 {
	border: 1px solid black;
}

.td2 {
	width: 50px;
}

.td3 {
	width: 150px;
}

.td4 {
	width: 700px;
}

.td5 {
	width: 100px;
}

.td5>a {
	text-decoration: none;
	font-size: small;
}

.td6 {
	border: 1px solid black;
}

.contentsTitle {
	border: 1px solid black;
	height: 40px;
}

.contents {
	height: 500px;
}

.comment {
	margin-left: 24%;
	margin-top: 10px;
	font-weight: bold;
}

.table2 {
	margin: auto;
	border: 1px solid black;
	width: 1000px;
	border-collapse: collapse;
	text-align: center;
}

.ctd1 {
	width: 150px;
	border-bottom: 1px solid black;
}

.ctd2 {
	width: 550px;
	border-bottom: 1px solid black;
}

.ctd3 {
	width: 200px;
	text-align: right;
	border-bottom: 1px solid black;
}

.ctd4 {
	width: 100px;
	border-bottom: 1px solid black;
}

.ctd4>a {
	text-decoration: none;
	font-size: small;
}

.commentBox {
	width: 920px;
	height: 20px;
	margin-left: 23.7%;
	margin-top: 5px;
}

.go_a {
	text-decoration: none;
	color: black;
}

.qna_form{
	margin-bottom: 70px;
}
</style>
</head>
<body>
	<div class="title">
		<h1>
			<a class="go_a" href="qnaBoard_list">문의게시판</a>
		</h1>
	</div>
	<table class="table1">
		<tr class="contentsTitle">
			<td class="td1" colspan="4">${qna.title}</td>
		</tr>
		<tr>
			<td class="td2">${qna.id}</td>
			<td class="td3">${qna.userId}</td>
			<td class="td4">${qna.writeDate.getYear()}-${qna.writeDate.getMonthValue()}-${qna.writeDate.getDayOfMonth()}</td>
			<td class="td5"><a href="qna_Modify?qnaId=${qna.id}&uId=${qna.uId}">수정 |</a> <a href="qna_delete?qnaId=${qna.id}">
					삭제</a></td>

		</tr>
		<tr class="contents">
			<td class="td6" colspan="4">
			${qna.content} <br>
				<c:if test="${!empty qna.img}">
				<img src="<spring:url value='/resources/qna_img/${qna.img}'/>">
				</c:if>
			</td>
		</tr>
	</table>
	<div class="comment">답변</div><br>
	<table class="table2">
		<c:forEach var="reply" items="${replyList}">
			<tr>
				<td class="ctd1">${reply.adminId}</td>
				<td class="ctd2">${reply.content}</td>
				<td class="ctd3">${reply.writeDate.getYear()}-${reply.writeDate.getMonthValue()}-${reply.writeDate.getDayOfMonth()}</td>
				<td class="ctd4"><a href="#commentupdate">수정 |</a> <a href="#commentdelete"> 삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<form class="qna_form" action="qna_reply" method="get">
		<input name="qnaId" value="${qna.id}" type="hidden"> 
		<input name="content" type="text" class="commentBox" placeholder="답변을 입력하세요">
		<input type="submit" value="답변쓰기">
	</form>
	<jsp:include page="../fragment/head_ver2.jsp"></jsp:include>
	<jsp:include page="../fragment/footer.jsp"></jsp:include>
</body>
<c:if test="${!empty result}">
<script type="text/javascript">
	alert("${result}")
</script>
</c:if>
</html>