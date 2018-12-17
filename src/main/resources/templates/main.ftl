<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<main>

<div class="search-panel">
    <form method="get" action="/">
        <p class="open-sans" style="font-size: 1em; text-align: center; font-weight: normal;">Search by Category</p>
        <input type="search" name="filter" value="${filter!}">
        <button type="submit">Search</button>
    </form>
</div>
<div class="catalog-items">
    <div class="catalog-items-list">
    <#list products as product>
        <#include "parts/security.ftl">
        <#if product.quantity != 0 || isAdmin>
    <div class="item">
        <#if isAdmin>
        <div style="padding-bottom: 30px">
        <form class="product-admin" action="/deleteProduct" method="post" style="float: right">
            <input type="hidden" value="${product.id}" name="id">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit"><i class="fas fa-times-circle"></i></button>
        </form>
        <form class="product-admin" action="/editProduct" method="get" style="float: right">
            <input type="hidden" value="${product.id}" name="id">
            <button type="submit" ><i class="far fa-edit"></i></button>
        </form>
        </div>
        </#if>
        <div class="product-name">Category: ${product.category}</div>
        <div class="product-name">Name: ${product.name}</div>
        <div class="quantity">Quantity: ${product.quantity}</div>
        <#if product.filename??>
            <img class="product-image" src="/img/${product.filename}">
        </#if>
        <div class="product-info">
            <div class="product-price">${product.price} $</div>
            <form action="/shoppingCart/addProduct" method="get">
                <input type="hidden" value="${product.id}" name="productId">
                <input type="hidden" value="${product.quantity}" name="quantity">
                <button class="product-button" type = "submit">Add Product</button>
            </form>
        </div>

    </div>
        </#if>
    <#else>
        <p class="open-sans" style="font-size: 1em; text-align: center; font-weight: normal; color: #666666">No products</p>
    </#list>
    </div>
</div>
</main>
</@c.page>