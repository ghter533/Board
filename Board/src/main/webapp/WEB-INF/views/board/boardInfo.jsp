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
		$("#deleteBtn").click(function(){
			var deleteOk = confirm("정말로 삭제 하시겠습니까?");
			var boardNum = $("#boardNum").val();
			if(deleteOk){
				$.ajax({
					type : "post",
					url : "delete",
					data : {"boardNum":boardNum},
					dataType : "json",
					success : function(json){
						if(json.deleteOk){
							alert("삭제 성공");
							location.href="list";
						}else {
							alert("");
						}
					},
					error : function(json){
						alert("에러발생");
					}
					
				}) //ajax
			}//if(deleteOk)
		});
		
		$("#updateBtn").click(function(){
			location.href="updateForm?boardNum=${boardInfo[0].boardNum}";
		})
		
	})


</script>




<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="boardNum" value="${boardInfo[0].boardNum}"> 
<table border="1">
<tr> <td>번호</td> <td>작성자</td> <td>제목</td> <td>내용</td> <td>작성일</td> <td>파일명</td> <td>조회수</td></tr>
<tr> <td> ${boardInfo[0].boardNum} </td> <td> ${boardInfo[0].id} </td> <td> ${boardInfo[0].title} </td> 
<td> ${boardInfo[0].contents} </td> <td> ${boardInfo[0].boarddate} </td> <td><a href="download?fileNum=${fileInfo.fileNum}"> ${fileInfo.originfilename} </td> <td> ${boardInfo[0].hit} </td> </tr>
</table><br>
<button type="submit" id="deleteBtn"> 삭 제 하 기 </button>
<button type="button" id="updateBtn"> 수 정 하 기 </button>
<a href="list" >리스트로 돌아가기</a>
</body>
</html>