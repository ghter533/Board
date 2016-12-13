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
 $(document).ready(function(){
	 $("#updateBtn").click(function(){
	 	var serializedData = $("#updateForm").eq(0).serialize();
	 	
	 	$.ajax({
	 		type : "post",
	 		url : "update",
	 		data : serializedData,
	 		dataType : "json",
	 		success : function(json){
	 			if(json.updateOk){
	 				alert("수정 성공");
	 				location.href="list"
	 			}
	 			
	 		},error:function(data){
	 			alert("error!!!");
	 		}
	 	});//ajax
	 
		 
		 
	 });
 })


</script>




<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>수정 폼</title>
</head>
<body>


<form id="updateForm">

<table border="1">
<tr> <td>번호</td> <td> <input type="text" name="boardNum" value="${updateInfo.boardNum}" readonly="readonly"> </td> </tr>
<tr> <td>작성자</td> <td> <input type="text" name="id" value="${updateInfo.id}" readonly="readonly"> </td> </tr>
<tr> <td>제목</td>  <td> <input type="text" name="title" value="${updateInfo.title}"> </td> </tr>
<tr> <td>내용</td> <td> <input type="text" name="contents" value="${updateInfo.contents}"> </td></tr>
</table>

<button type="button" id="updateBtn">수 정 하 기</button>

</form>


</body>
</html>