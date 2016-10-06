<%--
  Created by IntelliJ IDEA.
  User: heqingbao
  Date: 16/10/6
  Time: 下午4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:if test="${UID == null}">
    <c:redirect url="index.jsp" />
</c:if>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login Page</title>
    <link href="css/chat.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/chat.js"></script>
</head>
<body>

<form action="logout.go" method="post">
    <div>This is chat page</div>
    <div>Type message then press ENTER key to send message</div>
    <div>Click logout to return the login page</div>
    <div>Your name: <span id="uid">${UID}</span></div>
    <div id="content" class="content"></div>
    <div>
        <textarea class="msg-input" onkeyup="chat.dokeyup(event);">input text here</textarea>
    </div>
    <input type="submit" value="logout"/>
</form>
</body>
</html>
