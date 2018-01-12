<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Correction the fail answer</title>
    <link href=<c:url value="/static/style.css"/> rel="stylesheet">
</head>
<body>

<div align="center">
    <h3 style="font-family: Arial, sans-serif; color: #333">Error! Have a crack one more time!</h3>

    <c:if test="${!empty setWordRussian}">
        <table class="tg">
            <tr>
                <th width="600">Variants</th>
            </tr>
            <c:forEach items="${setWordRussian}" var="words">
                <tr>
                    <td align="center">${words.word}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div align="center">
    <h3 style="font-family: Arial, sans-serif; color: #333">In English is:</h3>

    <form:form action="${pageContext.servletContext.contextPath}/verify" commandName="wordEnglish">
        <table>
            <tr>
                <td>
                    <input type="text" name="word" value="" size="100" autocomplete="off">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="<spring:message text="Done!"/>">
                </td>
            </tr>
        </table>
    </form:form>
</div>

<br/>
<div align="center">
    <c:if test="${check == false}">
        <form action="${pageContext.servletContext.contextPath}/prompt" method="GET">
            <table>
                <tr>
                    <td>
                        <input type="submit" align="center" value="Prompt"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
    <c:if test="${check == true}">
        <h4 style="font-family: Arial, sans-serif; color: #333">Proper answer:</h4>
        <h3 style="font-family: Arial, sans-serif; color: #800000">${originalWordEnglish.word}</h3>
    </c:if>
</div>

</body>
</html>
