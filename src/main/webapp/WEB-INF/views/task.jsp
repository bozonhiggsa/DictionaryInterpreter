
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Task from dictionary</title>
    <link href=<c:url value="/static/style.css"/> rel="stylesheet">
</head>
<body>
<div align="center">
    <c:if test="${!empty wordEnglishList}">
        <h3 style="font-family: Arial, sans-serif; color: #333">Translate into English</h3>
    </c:if>

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
    <c:if test="${!empty wordEnglishList}">
        <h3 style="font-family: Arial, sans-serif; color: #333">In English is:</h3>
    </c:if>

    <c:if test="${!empty wordEnglishList}">
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
    </c:if>
</div>

<div align="center">
    <form action="${pageContext.servletContext.contextPath}/well_known" method="GET">
        <table>
            <tr>
                <td>
                    <input type="submit" align="center" value="Go ahead!"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<c:if test="${counterView.countForDone != 0}">
<br/>
<div align="center">
    <h4 style="font-family: Arial, sans-serif; color: #333">Remain for repeating ${counterView.countForDone} words / phrases</h4>
</div>

<br/>
<div align="center">
    <form action="${pageContext.servletContext.contextPath}/repeat" method="GET">
        <table>
            <tr>
                <td>
                    <input type="submit" align="center" value="Repeat done"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</c:if>

<br/>
<div align="center">
    <h4 style="font-family: Arial, sans-serif; color: #333">Remain ${counterView.countRemain} words / phrases</h4>
    <h4 style="font-family: Arial, sans-serif; color: #333">Dictionary volume: ${counterView.countALL} words / phrases</h4>
</div>

<br/>
<div align="center">
    <form action="${pageContext.servletContext.contextPath}/resetCount" method="GET">
        <table>
            <tr>
                <td>
                    <input type="submit" align="center" value="Reset counter"/>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
