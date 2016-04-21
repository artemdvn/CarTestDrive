<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Car Test Drive</title>
    </head>

    <body>
        <form action="<c:url value="/edit"/>" method="POST">
            <input type="hidden" name="carId" value="${car.carId}"/>
            <table>
                <tr>
                    <td>Name:</td><td><input type="text" name="carName" value="${car.carName}"/></td>
                </tr>
                <tr>
                    <td>Model:</td><td><input type="text" name="modelName" value="${car.modelName}"/></td>
                </tr>
                <tr>
                    <td>Year of issue:</td><td><input type="text" name="yearOfIssue" value="${car.yearOfIssue}"/></td>
                </tr>
                <tr>
                    <td>Showroom:</td>
                    <td>
                        <select name="showroomId">
                        <c:forEach var="showroom" items="${car.showrooms}">
                            <c:choose>
                                <c:when test="${showroom.showroomId==car.showroomId}">
                                    <option value="${showroom.showroomId}" selected><c:out value="${showroom.nameShowroom}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${showroom.showroomId}"><c:out value="${showroom.nameShowroom}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Mileage:</td><td><input type="text" name="mileage" value="${car.mileage}"/></td>
                </tr>
                <tr>
                    <td>Reserved:</td><td><input type="text" name="reserved" value="${car.reserved}"/></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><input type="submit" value="OK" name="OK"/></td>
                    <td><input type="submit" value="Cancel" name="Cancel"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>