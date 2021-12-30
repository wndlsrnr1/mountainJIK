<%@ page import="com.mountain.entity.SanDto" %><%--
  Created by IntelliJ IDEA.
  User: ikik
  Date: 2021/12/30
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<hr><br>
<div id="4" class="mountain_page_level">
    <span><b>난이도</b></span><br><br>
    <form action="/difficulty?id=${san.id}" method="post" name="levelForm" id="levelForm">
        <select name="level" style="height: 30px; margin-top: 10px;">
            <option value="🔥☆☆☆☆">🔥☆☆☆☆</option>
            <option value="🔥🔥☆☆☆">🔥🔥☆☆☆</option>
            <option value="🔥🔥🔥☆☆">🔥🔥🔥☆☆</option>
            <option value="🔥🔥🔥🔥☆">🔥🔥🔥🔥☆</option>
            <option value="🔥🔥🔥🔥🔥">🔥🔥🔥🔥🔥</option>
        </select>
        <input type="hidden" name="sanId" value="${san.id}">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="text" name="text" placeholder="한줄 평" style="width: 500px; height: 30px; ">
        <button type="submit" style="height: 30px;">난이도 등록</button>
    </form>
    <br>
    <table class="level">록
        <thead>
        <tr class="tr1">
            <td class="htd1">난이도</td>
            <td class="htd2">한줄평</td>
            <td class="htd4">작성일</td>
        </tr>
        </thead>
        <c:forEach var="diffi" items="${difficultyList}" >
            <tr class="tr2">
                <td class="td1">${diffi.level}</td>
                <td class="td2">${diffi.text}</td>
                <td class="td4">${diffi.writeDate.getYear()}-${diffi.writeDate.getMonthValue()}-${diffi.writeDate.getDayOfMonth()}</td>
            </tr>
        </c:forEach>
    </table><br>
</div>

<hr><br>
