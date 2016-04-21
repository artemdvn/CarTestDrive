<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Car Test Drive</title>
    </head>

    <body>
        <form action="<c:url value="/main"/>" method="POST">
            <table>
                <tr>
                    <td>Select showroom:
                        <select name="showroomId">
                            <c:forEach var="showroom" items="${form.showrooms}">
                                <c:choose>
                                    <c:when test="${showroom.showroomId==form.showroomId}">
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
                	<td><input type="submit" name="getFreeList" value="Get only free cars"/></td>
                    <td><input type="submit" name="getList" value="Get all cars"/></td>
                </tr>
            </table>

            <p/>
            <b>Car list for chosen parameters:<b>
            <br/>
            <table>
                <tr>
                    <th>&nbsp;</th>
                    <th>Name</th>
                    <th>Model</th>
                    <th>Year of issue</th>
                    <th>Mileage</th>
                    <th>Reserved</th>
                </tr>
                <c:forEach var="car" items="${form.cars}">
                <tr>
                    <td><input type="radio" name="carId" value="${car.carId}"></td>
                    <td><c:out value="${car.carName}"/></td>
                    <td><c:out value="${car.modelName}"/></td>
                    <td><c:out value="${car.yearOfIssue}"/></td>
                    <td><c:out value="${car.mileage}"/></td>
                    <td><c:out value="${car.reserved}"/></td>
                </tr>
                </c:forEach>
            </table>
                
            <table>
                <tr>
                    <td><input type="submit" value="Add" name="Add"/></td>
                    <td><input type="submit" value="Edit" name="Edit"/></td>
                    <td><input type="submit" value="Delete" name="Delete"/></td>
                    <td><input type="submit" value="Reserve" name="Reserve"/></td>
                    <td><input type="submit" value="Release" name="Release"/></td>
                </tr>
            </table>

            <p/>
            <b>Move cars to showroom<b>
            <br/>
            <table>
                <tr>
                   <td>Showroom list:
                        <select name="newShowroomId">
                        <c:forEach var="showroom" items="${form.showrooms}">
                            <option value="${showroom.showroomId}"><c:out value="${showroom.nameShowroom}"/></option>
                        </c:forEach>
                        </select>
                    </td>
                    <td><input type="submit" name="MoveGroup" value="Move"/></td>
                </tr>
            </table>
            
            <p/>
            <b>More options:<b>
            <br/>
            
            <a href="${pageContext.request.contextPath}/showrooms">Edit showroom list</a>
            
        </form>
    </body>

</html>