<#macro page>
<!DOCTYPE HTML>
<html>
<head>
    <title>Web-Store</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/static/login-signup.css">
    <link rel="stylesheet" href="/static/main.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

</head>
<body>
<header>
    <#include "security.ftl">
    <nav class = "top-row">
        <a class="active" href = "/">Web-Store</a>
        <#if unknown>
        <a style = "float: right; padding: 0"><@l.logout /></a>
        <a style = "float: right;" href = "/shoppingCart">Cart</a>
        </#if>
        <#if isAdmin>
            <a style = "float: right;" href = "/admin/addProduct">Add Product</a>
            <a style = "float: right;" href = "/admin/userList">User List</a>
            <a style = "float: right;" href = "/admin/ordersList">Orders List</a>
        </#if>
    </nav>
</header>
<main>
<#nested>
</main>
</body>
</html>
</#macro>