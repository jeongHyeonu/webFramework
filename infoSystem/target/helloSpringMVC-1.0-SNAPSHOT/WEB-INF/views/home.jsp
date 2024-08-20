<%--
  Created by IntelliJ IDEA.
  User: nykim
  Date: 2022/12/13
  Time: 12:55 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>학사 정보 시스템</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <section class="bg-dark-subtle p-3 mb-2">
      <div class="album py-5 bg-body-tertiary">
        <div class="container">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div class="col">
              <div class="card" style="height:500px">
                <img src="${pageContext.request.contextPath}/resources/images/picture1.jpg" class="card-img-top" alt="..." style="height:250px">
                <div class="card-body d-flex flex-column mb-3">
                  <h5 class="card-title" style="text-align:center; margin:20px">이수 학점 조회</h5>
                  <p class="card-text" style="height:100px">연도에 따른 학년별 자신의 학점을 조회합니다.
                    취득한 학점과 수강 내역을 확인할 수 있습니다.</p>
                  <a href="${pageContext.request.contextPath}/courses" class="btn btn-primary">조회</a>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card" style="height:500px">
                <img src="${pageContext.request.contextPath}/resources/images/picture2.jpg" class="card-img-top" alt="..." style="height:250px">
                <div class="card-body d-flex flex-column mb-3">
                  <h5 class="card-title" style="text-align:center; margin:20px">수강 신청</h5>
                  <p class="card-text" style="height:100px">2024년 2학기에 신청할 교과목을 웹 폼을 통해 입력 받은후 데이터베이스에 오류 검증 작업 후 저장합니다.</p>
                  <a href="${pageContext.request.contextPath}/courseEnroll" class="btn btn-primary">신청</a>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card" style="height:500px">
                <img src="${pageContext.request.contextPath}/resources/images/picture3.jpg" class="card-img-top" alt="..." style="height:250px">
                <div class="card-body d-flex flex-column mb-3">
                  <h5 class="card-title" style="text-align:center; margin:20px">수강신청 조회</h5>
                  <p class="card-text" style="height:100px">2024 년 2학기 수강 신청 내역을 조회합니다.</p>
                  <a href="${pageContext.request.contextPath}/courseEnrollList" class="btn btn-primary">조회</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </body>
</html>
