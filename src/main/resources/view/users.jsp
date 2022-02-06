<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<h2> All users </h2>

<table cellpadding="5", border="1", cellspacing="0">
<thead>
<tr>
<th> login </th>
<th> email </th>
</tr>
</thead>

<tbody>
<c:forEach items="${users}" var="user">
<tr>
<td> ${user.login} </td>
<td> ${user.email} </td>
</tr>
</c:forEach>
</tbody>

</table>
</body>
</html>