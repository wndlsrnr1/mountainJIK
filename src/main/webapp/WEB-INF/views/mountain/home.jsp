<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="fragment/uri.jsp"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>main.jsp</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style>
		*{margin: 0px; padding: 0px; text-decoration: none; list-style: none;}
	 	.slide{width: 1918px; height: 500px;}

	    .frame{
	        width: calc(1918px); height: 500px;
	
	        overflow: hidden;
	
	        position: relative;
	        }
	        
	    .outer{width: 1918px; height: 500px; margin: 0px auto; border: 1px solid lightgray; margin-top: 164px;}
	    .frame>ul{
	         width: calc(1920px*3); 
	        }
	
	    .frame li{float: left;}
	
	    .frame>ul::after{content: ""; clear: both; display: block;}
	
	    .outer>img
	            {position: absolute; top: 47%; width: 50px; height: 50px; text-align: center;}
		.outer>img:first-of-type{left:20px;}
		.outer>img:last-of-type{right: 20px;}
		.homeInfo{text-align: center; margin-top: 30px;}
	</style>
</head>
<body>
	<div class="outer">
		<div class="frame">
			<ul>
				<li><a href="/san"><img class="slide" src="https://t1.daumcdn.net/cfile/tistory/9951DC475EF188DC0B"></a></li>
	            <li><a href="/forum"><img class="slide" src="https://ifh.cc/g/vHYHsf.jpg"></a></li>
	            <li><a href="/qna"><img class="slide" src="https://ifh.cc/g/zxCITP.jpg"></a></li>
	         </ul>
		</div>
			<img src="https://ifh.cc/g/Ah7PQy.png.png">
	        <img src="https://ifh.cc/g/kSoc3q.png">
	</div>
	<br>
	<div class="homeInfo">
		<b><h1>등산정보 사이트</h1></b>
		<br><br>
		<b><h3>안전하고 즐거운 등산을 위해</h3></b>
		</div>
<jsp:include page="fragment/head_ver2.jsp"></jsp:include>
<jsp:include page="fragment/footer.jsp"></jsp:include>
</body>
	<script type="text/javascript">
	$(function(){
        $(".outer>img").last().click(function(){
            $(".frame>ul").animate(
                {"margin-left" : "-1918px"},
                1000,
                function(){
                    $(".frame>ul").css("margin-left", "0px");
                    $(".frame>ul").append($(this).children().eq(0))
                }
            );
        })
        $(".outer>img").first().click(function(){
            $(".frame>ul").prepend($(".frame>ul").children().last())
                $(".frame>ul").css("margin-left","-1918px");

                $(".frame>ul").animate(
                    {"margin-left" : "0px"},
                    1000
                );
        });
    });
	</script>
<c:if test="${!empty result}">
<script type="text/javascript">
	alert("${result}")
</script>
</c:if>
</html>