<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag isELIgnored="false" %>

<c:set var="actionUrl" value="/user/save" />
<form:form method="POST" name="userForm" action="${actionUrl}" >
    <input hidden id="userId" name="id" value="${userForm.id}" />
    <div>
        <label for="name">Adı</label><br>
        <input type="text" id="name" name="name" value="${userForm.name}"><br>
    </div>
    <div>
        <label for="surname">Soyadı</label><br>
        <input type="text" id="surname" name="surname" value="${userForm.surname}"><br>
    </div>
    <div>
        <label for="username">Kullanıcı Adı</label><br>
        <input type="text" id="username" name="username" value="${userForm.username}"><br>
    </div>
    <div>
        <label for="password">Şifre</label><br>
        <input type="text" id="password" name="password" value="${userForm.password}"><br>
    </div>
    <div>
        <label for="phone">Telefon</label><br>
        <input type="text" id="phone" name="phone" value="${userForm.phone}"><br>
    </div>
    <div>
        <label for="email">E-posta</label><br>
        <input type="text" id="email" name="email" value="${userForm.email}"><br>
    </div>
    <div>
        <label for="birthDate">Doğum Tarihi</label><br>
        <input type="text" id="birthDate" name="birthDate" value="${userForm.birthDate}"><br>
    </div>
    <div>
        <label for="roles">Roller</label><br>
        <input type="text" id="roles" name="roles" value="${userForm.roles[0]}"><br>
    </div>
    <div class="p-2">
        <button type="submit" class="btn btn-primary" id="submit-btn">Kaydet</button>
    </div>
</form:form>