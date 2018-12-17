<#import "parts/common.ftl" as c>

<#import "parts/login.ftl" as l>
<@c.page>
<main>
<@l.login "/login" "Login"/>
        <div class="signup-callout open-sans">
            <span>New user?</span>
            <a href = "/registration">Sign up now!</a>
        </div>
</main>
</@c.page>
