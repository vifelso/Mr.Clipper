<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mr.Clipper</title>
    <!-- function validates a url-->
    <script language="JavaScript">
        function check_it(theurl) {

            var tomatch = /^(ftp|http|https):\/\/[^ "]+$/;
            if (tomatch.test(theurl)) {
                window.alert("URL OK.");
                return true;
            }
            else {

                document.getElementById('myLabel').innerText='it is wrong url';
                window.alert("URL invalid. Try again.");


                return false;
            }
        }

    </script>
</head>
<body onload="check_it(document.getElementById('inputUrl').value)">

<form:form name="form1" action="/generationShortUrl" method="post" modelAttribute="url"
           onsubmit="return check_it(document.getElementById('inputUrl').value)">
    <form:input path="longUrl" autocomplete="false" id="inputUrl"/>
    <input type="submit" value="do short url"/>
    <c:if test="${url.shortUrl!=null}">
        <label id="myLabel">
            shortUrl - localhost:8080/clipper/<c:out value="${url.shortUrl}"/>
        </label>
    </c:if>
</form:form>
<form:form action="/returnLongUrl" method="get" modelAttribute="url">
    <form:input path="shortUrl" autocomplete="false"/>
    <input type="submit" value="do long url"/>
    <c:if test="${url.longUrl!=null}">
        <label>
            longUrl - localhost:8080/clipper/<c:out value="${url.longUrl}"/>
        </label>
    </c:if>
</form:form>
</body>
</html>
