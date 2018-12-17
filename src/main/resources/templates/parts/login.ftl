<#macro login path name>
    <div class = "login-form">
        <h3 class="open-sans" style="margin-bottom: 15px; font-size: 1.5em; text-align: center; font-weight: normal;">${name}</h3>
        <hr style="margin-bottom: 20px;">
    <form action="${path}" method="post">
        <label class = "open-sans">Username</label>
        <input type = "text" placeholder = "Your username..." name = "username" required>
        <label class = "open-sans">Password</label>
        <input type="password" placeholder = "Your password..." name="password" required>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class = "open-sans">
            <button class = "open-sans" type = "submit">${name}</button>
        </div>
    </form>
    </div>
</#macro>

<#macro logout>
        <form action="/logout" method="post">
            <input type="submit" value="Logout"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
</#macro>