<%@ page contentType="text/html"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="length" scope="session" value="4"/>

<t:wrapper title="${initParam.gameName} | Game"
           bodyClass="body--green-bg"
           mainClass="main--center">
    <jsp:attribute name="headExtraInternal">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/game.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/table.css"/>
    </jsp:attribute>
    <jsp:attribute name="header">
        <%@ include file="../fragments/nav.jspf" %>
        <div class="header__text-box">
            <h1 class="heading-primary">
                <div class="heading-primary--main" id="result">Your guess</div>
            </h1>
        </div>
    </jsp:attribute>
    <jsp:attribute name="main">
        <form method="POST" action="/game" class="main__center keys" id="guess-form">
            <input type="text" id="guess-input" name="guess"
                placeholder="Your guess..." autofocus required inputmode=numeric
                maxlength="${length}" pattern="([0-9]{${length}})"/>
            <div>
                <c:forEach var="i" begin="0" end="9">
                    <input type="button" class="key" name="button-${i}"
                           value="${i}" id="button-${i}"/>
                </c:forEach>
            </div>
            <div class="keys__control-buttons">
                <input type="button" id="new-game-button" name="new-game-button"
                       value="New Game" class="button control-button button-yes hidden"/>
                <input type="button" id="clear-button" name="clear-button"
                       value="Clear" class="button control-button button-no"/>
                <input type="submit" id="guess-button" name="guess-button"
                       value="Guess!" class="button control-button button-yes"/>
            </div>
        </form>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div id="history-container">
            <table class="table hidden" id="history-table">
                <thead class="table__header">
                    <tr class="table__header-row">
                        <th class="table__header-cell">Turn</th>
                        <th class="table__header-cell">Guess</th>
                        <th class="table__header-cell">Result</th>
                    </tr>
                </thead>
                <tbody class="table__body" id="history-table__body">
                </tbody>
            </table>
        </div>
        <script src="${pageContext.request.contextPath}/static/js/game.js"></script>
    </jsp:attribute>
</t:wrapper>