<%--
  Created by IntelliJ IDEA.
  User: nykim
  Date: 2024-03-11
  Time: 오후 7:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@ include file="header.jsp" %>
<body>
    <div class="px-4 pt-5 my-5 text-center border-bottom">
        수강신청이 완료 되었습니다.
        <a href="${pageContext.request.contextPath}/courseEnrollList"> 확인하러 가기  </a>
    </div>
</body>
</html>
