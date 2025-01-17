<!---->
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>
<script src="js/adjustTopPosition.js"></script>

<div id="Catalog">

    <div id="CartAndMyList">
        <div id="Cart">

            <h2>Shopping Cart</h2>

            <form action="updateCart" method="post">
                <table id="table" class="show">
                    <tr>
                        <th><b>Item ID</b></th>
                        <th><b>Product ID</b></th>
                        <th><b>Description</b></th>
                        <th><b>In Stock?</b></th>
                        <th><b>Quantity</b></th>
                        <th><b>List Price</b></th>
                        <th><b>Total Cost</b></th>
                        <th>&nbsp;</th>
                    </tr>

                    <c:if test="${sessionScope.cart.numberOfItems == 0}">
                        <tr>
                            <td colspan="8"><b>Your cart is empty.</b></td>
                        </tr>
                    </c:if>

                    <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                        <tr id="cartItem">
                            <td>
                                <a href="itemForm?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                            </td>
                            <td>${cartItem.item.product.productId}</td>
                            <td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                    ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                    ${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
                            <td>${cartItem.inStock}</td>
                            <td>
                                <input type="text"  data-itemId="${cartItem.item.itemId}" name="${cartItem.item.itemId}" value="${cartItem.quantity}">
                            </td>
                            <td><fmt:formatNumber value="${cartItem.item.listPrice}"
                                                  pattern="$#,##0.00" /></td>
                            <td><div class="total"><fmt:formatNumber value="${cartItem.total}"
                                                                     pattern="$#,##0.00" /></div></td>
                            <td>
                                <a href="removeCartItem?workingItemId=${cartItem.item.itemId}" class="Button">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="8">
                            <p id="subTotal" style="font-weight: bold; text-align: center">
                                Sub Total:<fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                            </p>
                        </td>
                    </tr>
                    <tr id="cartButton">
                        <td colspan="8">
                            <p style="text-align: center">
                                <input type="submit" value="Update Cart">
                                <c:if test="${sessionScope.cart.numberOfItems > 0}">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="newOrderForm" class="Button">
                                        <!--<div>Proceed to Checkout</div>-->
                                        <input type="button" value="Proceed to Checkout">
                                    </a>
                                </c:if>
                            </p>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <br><br>
        <div id="MyList">
            <c:if test="${sessionScope.loginAccount != null}">
                <c:if test="${!empty sessionScope.loginAccount.listOption}">
                    <%@ include file="includeMyList.jsp"%>
                </c:if>
            </c:if>
            <div id="bottomSpace"></div>
        </div>
    </div>
    <div id="Separator">&nbsp;</div>
</div>

<script src="js/updateCart.js"></script>
<%@ include file="../common/bottom.jsp"%>