<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag isELIgnored="false" %>

<c:set value="user/save" var="saveUrl" />
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
                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <th></th>
                </sec:authorize>
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
                    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                        <th><a class="btn btn-primary" href="${saveUrl}?id=${user.id}">Güncelle</a>
                            <span>&nbsp;</span>
                            <a class="btn btn-secondary" href="#" onclick="return removeUser(${user.id})">Sil</a></th>
                    </sec:authorize>
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