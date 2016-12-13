<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript">
$(function() {
  $( "#accordion" ).accordion({
    collapsible: true
  });
});



$(document).ready(function(){
	
	var loginOk = ${loginSession};
	
})


</script>

<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<table border="1"> 
<tr> <td>번호</td> <td>작성자</td> <td>제목</td> <td>내용</td> <td>작성일</td> <td>파일번호</td> <td>조회수</td></tr>
<c:forEach var="vo" items="${list}">
<tr><td>${vo.boardNum}</td> <td>${vo.id}</td> <td><a href="boardInfo?boardNum=${vo.boardNum}&fileNum=${vo.fileNum}">${vo.title}</a></td> <td>${vo.contents}</td> <td>${vo.boarddate}</td> <td>${vo.fileNum}</td> <td> ${vo.hit}</td></tr>
</c:forEach>
</table>

<!------------------------------------------------  페이지 네비게이션 ------------------------------------------------------>
<c:choose>
   <c:when test="${nvo.leftMore}">
      <a href="list?page=${nvo.links[0]-4}"> [<<] </a>
   </c:when>
</c:choose>



<c:forEach var="s" items="${nvo.links}">
<c:choose>
   <c:when test="${nvo.currPage == s}">
      [<span style='color:red;font-size:1.5em;'>${s}</span>]
   </c:when>
<c:otherwise>
   <a href="list?page=${s}">[${s}]</a> 
</c:otherwise>
</c:choose>
</c:forEach>



<c:choose>
   <c:when test="${nvo.rightMore}">
   <a href="list?page=${nvo.links[1]+3}"> [>>] </a>
   </c:when>
</c:choose>

<br>



<a href="boardInput">추 가 작 성</a><br>


<!------------------------검색 ----------------------------->
<form id="searchForm" action="search" method="post">
<select id="select" name="select"> 
<optgroup label="검색방법"> </optgroup>
<option value="title"> 제목 </option>
<option value="id"> 작성자 </option>
<option value="contents"> 내용 </option>
</select>
<input type="text" id="searchVal" name="searchVal"> <button type="submit" id="searchBtn">검색</button>

<a href="list"> LIST 로 이동하기 </a>
</form>
<!----------------------------------------------------->




<div id="accordion">
<c:forEach var="vo" items="${list}">
<h3>${vo.boardNum}</h3>
<ul> 
<li> ${vo.id}</li>
<li> ${vo.title}</li>
</ul>
</c:forEach>

</div>













</body>
</html>