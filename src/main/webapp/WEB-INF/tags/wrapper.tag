<%@ tag description="Main Wrapper Tag"
        pageEncoding="UTF-8"
        trimDirectiveWhitespaces="true" %>
<%@ attribute name="headExtraExternal" fragment="true" %>
<%@ attribute name="headExtraInternal" fragment="true" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="bodyClass" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="headerClass" %>
<%@ attribute name="main" fragment="true" %>
<%@ attribute name="mainClass" %>
<%@ attribute name="footer" fragment="true" %>
<%@ attribute name="footerClass" %>

<!doctype html>
<html lang="en">
<head>
    <%@ include file="../fragments/meta.jspf"%>
    <jsp:invoke fragment="headExtraExternal"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <jsp:invoke fragment="headExtraInternal"/>
    <title>${title}</title>
</head>
<body class="body ${bodyClass}">
    <header class="header ${headerClass}">
        <jsp:invoke fragment="header"/>
    </header>
    <main class="main ${mainClass}">
        <jsp:invoke fragment="main"/>
        <jsp:doBody/>
    </main>
    <footer class="footer ${footerClass}">
        <jsp:invoke fragment="footer"/>
    </footer>
</body>
</html>
