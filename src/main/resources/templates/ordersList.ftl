<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<main>
        <#list orders as order>
    <label class="open-sans" style="padding-top: 20px; color: #666666">List of orders</label>
    <table style="padding-top: 50px">
        <thead>
        <tr>
            <th>Id</th>
            <th>User Id</th>
            <th>Username</th>
            <th>Address</th>
            <th>Mail</th>
            <th>Order</th>
        </tr>
        </thead>
        <tbody>

        <tr class="open-sans">
            <td style="padding: 10px 20px 20px 20px">${order.id}</td>
            <td style="padding: 10px 20px 20px 20px">${order.userId}</td>
            <td style="padding: 10px 20px 20px 20px">${order.username}</td>
            <td style="padding: 10px 20px 20px 20px">${order.address}</td>
            <td style="padding: 10px 20px 20px 20px">${order.mail}</td>
            <td style="padding: 10px 20px 20px 20px">${order.listProduct}</td>
        </tr>

        </tbody>
    </table>
        <#else>
        <p class="open-sans" style="font-size: 1em; text-align: center; font-weight: normal; color: #666666">No orders</p>
        </#list>

</main>
</@c.page>