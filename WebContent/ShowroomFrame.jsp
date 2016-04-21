<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Car Test Drive</title>
    </head>

    <body>
        <form action="<c:url value="/edit_showroom"/>" method="POST">
            <input type="hidden" name="showroomId" value="${showroom.showroomId}"/>
            <table>
                <tr>
                    <td>Name:</td><td><input type="text" name="nameShowroom" value="${showroom.nameShowroom}"/></td>
                </tr>
                <tr>
                    <td>Address:</td><td><input type="text" name="address" value="${showroom.address}"/></td>
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