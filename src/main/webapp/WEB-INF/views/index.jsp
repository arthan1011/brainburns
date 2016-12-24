<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
  <head>
    <title>BrainBurns</title>
    <spring:url value="/resources/script/hel.js" var="helJs" />
    <spring:url value="/resources/script/hello.js" var="helloJs" />
    <script src="${helloJs}"></script>
  </head>
  <body>
  <h1>Welcome to BrainBurns!</h1>
  <h2 id="main-title">

  </h2>
  </body>
</html>
