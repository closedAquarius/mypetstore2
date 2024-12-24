<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/27
  Time: 下午9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="edit" method="post" id="registerForm">
        <h3>User Information</h3>
        <c:if test="${requestScope.editMsg != null}">
            <p><font color="red">${requestScope.editMsg}</font></p>
        </c:if>
        <table id="table1">
            <tr>
                <th>User ID:</th>
                <td>${sessionScope.loginAccount.username}</td>
            </tr>
            <tr>
                <th>New Password:</th>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <th>Repeat Password:</th>
                <td><input type="password" name="repeatPassword"></td>
            </tr>
        </table>
        <%@ include file="includeAccount.jsp"%>

        <br>
        <div id="save">
            <p>
                <input type="submit" value="Save Account Information" id="saveButton">
            </p>
            <p>
                <a href="listOrderForm"><input type="button" value="My Orders" id="listOrderButton"></a>
                &nbsp;
                <a href="journalForm"><input type="button" value="My Journals" id="listJournalsButton"></a>
            </p>
        </div>

    </form>
</div>


<%@ include file="../common/bottom.jsp"%>
