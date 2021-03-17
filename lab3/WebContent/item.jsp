<%@page import="com.item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
if (request.getParameter("itemCode") != null)
 {
 item itemObj = new item();
 String stsMsg = itemObj.insertItem(request.getParameter("itemCode"),
 request.getParameter("itemName"),
 request.getParameter("itemPrice"),
 request.getParameter("itemDesc"));
 session.setAttribute("statusMsg", stsMsg);
 }
    
else if(request.getParameter("itemId") != null){
	item itemUpdate = new item();
	String updateMsg = itemUpdate.updateItem(request.getParameter("itemId"),request.getParameter("itemCode"), request.getParameter("itemName"), request.getParameter("itemPrice"), request.getParameter("itemDesc"));
	session.setAttribute("updateStatus", updateMsg);
}
    
else if(request.getParameter("itemId") != null){
	item itemDelete = new item();	
	String deleteMsg = itemDelete.deleteItem(request.getParameter("itemId"));
	session.setAttribute("deleteStatus", deleteMsg);
}
%>
  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<h1>Items Management</h1>
<form method="post" action="item.jsp">
 Item code: <input name="itemCode" type="text"><br>
 Item name: <input name="itemName" type="text"><br>
 Item price: <input name="itemPrice" type="text"><br>
 Item description: <input name="itemDesc" type="text"><br>
 <input name="btnSubmit" type="submit" value="Save">
</form>
<%
 out.print(session.getAttribute("statusMsg"));
%> 		


</body>
</html>