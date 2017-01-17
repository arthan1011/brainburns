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
    <spring:url var="signUpUrl" value="/user/signup"/>
    <spring:url var="signInPageUrl" value="signin"/>
    <link rel="stylesheet"
          href="${styleHref}">
    <style>

    </style>
</head>

<body class="login">
<div class="login-layout">
    <c:if test="${param.exists != null}">
        <h2>User with this name already exists</h2>
    </c:if>
    <c:if test="${param.passwordNotRepeated != null}">
        <h2>You should repeat password!</h2>
    </c:if>
    <form action="${signUpUrl}" method="POST">
        <fieldset>
            <div class="form-group">
                <input id="username" name="username" type="text" placeholder="Username" autofocus required>
            </div>
            <div class="form-group">
                <input id="password" name="password" type="password" placeholder="Password" required>
            </div>
            <div class="form-group">
                <input id="repeatedPassword" name="repeatedPassword" type="password" placeholder="Repeat password" required>
            </div>
        </fieldset>
        <fieldset id="actions">
            <button type="submit" name="submit" id="submit" value="Sign In">Sign Up</button>
            <div>
                Already have an account?
                <a href="${signInPageUrl}">
                    Login here
                </a>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
