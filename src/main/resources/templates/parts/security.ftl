<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        isAdmin = user.isAdmin()
        id = user.getId()
        name = user.getUsername()
        unknown = true
        >
<#else>
    <#assign
        unknown = false
        isAdmin = false
    >
</#if>