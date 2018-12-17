<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<main>
    <div class="add-product">
        <label class="open-sans" style="padding-top: 20px; color: #666666">Edit product</label>
        <form action="/editProduct" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${product.id}" />
            <label for="cname">Category</label>
            <input type="text" name="category" value="${product.category}" placeholder="Category name" required>
            <label for="pname">Name</label>
            <input type="text" name="name"  value="${product.name}" placeholder="Product name" required>
            <label for="price">Price</label>
            <input type="text" name="price" value="${product.price}" placeholder="Price" required>
            <label for="qty">Quantity</label>
            <input type="text" name="quantity" value="${product.quantity}" placeholder="Quantity" required>
            <label for="img">Choose image</label>
            <input type="file" name="file" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" required>
            <input type="submit" value="Submit">
        </form>
    </div>
</main>
</@c.page>