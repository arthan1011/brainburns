<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>BrainBurns</title>
    <%--Looks like base tag should be before scripts for proper routing--%>
    <base href="/">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <spring:url value="/resources/styles.css"
                var="styleHref"/>
    <spring:url value="/resources/node_modules/core-js/client/shim.min.js"
                var="polyfillSrc"/>
    <spring:url value="/resources/node_modules/reflect-metadata/Reflect.js"
                var="reflectJsSrc"/>
    <spring:url value="/resources/node_modules/zone.js/dist/zone.js"
                var="zoneJsSrc"/>
    <spring:url value="/resources/node_modules/systemjs/dist/system.src.js"
                var="systemSrcJsSrc"/>
    <spring:url value="/resources/systemjs.config.js"
                var="systemJsSrc"/>
    <link rel="stylesheet"
          href="${styleHref}">

    <!-- Polyfill(s) for older browsers -->
    <script src="${polyfillSrc}"></script>

    <script src="${zoneJsSrc}"></script>
    <script src="${reflectJsSrc}"></script>
    <script src="${systemSrcJsSrc}"></script>

    <script src="${systemJsSrc}"></script>
    <script>
        System.import('app').catch(function (err) {
            console.error(err);
        });
    </script>
</head>

<body>
<brainburns-app></brainburns-app>
<%--Loading Overlay--%>
<div id="loading-overlay" class="loading">
    <div class="center-panel">
        <div class="loading-label">Loading</div>
        <div class="anim-panel">
            <div class="vertical dot"></div>
            <div class="horizontal dot"></div>
        </div>
    </div>
</div>

<script type="text/javascript">
    window.bootstrapping.then(function () {
        document.getElementById("loading-overlay").className = "";
    });
</script>
</body>
</html>
