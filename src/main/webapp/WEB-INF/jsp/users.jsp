<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<spring:htmlEscape defaultHtmlEscape="true" />

<%@page isELIgnored="false" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <tags:css />
</head>
<body>
<tags:header/>
<c:set value="user/save" var="saveUrl" />
<section class="d-flex flex-column col-12 main-section">
    <div class="p-2 col-10" id="header">
        <h1>Kullanıcı Listesi</h1>
    </div>
    <div class="p-2 col-10">
        <div class="main-header">
            <a class="btn btn-primary" href="${saveUrl}">Yeni Kullanıcı Ekle</a>
        </div>
    </div>
    <div class="p-2" id="user-table">
        <tags:userTable/>
    </div>
</section>
</body>
<tags:modal/>
<tags:js />
