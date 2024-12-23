<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/26
  Time: 下午11:11
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${!empty sessionScope.myList}">
    <h2>Pet Favorites</h2>
    <p>Shop for more of your favorite pets here.<p>
    <ul>
        <c:forEach var="product" items="${sessionScope.myList}">
            <a href="productForm?productId=${product.productId}">
                <li>
                    ${product.name}
                    <span>(${product.productId})</span>
                </li>
            </a>
        </c:forEach>
    </ul>
</c:if>
