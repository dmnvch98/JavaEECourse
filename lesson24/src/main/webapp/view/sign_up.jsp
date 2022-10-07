<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>signUp</title>
</head>
<body>
<style><%@include file="styles/sign_in_style.css" %></style>
<form method="post" action="/signUp">
    <div class="box">
        <h1>Sign up</h1>
        <input type="username" name="username" placeholder="username" class="username"/>
        <input type="password" name="password" placeholder="password" class="password"/>
        <input type="submit" value="Submit" class="btn signUp-btn"/>
        <a href="/signIn">
            <div id="btn2">Sign In</div>
        </a>
    </div>
</form>
</body>
</html>