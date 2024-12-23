<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/27
  Time: 下午9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="register" method="post" id="registerForm">
        <h3>User Information</h3>
        <c:if test="${requestScope.registerMsg != null}">
            <p><font color="red">${requestScope.registerMsg}</font></p>
        </c:if>
        <table id="table1">
            <tr>
                <th>User ID:</th>
                <td><input type="text" name="username" id="username"></td>
                <div id="usernameFeedback" class="feedback"></div>
            </tr>
            <tr>
                <th>New password:</th>
                <td><input type="password" name="password" id="password"></td>
                <div id="passwordFeedback" class="feedback"></div>
            </tr>
            <tr>
                <th>Repeat password:</th>
                <td><input type="password" name="repeatPassword" id="repeatPassword"></td>
                <div id="repeatPasswordFeedback" class="feedback"></div>
            </tr>
        </table>

            <%@ include file="includeAccount.jsp"%>

        <br>
        <div id="save">
            <p>
                <input type="text" name="captchaInput">
                <img src="getCaptcha" alt="验证码">
                &nbsp;&nbsp;&nbsp;
                <input type="submit" value="Save Account Information" id="saveButton">
            </p>
        </div>
    </form>
</div>

<%@ include file="../common/bottom.jsp"%>
