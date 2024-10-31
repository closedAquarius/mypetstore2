<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/30
  Time: 上午11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

  <table>
    <tr>
      <th>&nbsp;</th>
      <th>Product ID</th>
      <th>Name</th>
    </tr>
    <c:forEach var="product" items="${sessionScope.productList}">
      <tr>
        <td>
          <a href="productForm?productId=${product.productId}">${product.description}</a>
        </td>
        <td><b>
          <a href="productForm?productId=${product.productId}">${product.productId}</a>
        </b></td>
        <td>${product.name}</td>
      </tr>
    </c:forEach>
    <tr>
      <td></td>
    </tr>

  </table>

</div>

<%@ include file="../common/bottom.jsp"%>


