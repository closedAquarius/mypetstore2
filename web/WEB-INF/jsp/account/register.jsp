<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/27
  Time: 下午9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="register" method="post">
        <h3>User Information</h3>
        <c:if test="${requestScope.registerMsg != null}">
            <p><font color="red">${requestScope.registerMsg}</font></p>
        </c:if>
        <table>
            <tr>
                <td>User ID:</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="password" name="repeatPassword"></td>
            </tr>
        </table>

        <%@ include file="includeAccount.jsp"%>
        <input type="text" name="captchaInput">
        <img src="getCaptcha" alt="验证码"><br />
        <input type="submit" value="Save Account Information">
    </form>

</div>

<%@ include file="../common/bottom.jsp"%>
