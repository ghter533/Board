<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#logBtn").click(function() {
		var serializedData = $("form").eq(0).serialize();
		alert(serializedData);
		$.ajax({
			type : "post",
			url : "login",
			dataType : "json",
			data : serializedData,
			success : function(jsonObj){
			//alert(jsonObj.loginOk);
				if(jsonObj.loginOk){
				alert(jsonObj.loginOk);
				alert("성공");
				location.href="list";
				}else{
				alert("ID와 PWD를 다시 한번 확인해주세요");
				}
			}
			
		});//ajax
		
	}); //버튼클릭
	
})


</script>




<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인 폼</title>
</head>
<body>
<form>
ID: <input type="text" id="id" name="id" value="aaaa"><br>
pwd: <input type="password" id="pwd" name="pwd" value="1111"><br>

<button type="button" id="logBtn" name="logBtn">로그인</button>
</form>


</body>
</html>