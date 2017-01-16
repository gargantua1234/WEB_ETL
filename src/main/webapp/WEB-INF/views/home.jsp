<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 08.01.2017
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ETL</title>
</head>
<body>
    ${message}

<form:form method="post" action="/" commandName="productCode">
    <table>
        <tr>
            <td>Product code</td>
            <td><input type="text" name="productCode"/></td>
            <td><input type="submit" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>
