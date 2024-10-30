<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/25
  Time: 下午2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>


<div id="Catalog">

    <form action="signOn" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg != null}">
            <p><font color="red">${requestScope.signOnMsg}</font></p>
        </c:if>
        <p>Username:<input type="text" name="username"> <br />
            Password:<input type="password" name="password"></p>

        <input type="text" name="captchaInput">
        <img src="getCaptcha" alt="验证码"><br />
        <input type="submit" value="Login">
    </form>

    Need a username and password?
    <a href="registerForm">Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>
