<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style><%@include file="styles/sign_in_style.css"%></style>
    <form method="post" action="/signIn">
        <div class="box">
            <h1>Sign in</h1>
            <input type="username" name="username" placeholder="username" class="username" />
            <input type="password" name="password" placeholder="password" class="password"/>
            <input type="submit" value="Submit" class="btn"/>
            <a href="/signUp"><div id="btn2">Sign Up</div></a>
        </div>
    </form>
</body>
</html>
