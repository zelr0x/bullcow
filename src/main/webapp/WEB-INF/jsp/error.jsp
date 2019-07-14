<%@ page contentType="text/html"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="${initParam.gameName} | Error">
    <jsp:attribute name="header">
        <div class="header__text-box">
            <h1 class="heading-primary">
                <div class="heading-primary--main">Oops!</div>
            </h1>
            <a href="/register" class="btn btn--white btn--animated">Sign Up</a>
        </div>
    </jsp:attribute>
</t:wrapper>