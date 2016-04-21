<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Car Test Drive</title>
    </head>

    <body>
        <form action="<c:url value="/showrooms"/>" method="POST">
            <p/>
            <b>Showroom list:<b>
            <br/>
            <table>
                <tr>
                    <th>&nbsp;</th>
                    <th>Name</th>
                    <th>Address</th>
                </tr>
                <c:forEach var="showroom" items="${form.showrooms}">
                <tr>
                    <td><input type="radio" name="showroomId" value="${showroom.showroomId}"></td>
                    <td><c:out value="${showroom.nameShowroom}"/></td>
                    <td><c:out value="${showroom.address}"/></td>
                </tr>
                </c:forEach>
            </table>
                
            <table>
                <tr>
                    <td><input type="submit" value="Add" name="Add"/></td>
                    <td><input type="submit" value="Edit" name="Edit"/></td>
                    <td><input type="submit" value="Delete" name="Delete"/></td>
                </tr>
            </table>

            <p/>
            
            <a href="${pageContext.request.contextPath}/main">Back to main list</a>
            
        </form>
    </body>

</html>