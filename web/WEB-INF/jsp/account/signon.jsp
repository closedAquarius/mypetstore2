<%--
  Created by IntelliJ IDEA.
  User: 23837
  Date: 2024/10/25
  Time: 下午2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<script>
    function reloadCaptcha() {
        // 获取验证码图片元素
        var captchaImage = document.getElementById('captchaImage');
        // 为图片添加一个时间戳参数，以确保每次都加载新的图片
        captchaImage.src = 'getCaptcha?' + new Date().getTime();
    }
</script>
<div id="Catalog">

    <form action="signOn" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg != null}">
            <p><font color="red">${requestScope.signOnMsg}</font></p>
        </c:if>
        <p>Username:<input type="text" name="username"> <br />
            Password:<input type="password" name="password"></p>

        <input type="text" name="captchaInput">
        <img id="captchaImage" src="getCaptcha" alt="验证码"
             style="cursor: pointer;" onclick="reloadCaptcha()"><br />
        <input type="submit" value="Login">
    </form>

    Need a username and password?
    <a href="registerForm">Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>
