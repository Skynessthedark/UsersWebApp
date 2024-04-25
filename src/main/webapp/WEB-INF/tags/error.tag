<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag isELIgnored="false" %>
<%@ attribute name="message" required="true" type="java.lang.String"%>

<div id="error">
    ${message}
</div>