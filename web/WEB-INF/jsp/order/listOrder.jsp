<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/28
  Time: 下午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <div id="ListOrder">
    <h2>My Orders</h2>

    <table>
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Total Price</th>
        </tr>

        <c:forEach var="order" items="${requestScope.orderList}">
            <tr>
                <td>
                <a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a>
                </td>
                <td><fmt:formatDate value="${order.orderDate}"
                                    pattern="yyyy/MM/dd hh:mm:ss" /></td>
                <td><fmt:formatNumber value="${order.totalPrice}"
                                      pattern="$#,##0.00" /></td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
<%@ include file="../common/bottom.jsp"%>
