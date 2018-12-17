<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<body>
    <div class="add-product">
        <h3 style="text-align: center;">Add product</h3>
    <form action="/addProduct" method="post" enctype="multipart/form-data">
        <label for="cname">Category</label>
        <input type="text" name="category" placeholder="Category name" required>
        <label for="pname">Name</label>
        <input type="text" name="name" placeholder="Product name" required>
        <label for="price">Price</label>
        <input type="text" name="price" placeholder="Price" required>
        <label for="qty">Quantity</label>
        <input type="text" name="quantity" placeholder="Quantity" required>
        <label for="img">Choose image</label>
        <input type="file" name="file" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" required>
        <input type="submit" value="Submit">
    </form>
    </div>
</body>
</@c.page>