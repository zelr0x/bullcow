<%@ page contentType="text/html"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper title="${initParam.gameName} | Log in"
           bodyClass="body--green-bg"
           mainClass="main--center">
    <jsp:attribute name="headExtraExternal">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    </jsp:attribute>
    <jsp:attribute name="headExtraInternal">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css"/>
    </jsp:attribute>
    <jsp:attribute name="main">
        <!-- https://codepen.io/ehermanson/pen/KwKWEv -->
        <div class="form">
            <ul class="tab-group">
                <li class="tab active"><a href="#signup">Sign Up</a></li>
                <li class="tab"><a href="#login">Log In</a></li>
            </ul>

            <div class="tab-content">
                <div id="signup">
                    <h1>Sign Up for Free</h1>
                    <form action="/register" method="POST">
                        <div class="field-wrap">
                            <label>
                                Username<span class="req">*</span>
                            </label>
                            <input type="text" name="username" required autocomplete="off" />
                        </div>

                        <div class="field-wrap">
                            <label>
                                Password<span class="req">*</span>
                            </label>
                            <input type="password" name="password" required autocomplete="off" />
                        </div>

                        <div class="field-wrap">
                            <label>
                                Repeat a Password<span class="req">*</span>
                            </label>
                            <input type="password" name="password_repeat" required autocomplete="off" />
                        </div>

                        <button type="submit" class="button button-block" />Get Started</button>
                    </form>
                </div>

                <div id="login">
                    <h1>Welcome Back!</h1>
                    <form action="/login" method="POST">
                        <div class="field-wrap">
                            <label>
                                Username<span class="req">*</span>
                            </label>
                            <input type="text" name="username" required autocomplete="off" />
                        </div>

                        <div class="field-wrap">
                            <label>
                                Password<span class="req">*</span>
                            </label>
                            <input type="password" name="password" required autocomplete="off" />
                        </div>
                        <button type="submit" class="button button-block" />Log In</button>
                    </form>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="${pageContext.request.contextPath}/static/js/login.js"></script>
    </jsp:attribute>
</t:wrapper>