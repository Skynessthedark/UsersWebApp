<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag isELIgnored="false" %>

<c:set var="actionUrl" value="/user/save" />
<c:set var="isAdmin" value="${fn:containsIgnoreCase(userForm.roles, 'ADMIN')}" />
<form:form method="POST" name="userForm" action="${actionUrl}" >
    <input hidden id="userId" name="id" value="${userForm.id}" />
    <div class="row form-div">
        <label class="form-label" for="name">Adı</label>
        <input class="form-control" type="text" id="name" name="name" value="${userForm.name}">
    </div>
    <div class="row form-div">
        <label class="form-label" for="surname">Soyadı</label>
        <input class="form-control" type="text" id="surname" name="surname" value="${userForm.surname}">
    </div>
    <div class="row form-div">
        <label class="form-label" for="username">Kullanıcı Adı</label>
        <input class="form-control" type="text" id="username" name="username" value="${userForm.username}">
    </div>
    <div class="row form-div">
        <label class="form-label" for="password">Şifre</label>
        <input class="form-control" type="password" id="password" name="password" value="${userForm.password}">
    </div>
    <div class="row form-div">
        <label class="form-label" for="phone">Telefon</label>
        <input class="form-control" type="text" id="phone" name="phone" value="${userForm.phone}">
    </div>
    <div class="row form-div">
        <label class="form-label" for="email">E-posta</label>
        <input class="form-control" type="text" id="email" name="email" value="${userForm.email}">
    </div>
    <div class="row form-div">
        <label class="form-label" for="birthDate">Doğum Tarihi</label>
        <div class="form-date">
            <div class="input-group date" id="datepicker">
                <input type="text" class="form-control" id="birthDate" name="birthDate" value="${userForm.birthDate}"/>
                <span class="input-group-append">
                    <span class="input-group-text bg-light d-block">
                        <i class="fa fa-calendar"></i>
                    </span>
                </span>
            </div>
        </div></div>
    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
        <div class="row form-div">
            <label class="form-check-label" for="admin">Admin Yetkisi Var mı?</label>
            <input class="form-check-input" type="checkbox" id="admin" name="admin" <c:if test="${isAdmin eq true}">checked</c:if> >
        </div>
    </sec:authorize>
    <div class="row form-submit-div">
        <div class="cancel-btn">
            <a href="/user" class="btn btn-secondary" id="cancel-btn">İptal</a>
        </div>
        <div class="submit-btn">
            <button type="submit" class="btn btn-primary" id="submit-btn">Kaydet</button>
        </div>
    </div>
</form:form>