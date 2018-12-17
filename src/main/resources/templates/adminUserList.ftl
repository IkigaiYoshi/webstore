<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<main>
    <label class="open-sans" style="padding-top: 20px; color: #666666">List of users</label>
<table style="padding-top: 50px">
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
<#list users as user>
<tr class="open-sans">
    <td style="padding: 10px 20px 20px 20px">${user.username}</td>
    <td style="padding: 10px 20px 20px 20px"><#list user.roles as role>${role}<#sep>, </#list></td>
    <td style="padding: 10px 20px 20px 20px"><a href="/admin/${user.id}">edit</a> </td>
</tr>
</#list>
    </tbody>
</table>
</main>
</@c.page>