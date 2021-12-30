<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<title>write.jsp</title>
<style>
.write_form_title {
	text-align: center;
	font-size: large;
	margin-top: 130px;
}

.write_form {
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: stretch;
	width: 600px;
	margin-left: auto;
	margin-right: auto;
}

.write_form>.title_div, .write_form>.main_text_div {
	display: flex;
	flex-direction: column;
	align-items: stretch;
}

.write_form>.button {
	display: flex;
	justify-content: space-between;
}

.textBox {
	height: 20px
}

.a{
	text-decoration: none;
}
</style>
<body>
	<div class="write_form_title">
		<h1>문의하기</h1>
	</div>
	<!--게시판으로 이동하는 jsp 파일 -->
	<form class="write_form" action="qna_write" method="POST"
		style="height: 500px;" enctype="multipart/form-data">

		<div class="title_div">
			<p>문의하기</p>
			<input class="textBox" name="title" type="text" placeholder="문의하기 제목">
		</div>

		<div class="main_text_div">
			<p>내용</p>
			<textarea name="content" id="" cols="30" rows="10"
				style="resize: none; height: 250px;" placeholder="문의하기 내용"></textarea>
		</div>

		<div class="button">
			<input name="image" type="file" value=""> <input
				type="submit" value="문의하기">
		</div>
	</form>
	<jsp:include page="../fragment/head_ver2.jsp"></jsp:include>
	<jsp:include page="../fragment/footer.jsp"></jsp:include>
</body>
<%
//로그인에 대한 session 정보 받아오고 다시 다음 페이지로 넘기기
//다음 페이지로넘길 파라미터: title, main_text, 이미지.
//세센에 로그인 정보가 없다면, 접근 할 수 없는 로직도 만들어 넣는다.
%>
</html>