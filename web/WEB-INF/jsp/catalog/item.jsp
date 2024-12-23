<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/25
  Time: 上午9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="productForm?productId=${sessionScope.product.productId}">Return to ${sessionScope.product.name}</a>
</div>

<div id="Catalog">

    <table id="itemTable">
        <tr id="firstTr">
            <td>${sessionScope.product.description}</td>
        </tr>
        <tr class="itemDetails">
            <td><b> ${sessionScope.item.itemId} </b></td>
        </tr>
        <tr class="itemDetails">
            <td><b><font size="4"> ${sessionScope.item.attribute1}
                ${sessionScope.item.attribute2} ${sessionScope.item.attribute3}
                ${sessionScope.item.attribute4} ${sessionScope.item.attribute5}
                ${sessionScope.product.name} </font></b></td>
        </tr>
        <tr class="itemDetails">
            <td>${sessionScope.product.name}</td>
        </tr>
        <tr class="itemDetails">
            <td><c:if test="${sessionScope.item.quantity <= 0}">
                Back ordered.
            </c:if> <c:if test="${sessionScope.item.quantity > 0}">
                ${sessionScope.item.quantity} in stock.
            </c:if></td>
        </tr>
        <tr class="itemDetails">
            <td><fmt:formatNumber value="${sessionScope.item.listPrice}"
                                  pattern="$#,##0.00" /></td>
        </tr>
        <tr id="lastTr">
            <td>
                <a href="addItemToCart?workingItemId=${sessionScope.item.itemId}" class="Button"><p>Add to Cart</p></a>
            </td>
        </tr>
    </table>

</div>

<%@ include file="../common/bottom.jsp"%>



