<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/29
  Time: 上午9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
    <p id="confirmOrderTips">Please confirm the information below and then press continue...</p>
    <form action="newOrder?confirmed=true" method="post" id="confirmOrderForm">
        <table>
            <tr>
                <th align="center" colspan="4">
                   Order <fmt:formatDate value="${sessionScope.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
                </th>
            </tr>

            <tr>
                <th colspan="4">Billing Address</th>
            </tr>
            <tr>
                <th>First name:</th>
                <td><c:out value="${sessionScope.order.billToFirstName}" /></td>
                <th>City:</th>
                <td><c:out value="${sessionScope.order.billCity}" /></td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td><c:out value="${sessionScope.order.billToLastName}" /></td>
                <th>State:</th>
                <td><c:out value="${sessionScope.order.billState}" /></td>
            </tr>
            <tr>
                <th>Address 1:</th>
                <td><c:out value="${sessionScope.order.billAddress1}" /></td>
                <th>Zip:</th>
                <td><c:out value="${sessionScope.order.billZip}" /></td>
            </tr>
            <tr>
                <th>Address 2:</th>
                <td><c:out value="${sessionScope.order.billAddress2}" /></td>
                <th>Country:</th>
                <td><c:out value="${sessionScope.order.billCountry}" /></td>
            </tr>
            <tr>
                <th colspan="4">Shipping Address</th>
            </tr>
            <tr>
                <th>First name:</th>
                <td><c:out value="${sessionScope.order.shipToFirstName}" /></td>
                <th>City:</th>
                <td><c:out value="${sessionScope.order.shipCity}" /></td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td><c:out value="${sessionScope.order.shipToLastName}" /></td>
                <th>State:</th>
                <td><c:out value="${sessionScope.order.shipState}" /></td>
            </tr>
            <tr>
                <th>Address 1:</th>
                <td><c:out value="${sessionScope.order.shipAddress1}" /></td>
                <th>Zip:</th>
                <td><c:out value="${sessionScope.order.shipZip}" /></td>
            </tr>
            <tr>
                <th>Address 2:</th>
                <td><c:out value="${sessionScope.order.shipAddress2}" /></td>
                <th>Country:</th>
                <td><c:out value="${sessionScope.order.shipCountry}" /></td>
            </tr>
            <tr>&nbsp;</tr>
        </table>

        <p><input type="submit" name="newOrder" value="Confirm"/></p>
    </form>
    <%@ include file="../common/bottom.jsp"%>

