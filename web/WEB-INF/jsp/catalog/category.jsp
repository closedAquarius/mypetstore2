<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/24
  Time: 下午11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <h2>${sessionScope.category.name}</h2>

    <table class="show">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${sessionScope.productList}">
                <tr>
                    <td>
                        <a href="productForm?productId=${product.productId}">${product.productId}</a>
                    </td>
                    <td>${product.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<%@ include file="../common/bottom.jsp"%>
