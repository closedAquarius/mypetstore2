<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/25
  Time: 下午2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="newOrder?newOrderFormSubmitted=true" method="post" id="orderForm"><table>
            <tr>
                <th colspan=2>Payment Details</th>
            </tr>
            <tr>
                <th>Card Type:</th>
                <td>
                    <select name="order.cardType">
                        <option value="Visa">Visa</option>
                        <option value="wechat">wechat</option>
                        <option value="alipay">alipay</option>
                        <option value="others">others</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Card Number:</th>
                <td>
                    <input type="text" name="order.creditCard" value="${sessionScope.order.creditCard}"/>
                </td>
            </tr>
            <tr>
                <th>Expiry Date (MM/YYYY):</th>
                <td>
                    <input type="text" name="order.expiryDate" value="${sessionScope.order.expiryDate}"/>
                </td>
            </tr>
            <tr>
                <th colspan=2>Billing Address</th>
            </tr>

            <tr>
                <th>First name:</th>
                <td>
                    <input type="text" name="order.billToFirstName" value="${sessionScope.loginAccount.firstName}"/>
                </td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td>
                    <input type="text" name="order.billToLastName" value="${sessionScope.loginAccount.lastName}"/>
                </td>
            </tr>
            <tr>
                <th>Address 1:</th>
                <td>
                    <input type="text" size="40" name="order.billAddress1" value="${sessionScope.loginAccount.address1}"/>
                </td>
            </tr>
            <tr>
                <th>Address 2:</th>
                <td>
                    <input type="text" size="40" name="order.billAddress2" value="${sessionScope.loginAccount.address2}"/>
                </td>
            </tr>
            <tr>
                <th>City:</th>
                <td><input type="text" name="order.billCity" value="${sessionScope.loginAccount.city}"/></td>
            </tr>
            <tr>
                <th>State:</th>
                <td>
                    <input type="text" size="40" name="order.billState" value="${sessionScope.loginAccount.state}"/>
                </td>
            </tr>
            <tr>
                <th>Zip:</th>
                <td>
                    <input type="text" size="40" name="order.billZip" value="${sessionScope.loginAccount.zip}"/>
                </td>
            </tr>
            <tr>
                <th>Country:</th>
                <td>
                    <input type="text" size="40" name="order.billCountry" value="${sessionScope.loginAccount.country}"/>
                </td>
            </tr>

            <tr>&nbsp;</tr>
            <tr>
                <td colspan=2>
                    <input type="checkbox" name="shippingAddressRequired" />
                    Ship to different address...
                </td>
            </tr>
        </table>

        <p><input type="submit" name="newOrder" value="Continue" /></p>
    </form>
</div>

<%@ include file="../common/bottom.jsp"%>