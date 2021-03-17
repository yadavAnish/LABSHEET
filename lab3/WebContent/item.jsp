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
<link rel="stylesheet" href="views/bootstrap.min.css">
</head>
<body>
<div class="container">
 <div class="row">
 <div class="col">
 
		<h1>Items Management</h1>
<form method="post" action="item.jsp">
 Item code: <input name="itemCode" type="text" class="form-control"><br>
 Item name: <input name="itemName" type="text" class="form-control"><br>
 Item price: <input name="itemPrice" type="text" class="form-control"><br>
 Item description: <input name="itemDesc" type="text" class="form-control"><br>
 <input name="btnSubmit" type="submit" value="Save"  class="btn btn-primary">
 <input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>
</form>

<div class="alert alert-success">
<% out.print(session.getAttribute("statusMsg"));%>
</div>
		
 
 </div>
 </div>
</div>

</body>
</html>