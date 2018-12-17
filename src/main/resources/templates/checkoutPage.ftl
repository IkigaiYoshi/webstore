<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#include "parts/security.ftl">
<@c.page>
<main>
    <div class="add-product">
        <h3 style="text-align: center;">Write your information</h3>
        <form action="/shoppingCart/checkout" method="post" enctype="multipart/form-data">
            <label for="address">Address</label>
            <input type="text" name="address" placeholder="Address" required>
            <label for="mail">Mail</label>
            <input type="text" name="mail" placeholder="Mail" required>
            <input type="hidden" name="userId" value="${id}" required>
            <input type="hidden" name="username" value="${name}" required>
            <input type="hidden" name="_csrf" value="${_csrf.token}" required>
            <input type="submit" value="Submit">
        </form>
    </div>
</main>
</@c.page>