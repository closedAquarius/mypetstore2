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

<c:choose>
    <c:when test="${sessionScope.category.name == 'Fish'}">
        <img src="images/fishBackground.png" alt="Fish" id="background">
    </c:when>
    <c:when test="${sessionScope.category.name == 'Dogs'}">
        <img src="images/dogBackground.png" alt="Dogs" id="background">
    </c:when>
    <c:when test="${sessionScope.category.name == 'Cats'}">
        <img src="images/catBackground.png" alt="Cats" id="background">
    </c:when>
    <c:when test="${sessionScope.category.name == 'Birds'}">
        <img src="images/birdBackground.png" alt="Birds" id="background">
    </c:when>
    <c:when test="${sessionScope.category.name == 'Reptiles'}">
        <img src="images/reptileBackground.png" alt="Reptiles" id="background">
    </c:when>
    <c:otherwise>
        <img src="images/defaultBackground.png" alt="Default" id="background">
    </c:otherwise>
</c:choose>

<%@ include file="../common/bottom.jsp"%>
