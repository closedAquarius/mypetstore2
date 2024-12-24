<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/29
  Time: 上午9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>
<script src="js/adjustTopPosition.js"></script>

<div id="Catalog">
    <form id="confirmOrderForm">
        <table>
            <tr>
                <td colspan="4" id="successInfo">
                    <p>Thank you, your order has been submitted.</p>
                </td>
            </tr>
            <tr>
                <th align="center" colspan="4">Order #${sessionScope.order.orderId}
                    <fmt:formatDate value="${sessionScope.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
                </th>
            </tr>
            <tr>
                <th colspan="4">Payment Details</th>
            </tr>
            <tr>
                <th colspan="2">Card Type:</th>
                <td colspan="2">
                    <c:out value="${sessionScope.order.cardType}" />
                </td>
            </tr>
            <tr>
                <th colspan="2">Card Number:</th>
                <td colspan="2">
                    <c:out value="${sessionScope.order.creditCard}" /> * Fake number!
                </td>
            </tr>
            <tr>
                <th colspan="2">Expiry Date (MM/YYYY):</th>
                <td colspan="2">
                    <c:out value="${sessionScope.order.expiryDate}" />
                </td>
            </tr>
            <tr>
                <th colspan="4">Billing Address</th>
            </tr>
            <tr>
                <th>First name:</th>
                <td>
                    <c:out value="${sessionScope.order.billToFirstName}" />
                </td>
                <th>City:</th>
                <td>
                    <c:out value="${sessionScope.order.billCity}" />
                </td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td>
                    <c:out value="${sessionScope.order.billToLastName}" />
                </td>
                <th>State:</th>
                <td>
                    <c:out value="${sessionScope.order.billState}" />
                </td>
            </tr>
            <tr>
                <th>Address 1:</th>
                <td>
                    <c:out value="${sessionScope.order.billAddress1}" />
                </td>
                <th>Zip:</th>
                <td>
                    <c:out value="${sessionScope.order.billZip}" />
                </td>
            </tr>
            <tr>
                <th>Address 2:</th>
                <td>
                    <c:out value="${sessionScope.order.billAddress2}" />
                </td>
                <th>Country:</th>
                <td>
                    <c:out value="${sessionScope.order.billCountry}" />
                </td>
            </tr>
            <tr>
                <th colspan="4">Shipping Address</th>
            </tr>
            <tr>
                <th>First name:</th>
                <td>
                    <c:out value="${sessionScope.order.shipToFirstName}" />
                </td>
                <th>City:</th>
                <td>
                    <c:out value="${sessionScope.order.shipCity}" />
                </td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td>
                    <c:out value="${sessionScope.order.shipToLastName}" />
                </td>
                <th>State:</th>
                <td>
                    <c:out value="${sessionScope.order.shipState}" />
                </td>
            </tr>
            <tr>
                <th>Address 1:</th>
                <td>
                    <c:out value="${sessionScope.order.shipAddress1}" />
                </td>
                <th>Zip:</th>
                <td>
                    <c:out value="${sessionScope.order.shipZip}" />
                </td>
            </tr>
            <tr>
                <th>Address 2:</th>
                <td>
                    <c:out value="${sessionScope.order.shipAddress2}" />
                </td>
                <th>Country:</th>
                <td>
                    <c:out value="${sessionScope.order.shipCountry}" />
                </td>
            </tr>
            <tr>
                <th>Courier:</th>
                <td>
                    <c:out value="${sessionScope.order.courier}" />
                </td>
                <th>Status:</th>
                <td>
                    <c:out value="${sessionScope.order.status}" />
                </td>
            </tr>
            <tr>
                <th colspan="4">Purchased Items</th>
            </tr>
            <tr>
                <th>Item ID</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <c:forEach var="lineItem" items="${sessionScope.order.lineItems}">
                <tr>
                    <td>
                        <a href="itemForm?itemId=${lineItem.item.itemId}">${lineItem.item.itemId}</a>
                    </td>
                    <td>
                        <c:if test="${lineItem.item != null}">
                            ${lineItem.item.attribute1}
                            ${lineItem.item.attribute2}
                            ${lineItem.item.attribute3}
                            ${lineItem.item.attribute4}
                            ${lineItem.item.attribute5}
                            ${lineItem.item.product.name}
                        </c:if> <c:if test="${lineItem.item == null}">
                        <i>{description unavailable}</i>
                    </c:if>
                    </td>
                    <td style="text-align: center">${lineItem.quantity}</td>
                    <td style="text-align: center">
                        <fmt:formatNumber value="${lineItem.unitPrice}"
                                          pattern="$#,##0.00" />
                    </td>
                    <!--<td><fmt:formatNumber value="${lineItem.total}" pattern="$#,##0.00" /></td>-->
                </tr>
            </c:forEach>
            <tr>
                <th colspan="4">
                    Total: <fmt:formatNumber
                        value="${sessionScope.order.totalPrice}" pattern="$#,##0.00" />
                </th>
            </tr>
        </table>
    </form>
</div>

<%@ include file="../common/bottom.jsp"%>

