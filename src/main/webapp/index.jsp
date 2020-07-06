<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dictionary</title>
    <link href=<c:url value="/static/style.css"/> rel="stylesheet">
</head>
<body>
<br/>
<br/>
<br/>
<div align="center">
    <h4 style="font-family: Arial, sans-serif; color: #333">Program for testing your writing english skills</h4>
    <form action="${pageContext.servletContext.contextPath}/translate" method="GET">
    <table>
        <tr>
            <td>
                <input type="submit" align="center" value="Start!"/>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
