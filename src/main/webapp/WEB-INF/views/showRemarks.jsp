<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Remarks</title>
</head>
<body>
<div>
    <div id="header">
        <h1>Products in Database</h1>
    </div>
    <div id="jumbotron">
        <table>
            <tr>
                <th>No</th>
                <th>Type</th>
                <th>Brand</th>
                <th>Model</th>
            </tr>
            <c:forEach items="${products}" var="product" varStatus="index" >
                <tr>
                    <td>${index.index+1}</td>
                    <td>${product.type}</td>
                    <td>${product.brand}</td>
                    <td>${product.model}</td>
                    <td><a href="<c:url value="/product/${product.id}/comments" />"><button>Show Comments</button></a></td>
                    <td><button>Show Remarks</button></td>
                    <td><button>Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="footer">

    </div>

</div>


</body>
</html>