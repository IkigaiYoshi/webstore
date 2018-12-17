<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<main>
    <label class="open-sans" style="padding-top: 20px; color: #666666;">User Editor</label>
    <div class="user-role">
        <form action="/admin" method="post">
            <input type="text" style="align-content: center" name="username" value="${user.username}">
            <#list roles as role>
            <div style="float: right; padding-right: 115px">
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
            </#list>
            <input class="open-sans" type="hidden" value="${user.id}" name="userId">
            <input  type="hidden" value="${_csrf.token}" name="_csrf">
            <input type="submit" value="Save">
        </form>
    </div>
</main>
</@c.page>