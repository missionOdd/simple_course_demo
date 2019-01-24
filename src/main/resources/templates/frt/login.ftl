<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/login.css">
    <script src="/js/common/common.js"></script>
</head>
<body>
<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">注册</label>
        <div class="login-form">
            <form method="get" action="/tologin">
            <div class="sign-in-htm">
                <div class="group">
                    <label for="user" class="label">Username</label>
                    <input id="user" name="username" type="text" class="input">
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" name="password" type="password" class="input" data-type="password">
                </div>
                <div class="group">
                    <input id="check" name="rememberMe" type="checkbox" class="check" checked>
                    <label for="check"><span class="icon"></span> Keep me Signed in</label>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign In">
                </div>
                <div class="hr"></div>
                <div class="foot-lnk">
                    <a href="#forgot">${(msg)!''}</a>
                </div>
            </div>
            </form>
            <form method="POST" action="/user/createuser" enctype="multipart/form-data">
            <div class="sign-up-htm">
                <div class="group">
                    <label for="user" class="label">Username</label>
                    <input id="user" name="username"  type="text" class="input">
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" name="password"  type="password" class="input" data-type="password">
                </div>

                <div class="group">
                    <label for="email" class="label">Email Address</label>
                    <input id="email" name="email" type="text" class="input">
                </div>

                <div class="group">
                    <label for="file" class="label">头像</label>
                    <input id="file" name="file" type="file" class="input">
                </div>

                <div class="group">
                    <label for="code" class="label">验证码</label>
                    <input id="verifyCodeActual" name="verifyCodeActual" type="text" class="input">
                </div>
                <img onclick="changeVerifyCode(this)" src="/kaptcha">
                <input type="hidden" name="role" value="user">
                <div class="group">
                    <input type="submit" class="button" value="Sign Up">
                </div>
                <form/>
                <div class="hr"></div>
                <div class="foot-lnk">
                    <label for="tab-1">${(msg)!''}</label>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>