<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 16.01.2017
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <div>
            <h1>Product Data</h1>
        </div>
        <div>
            <table>
                <tr>
                    <th>No</th>
                    <th>Type</th>
                    <th>Brand</th>
                    <th>Model</th>
                </tr>
                <tr>
                    <td>${product.id}</td>
                    <td>${product.type}</td>
                    <td>${product.brand}</td>
                    <td>${product.model}</td>
                </tr>
            </table>
        </div>
    </div>
    <div>
        <div>
            <h1>Remarks Data</h1>
        </div>
        <div>
            <table>
                <tr>
                    <th>Key</th>
                    <th>Value</th>
                </tr>
                <c:forEach items="${product.remarks}" var="remark">
                    <tr>
                        <td>${remark.name}</td>
                        <td>${remark.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div>
        <div>
            <h1>Comments Data</h1>
        </div>
        <div>
            <table border="1">
                <tr>
                    <th>No</th>
                    <th>Author</th>
                    <th>Date</th>
                    <th>Content</th>
                    <th>Rate</th>
                    <th>Recommended</th>
                    <th>Helpful</th>
                    <th>Unhelpful</th>
                    <th>Pros</th>
                    <th>Cons</th>
                </tr>
                <c:forEach items="${product.comments}" var="comment">
                    <tr>
                        <td>${comment.id}</td>
                        <td>${comment.author}</td>
                        <td>${comment.date}</td>
                        <td>${comment.content}</td>
                        <td>${comment.rate}</td>
                        <td>${comment.recommended}</td>
                        <td>${comment.helpful}</td>
                        <td>${comment.unhelpful}</td>
                        <td>
                            <c:forEach items="${comment.pros}" var="pro">
                                ${pro}&#10;&#13;
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${comment.cons}" var="con">
                                ${con}&#10;&#13;
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
