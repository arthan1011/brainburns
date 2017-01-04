<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <title>BrainBurns</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <spring:url value="/resources/styles.css" var="styleHref" />
  <spring:url value="/resources/node_modules/core-js/client/shim.min.js" var="polyfillSrc" />
  <spring:url value="/resources/node_modules/reflect-metadata/Reflect.js" var="reflectJsSrc" />
  <spring:url value="/resources/node_modules/zone.js/dist/zone.js" var="zoneJsSrc" />
  <spring:url value="/resources/node_modules/systemjs/dist/system.src.js" var="systemSrcJsSrc" />
  <spring:url value="/resources/systemjs.config.js" var="systemJsSrc" />
  <link rel="stylesheet" href="${styleHref}">

  <!-- Polyfill(s) for older browsers -->
  <script src="${polyfillSrc}"></script>

  <script src="${zoneJsSrc}"></script>
  <script src="${reflectJsSrc}"></script>
  <script src="${systemSrcJsSrc}"></script>

  <script src="${systemJsSrc}"></script>
  <script>
    System.import('app').catch(function(err){ console.error(err); });
  </script>
  <base href="/">
</head>

<body>
<brainburns-app>Loading AppComponent content here ...</brainburns-app>
</body>
</html>
