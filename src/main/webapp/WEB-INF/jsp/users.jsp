<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<spring:htmlEscape defaultHtmlEscape="true" />

<%@page isELIgnored="false" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
    </style>
</head>
<body>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div id="error" style="text-align: center; background-color: orangered">
        Lütfen tekrar giriş yapmayı deneyin...
    </div>
</c:if>
<c:set value="user/save" var="saveUrl" />
<c:set value="user/remove" var="removeUrl" />
<section class="d-flex flex-column col-12" style="text-align: center;align-items: center; display: inline-block">
    <div class="p-2 col-10" id="header">
        <h1>Kullanıcı Listesi</h1>
    </div>
    <div class="p-2 col-9">
        <div style="text-align: end; margin-right: 3em;">
            <a class="btn btn-primary" href="${saveUrl}">Yeni Kullanıcı Ekle</a>
        </div>
    </div>
    <div class="p-2">
    <c:choose>
        <c:when test="${not empty users and users.size() > 0}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Adı</th>
                    <th>Soyadı</th>
                    <th>Kullanıcı Adı</th>
                    <th>E-posta</th>
                    <th>Telefon</th>
                    <th>Doğum Tarihi</th>
                    <%--<sec:authorize access="hasRole('ADMIN')">
                        //TODO: Find compatible sec resource with jakarta
                    </sec:authorize>--%>
                    <th></th>
                </tr>
                <c:forEach items="${users}" var="user" varStatus="index">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.birthDate}</td>
                        <%--<sec:authorize access="hasRole('ADMIN')">

                        </sec:authorize>--%>
                        <th><a class="btn btn-primary" href="${saveUrl}?id=${user.id}">Güncelle</a>
                            <span>&nbsp;</span>
                            <a class="btn btn-secondary" href="${removeUrl}?id=${user.id}">Sil</a></th>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
        <table>
            <tr>Kullanıcı Bulunamadı...</tr>
        </table>
        </c:otherwise>
    </c:choose>
    </div>
</section>
</body>
