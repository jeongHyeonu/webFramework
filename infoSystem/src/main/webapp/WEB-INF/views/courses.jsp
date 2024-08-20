<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학점 조회</title>
</head>
<%@include file="header.jsp"%>
<body>
    <div class="px-4 pt-5 my-5 text-center border-bottom">
        <h1>이수 학점 조회</h1>
        <table class="table table-light table-hover" style="margin : auto; width : 80%;">
            <tbody>
            <tr>
                <th>년도</th>
                <th>학기</th>
                <th>취득 학점</th>
                <th>상세보기</th>
            </tr>
            <c:forEach var="course" items="${id_courses}" >
                <tr>
                    <td><c:out value="${course.year}"> </c:out></td>
                    <td><c:out value="${course.semester}"> </c:out></td>
                    <td><c:out value="${course.totalDegree}"> </c:out></td>
                    <td><a href="${pageContext.request.contextPath}/courses?year=${course.year}&semester=${course.semester}">상세보기</a></td>
                </tr>
                <c:set var="finalTotal" value="${finalTotal+course.totalDegree}"/>
            </c:forEach>
            <tr>
                <td>총계</td>
                <td></td>
                <td><c:out value="${finalTotal}"> </c:out></td>
                <td></td>
            </tr>
            </tbody>
        </table>

        <c:if test="${not empty course_detail}">
            <br/>
            <div style="color: #ff0000;"> <h3>${pageContext.request.userPrincipal.name}님의 ${course_detail[0].year}년 ${course_detail[0].semester}학기 세부 정보</h3></div>
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
                <c:forEach var="course_detail" items="${course_detail}" >
                    <tr>
                        <td><c:out value="${course_detail.year}"> </c:out></td>
                        <td><c:out value="${course_detail.semester}"> </c:out></td>
                        <td><c:out value="${course_detail.name}"> </c:out></td>
                        <td><c:out value="${course_detail.division}"> </c:out></td>
                        <td><c:out value="${course_detail.prof}"> </c:out></td>
                        <td><c:out value="${course_detail.degree}"> </c:out></td>
                    </tr>
                    <c:set var="detail_Total" value="${detail_Total+course_detail.degree}"/>
                </c:forEach>
                <tr>
                    <td colspan="5">총계</td>
                    <td ><c:out value="${detail_Total}"> </c:out></td>
                </tr>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
