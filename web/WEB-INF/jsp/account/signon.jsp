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

    <form action="signOn" method="post" id="signOnForm">
        <p id="signOnTitle">Please Enter Your Username And Password</p>
        <div id="formContent">
            <p>
                <span>Username:</span><br>
                <input type="text" name="username" placeholder="Enter Your Username"> <br><br>
                <span>Password:</span><br>
                <input type="password" name="password" placeholder="Enter Your Password"><br><br>
                <span>Captcha:</span><br>
                <input type="text" name="captchaInput" placeholder="Enter The Captcha" id="captchaInput">&nbsp;
                <img id="captchaImage" src="getCaptcha" alt="验证码"
                 style="cursor: pointer;" onclick="reloadCaptcha()">
            </p><br><br><br>

            <span id="needAnAccount">
                    Need an account?
                    <a href="registerForm">Register Now!</a>
            </span>
            <input type="submit" value="Login" id="submit">
            <c:if test="${requestScope.signOnMsg != null}">
                <p id="requestMessage"><font color="red">${requestScope.signOnMsg}</font></p>
            </c:if>
        </div>
    </form>



</div>

<%@ include file="../common/bottom.jsp"%>
