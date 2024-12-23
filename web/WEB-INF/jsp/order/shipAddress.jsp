<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/29
  Time: 上午9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="newOrder?shipAddressSubmitted=true" method="post" id="orderForm">
        <table>
            <tr>
                <th colspan=2>Shipping Address</th>
            </tr>
            <tr>
                <th>First name:</th>
                <td>
                    <input type="text" name="order.shipToFirstName" value="${sessionScope.loginAccount.firstName}">
                </td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td>
                    <input type="text" name="order.shipToLastName" value="${sessionScope.loginAccount.lastName}">
                </td>
            </tr>
            <tr>
                <th>Address 1:</th>
                <td>
                    <input type="text" name="order.shipAddress1" size="40" value="${sessionScope.loginAccount.address1}">
                </td>
            </tr>
            <tr>
                <th>Address 2:</th>
                <td>
                    <input type="text" name="order.shipAddress2" size="40" value="${sessionScope.loginAccount.address2}">
                </td>
            </tr>
            <tr>
                <th>City:</th>
                <td>
                    <input type="text" name="order.shipCity" value="${sessionScope.loginAccount.city}">
                </td>
            </tr>
            <tr>
                <th>State:</th>
                <td>
                    <input type="text" name="order.shipState" size="4" value="${sessionScope.loginAccount.state}">
                </td>
            </tr>
            <tr>
                <th>Zip:</th>
                <td>
                    <input type="text" name="order.shipZip" size="10" value="${sessionScope.loginAccount.zip}">
                </td>
            </tr>
            <tr>
                <th>Country:</th>
                <td>
                    <input type="text" name="order.shipCountry" size="15" value="${sessionScope.loginAccount.country}">
                </td>
            </tr>
            <tr>&nbsp;</tr>
        </table>

        <p><input type="submit" name="newOrder" value="Continue" /></p>

    </form>
</div>

<%@ include file="../common/bottom.jsp"%>
