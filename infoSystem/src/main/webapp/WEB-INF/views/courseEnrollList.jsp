<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2024 2학기 목록</title>
</head>
<%@ include file="header.jsp" %>
<body>
<div class="px-4 pt-5 my-5 text-center border-bottom">
<div style="color: #ff0000;"> <h3>${pageContext.request.userPrincipal.name}님의 2024년 2학기 수강 신청 목록</h3></div>
    <table class="table table-light table-hover" style="margin : auto; width : 80%;">
        <tbody>
        <tr>
            <th>년도</th>
            <th>학기</th>
            <th>교과목명</th>
            <th>교과구분</th>
            <th>담당교수</th>
            <th>학점</th>
        </tr>
        <c:forEach var="courses_2024_2" items="${courses_2024_2}" >
            <tr>
                <td><c:out value="${courses_2024_2.year}"> </c:out></td>
                <td><c:out value="${courses_2024_2.semester}"> </c:out></td>
                <td><c:out value="${courses_2024_2.name}"> </c:out></td>
                <td><c:out value="${courses_2024_2.division}"> </c:out></td>
                <td><c:out value="${courses_2024_2.prof}"> </c:out></td>
                <td><c:out value="${courses_2024_2.degree}"> </c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
