<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/29
  Time: 上午9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="newOrder?shipAddressSubmitted=true" method="post">
        <table>
            <tr>
                <th colspan=2>Shipping Address</th>
            </tr>
            <tr>
                <td>First name:</td>
                <td>
                    <input type="text" name="order.shipToFirstName" value="${sessionScope.loginAccount.firstName}">
                </td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td>
                    <input type="text" name="order.shipToLastName" value="${sessionScope.loginAccount.lastName}">
                </td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td>
                    <input type="text" name="order.shipAddress1" size="40" value="${sessionScope.loginAccount.address1}">
                </td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td>
                    <input type="text" name="order.shipAddress2" size="40" value="${sessionScope.loginAccount.address2}">
                </td>
            </tr>
            <tr>
                <td>City:</td>
                <td>
                    <input type="text" name="order.shipCity" value="${sessionScope.loginAccount.city}">
                </td>
            </tr>
            <tr>
                <td>State:</td>
                <td>
                    <input type="text" name="order.shipState" size="4" value="${sessionScope.loginAccount.state}">
                </td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td>
                    <input type="text" name="order.shipZip" size="10" value="${sessionScope.loginAccount.zip}">
                </td>
            </tr>
            <tr>
                <td>Country:</td>
                <td>
                    <input type="text" name="order.shipCountry" size="15" value="${sessionScope.loginAccount.country}">
                </td>
            </tr>

        </table>
        <br/>

        <input type="submit" name="newOrder" value="Continue" />

    </form>
</div>

<%@ include file="../common/bottom.jsp"%>
