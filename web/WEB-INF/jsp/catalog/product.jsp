<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/24
  Time: 下午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="categoryForm?categoryId=${sessionScope.category.categoryId}">Return to ${sessionScope.category.name}</a>
</div>
<script src="js/adjustTopPosition.js"></script>

<div id="Catalog">

    <h2>${sessionScope.product.name}</h2>
    <table class="show">
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>Operation</th>
        </tr>
        <c:forEach var="item" items="${sessionScope.itemList}">
            <tr>
                <td>
                    <a href="itemForm?itemId=${item.itemId}">${item.itemId}</a>
                </td>
                <td>${item.product.productId}</td>
                <td>${item.attribute1} ${item.attribute2} ${item.attribute3}
                        ${item.attribute4} ${item.attribute5} ${sessionScope.product.name}</td>
                <td><fmt:formatNumber value="${item.listPrice}"
                                      pattern="$#,##0.00" /></td>
                <td>
                    <a href="addItemToCart?workingItemId=${item.itemId}" class="Button">Add to Cart</a>
                </td>
            </tr>
        </c:forEach>
        <tr id="productImage">
            <td colspan="5"><p>${sessionScope.product.description}</p></td>
        </tr>
    </table>
</div>

<%@ include file="../common/bottom.jsp"%>
