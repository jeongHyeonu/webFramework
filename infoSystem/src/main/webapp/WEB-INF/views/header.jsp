<%--
  Created by IntelliJ IDEA.
  User: hw020
  Date: 2024-04-05
  Time: 오후 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">Hansung Univ</a>
            <ul class="nav nav-pills">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <a type="button" class="btn btn-secondary btn-sm" href="javascript:document.getElementById('logout').submit()">로그아웃</a>
                </c:if>
                <li class="nav-item">
                    <form id="logout" method="post" action="<c:url value='/logout' />" >
                        <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-2 link-secondary" href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-2 link-secondary" href="${pageContext.request.contextPath}/courses">학점 조회</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-2 link-secondary" href="${pageContext.request.contextPath}/courseEnroll">수강신청</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-2 link-secondary" href="${pageContext.request.contextPath}/courseEnrollList">수강신청 조회</a>
                </li>
            </ul>
        </div>
    </div>
</header>
