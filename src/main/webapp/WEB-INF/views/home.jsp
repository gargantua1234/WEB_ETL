<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 08.01.2017
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>ETL</title>
</head>
<body>

<form:form method="post" action="/" commandName="productCode">
    <table>
        <tr>
            <td>Product code</td>
            <td><input type="text" name="productCode"/></td>
            <td><input type="submit" /></td>

        </tr>
    </table>
</form:form>
<td><a href="<c:url value="/products"/>"><button>Show Products</button></a></td>

</body>
</html>
