<%@ page contentType="text/html"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="${initParam.gameName} | Home"
           headerClass="header--hero"
           footerClass="footer--hero">
    <jsp:attribute name="header">
        <div class="header__text-box">
            <h1 class="heading-primary">
                <div class="heading-primary--main">${initParam.gameName}</div>
            </h1>
            <a href="/play" class="btn btn--white btn--animated">Play!</a>
            <a href="/rating" class="btn btn--white btn--animated">Rankings</a>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div class="footer__attribution">Photo by Robert Bye on Unsplash</div>
    </jsp:attribute>
</t:wrapper>