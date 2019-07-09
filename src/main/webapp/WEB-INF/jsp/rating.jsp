<%@ page contentType="text/html"
         pageEncoding="UTF-8"
         import="io.github.zelr0x.bullcow.dto.PlayerDto" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="${initParam.gameName} | Rankings"
           bodyClass="body--green-bg">
    <jsp:attribute name="headExtraInternal">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/table.css"/>
    </jsp:attribute>
    <jsp:attribute name="header">
        <div class="header__text-box">
            <h1 class="heading-primary">
                <div class="heading-primary--main">Rankings</div>
            </h1>
        </div>
    </jsp:attribute>
    <jsp:attribute name="main">
        <table class="table" id="rating-table">
            <thead class="table__header">
                <tr class="table__header-row">
                    <th class="table__header-cell">Rank</th>
                    <th class="table__header-cell">User</th>
                    <th class="table__header-cell">Average guesses</th>
                    <th class="table__header-cell">Games</th>
                </tr>
            </thead>
            <tbody class="table__body" id="rating-table__body">
            </tbody>
        </table>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script>const usersJson = '${requestScope.players}'</script>
        <script src="${pageContext.request.contextPath}/static/js/rating.js"></script>
    </jsp:attribute>
</t:wrapper>