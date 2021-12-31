<%--
  Created by IntelliJ IDEA.
  User: ikik
  Date: 2021/12/31
  Time: 7:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("homeURI", "http://localhost:8080");

    request.setAttribute("sanURI", "http://localhost:8080/san");
    request.setAttribute("eachSanURI", "http://localhost:8080/san/mountain?id=");

    request.setAttribute("userLoginURI", "http://localhost:8080/user/login");
    request.setAttribute("userJoinURI", "http://localhost:8080/user/join");
    request.setAttribute("userLogoutURI", "http://localhost:8080/user/logout");
    request.setAttribute("userFindURI", "http://localhost:8080/user/findId");
    request.setAttribute("userFindPasswordURI", "http://localhost:8080/user/findPassword");

    request.setAttribute("qnaURI", "http://localhost:8080/qna");


    request.setAttribute("forumURI", "http://localhost:8080/forum");
    request.setAttribute("forumPostURI", "http://localhost:8080/forum/post");
    request.setAttribute("forumPostWriteURI", "http://localhost:8080/forum/post/write");
    request.setAttribute("forumURI", "http://localhost:8080/forum");
    request.setAttribute("forumModifyURI", "http://localhost:8080/forum/post/modify");
    request.setAttribute("forumDeleteURI", "http://localhost:8080/forum/post/delete");
    request.setAttribute("forumReplyURI", "http://localhost:8080/forum/reply");
    request.setAttribute("forumReplyDelete", "http://localhost:8080/forum/reply/delete");



%>