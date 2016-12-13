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



</script>




<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>리스트 작성</title>
</head>
<body>
<form id="input" method="post" enctype="multipart/form-data"  action="input">

<table border='1'>

<tr> <td>작성자 : </td> <td><input type="text" id="id" name="id" value="${UserId}" readonly="readonly"></td></tr>
<tr> <td>제   목 : </td> <td><input type="text" id="title" name="title"></td> </tr>
<tr> <td>내   용 : </td> <td><textarea id="contents" name="contents"> </textarea></td> </tr>
<tr> <td><button type="submit">작 성 하 기</button> </td><td> <input type="file" name="file"> </td></tr> 

</table>


</form>

</body>
</html>