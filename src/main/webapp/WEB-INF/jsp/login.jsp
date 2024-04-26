<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<spring:htmlEscape defaultHtmlEscape="true" />

<%@page isELIgnored="false" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <tags:css />
</head>
<body>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <tags:error message="Lütfen tekrar giriş yapmayı deneyin..." />
</c:if>
<c:url var="loginUrl" value='/spring_security_check'/>
<section class="d-flex flex-column main-section">
    <div class="p-2" id="header">
        <h1>Hoşgeldiniz!</h1>
    </div>
    <div class="p-2">
        <p>Lütfen Giriş Yapın.</p>
    </div>
    <div class="p-2">
        <form:form action="${loginUrl}" method="POST" modelAttribute="loginForm" id="login-form">
            <div class="form-floating mt-3">
                <input type="text" class="form-control" id="username" placeholder="Kullanıcı Adı:" name="username">
                <label for="username">Kullanıcı Adı:</label>
            </div>
            <div class="form-floating mt-3">
                <input type="password" class="form-control" id="password" placeholder="Şifre: " name="password">
                <label for="password">Şifre: </label>
            </div>
            <div class="d-grid mt-3">
                <button type="submit" class="btn btn-primary">
                    Giriş Yap
                </button>
            </div>
        </form:form>
    </div>
</section>
</body>
<tags:js />