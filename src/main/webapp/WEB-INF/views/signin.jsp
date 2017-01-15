<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>BrainBurns Sign In</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <spring:url value="/resources/styles.css"
                var="styleHref"/>
    <spring:url var="signInUrl" value="/signin" />
    <spring:url var="signUpPageUrl"
                value="/signup"/>
    <link rel="stylesheet"
          href="${styleHref}">
    <style>

    </style>
</head>

<body>
<div class="login-layout">
    <h1>Sign In</h1>
    <c:if test="${param.error != null}">
        <h2>Incorrect Username or Password</h2>
    </c:if>
    <c:if test="${param.userCreated != null}">
        <h3>You created a new user</h3>
    </c:if>
    <form action="${signInUrl}" method="POST">
        <fieldset>
            <div class="form-group">
                <input id="username" name="username" type="text" placeholder="Username" autofocus required>
            </div>
            <div class="form-group">
                <input id="password" name="password" type="password" placeholder="Password" required>
            </div>
        </fieldset>
        <fieldset id="actions">
            <button type="submit" name="submit" id="submit" value="Sign In">Submit</button>
            <a href="${signUpPageUrl}">
                <button type="button">Sign Up</button>
            </a>
        </fieldset>
    </form>
</div>
</body>
</html>
