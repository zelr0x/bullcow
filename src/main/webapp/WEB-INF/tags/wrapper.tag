<%@ tag description="Main Wrapper Tag"
        pageEncoding="UTF-8"
        trimDirectiveWhitespaces="true" %>
<%@ attribute name="headExtra" fragment="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="main" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<%@ attribute name="title" required="true" %>

<!doctype html>
<html lang="en">
<head>
    <%@ include file="../../fragments/meta.jspf"%>
    <jsp:invoke fragment="headExtra"/>
    <link rel="stylesheet" href="../../css/style.css"/>
    <title>${title}</title>
</head>
<body>
    <header class="header">
        <jsp:invoke fragment="header"/>
    </header>
    <main class="main">
        <jsp:invoke fragment="main"/>
    </main>
    <footer class="footer">
        <jsp:invoke fragment="footer"/>
    </footer>
</body>
</html>
<jsp:doBody/>