<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="test.do" method="post" enctype="multipart/form-data"><!-- img자체를 보낼라면 enctype저 속성값을 써야함 -->
	<input type="text" name="mid">
	<input type="file" name="file">
	<input type="submit" value="확인">
</form>

<img alt="" src="images/개발 천국.png">
<hr>
<img alt="확인" src="images/${file }"/>
<h1>${file }</h1>

</body>
</html>