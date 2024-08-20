<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>수강 신청 입력 폼</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/main.css" >

</head>
<%@ include file="header.jsp" %>
<body>
<div class="px-4 pt-5 my-5 text-center border-bottom">
<h2>2024년 2학기 수강신청</h2>
    <sf:form method="post" action="${pageContext.request.contextPath}/doEnroll" modelAttribute="course">
        <table class="formtable" style="margin:auto">
            <tr>
                <td class="label">수강년도:</td>
                <td><sf:input class="control" type="text" path="year"/>  <br/>
                    <sf:errors path="year" class="error"/>
                </td>
            </tr>
            <tr>
                <td class="label">학기:</td>
                <td><sf:input class="control" type="text" path="semester"/> <br/>
                    <sf:errors path="semester" class="error" />
                </td>
            </tr>
            <tr>
                <td class="label">과목코드:</td>
                <td><sf:input class="control" type="text" path="code"/> <br/>
                    <sf:errors path="code" class="error" />
                </td>
            </tr>
            <tr>
                <td class="label">과목명:</td>
                <td><sf:input class="control" type="text" path="name"/> <br/>
                    <sf:errors path="name" class="error" />
                </td>
            </tr>
            <tr>
                <td class="label">교과구분:</td>
                <td><sf:input class="control" type="text" path="division"/> <br/>
                    <sf:errors path="division" class="error" />
                </td>
            </tr>
            <tr>
                <td class="label">담당교수:</td>
                <td><sf:input class="control" type="text" path="prof"/> <br/>
                    <sf:errors path="prof" class="error" />
                </td>
            </tr>
            <tr>
                <td class="label">학점:</td>
                <td><sf:input class="control" type="text" path="degree"/> <br/>
                    <sf:errors path="degree" class="error" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="수강 신청"/> </td>
            </tr>
        </table>
    </sf:form>
</div>
</body>
</html>
