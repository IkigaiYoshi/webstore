<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<main xmlns="http://www.w3.org/1999/html">
    <script src="/static/js/main.js"></script>
    <div>
        <div>
            <#if cartItems??>
    <#list cartItems as cartItem>
        <div class="cart">
            <div>
                <div style="float: right">
                    <form action="/shoppingCart/deleteProduct" method="post" class="product-admin">
                        <input type="hidden" value="${cartItem.product.id}" name="id">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit"><i class="fas fa-times-circle"></i></button>
                    </form>
                </div>
                <div style="float: right">
                    <form action="/shoppingCart/editProduct" method="post" class="product-admin">
                        <input type="hidden" value="${cartItem.product.id}" name="id">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <label for="quantity" class="quantity open-sans">Quantity: </label>
                        <input id="quantity"  type="number" value="${cartItem.quantity}" name="quantity" style="width: 25px; margin-right: 5px">
                        <button type="submit"><i class="far fa-edit"></i></button>
                    </form>
                 </div>
            </div>

                <label class="product-name">Name: ${cartItem.product.name}</label>
             <div>
                 <div style="float: left">
                              <#if cartItem.product.filename??>
                        <img class="cart-image" style="padding-top: 5px" src="/img/${cartItem.product.filename}">
                              </#if>
                     <div>
                         <div style="float: left">
                            <label class="product-name open-sans">Price: ${cartItem.product.price} $</label>
                         </div>
                     </div>
                 </div>
                     <label class="product-price open-sans" style="float: right; padding-top: 80px">Total: <script>f(${cartItem.product.price},${cartItem.quantity})</script> $</label>
             </div>

        </div>
    </#list>
            </#if>
            <div id = "t"></div>
             <#if cartEmpty??>
                 <span class="product-name open-sans" style="padding-top: 50px">${cartEmpty}</span>
                <#else>
                <div style="float: right">
                    <div style="padding-right: 365px; color: #666666">
                        <div style="">
                            <span class="product-price open-sans">Total price: ${totalPrice} $</span>
                        </div>
                    </div>
                </div>
                    <div class="cart-button" style="padding-top: 50px">
                        <form action="/shoppingCart/checkout" method="get">
                            <button class="open-sans" type="submit">Сheckout</button>
                        </form>
                    </div>
             </#if>
            <div>
                <#if errorName?? && errorQuantity??>
                    <div class="cart-error">
                    <div class="error">Not available in such a quantity of product ${errorName}. There are ${errorQuantity} products available. Please change the size.</div>
                    </div>
                </#if>
            </div>
        </div>

    </div>
</main>
</@c.page>