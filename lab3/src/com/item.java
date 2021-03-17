package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class item {
	
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/store_db",
	 "root", "root");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}

	
	
	
	
	public String insertItem(String code, String name, String price, String desc)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " insert into item (`itemId`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, name);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(5, desc); 
	
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while inserting";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
	
	public String readItems()
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Item Code</th>"
	 +"<th>Item Name</th><th>Item Price</th>"
	 + "<th>Item Description</th>"
	 + "<th>Update</th><th>Remove</th></tr>";
	 String query = "select * from items";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String itemId = Integer.toString(rs.getInt("itemId"));
	 String itemCode = rs.getString("itemCode");
	 String itemName = rs.getString("itemName");
	 String itemPrice = Double.toString(rs.getDouble("itemPrice"));
	 String itemDesc = rs.getString("itemDesc");
	 // Add a row into the html table
	 output += "<tr><td>" + itemCode + "</td>";
	 output += "<td>" + itemName + "</td>";
	 output += "<td>" + itemPrice + "</td>"; 
	
	output += "<td>" + itemDesc + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' "
	 + " type='button' value='Update'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' "
	 + " type='submit' value='Remove'>"
	 + "<input name='itemID' type='hidden' "
	 + " value='" + itemId + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}

	
	public String deleteItem(String itemId) {
		String result = "";
		
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the Database";
			}
			
			String query = "DELETE FROM item WHERE itemId=?";
			PreparedStatement preparedSt = con.prepareStatement(query);
			preparedSt.setInt(1, Integer.parseInt(itemId));
			preparedSt.execute();
			
			con.close();
			
			result = "Record has been Deleted Successfully";
		}catch(Exception e) {
			result = "Error while Deleting the Student detail.";
			System.err.print(e.getMessage());
		}
		
		return result;
	}
	
	public String updateItem(String id, String itemCode, String itemName, String itemPrice, String itemDesc) {
		String result = "";
		
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the Database";
			}
			
			String query = "UPDATE item SET itemCode=?, itemName=?, itemPrice=?, itemDesc=? WHERE itemId=?";
			PreparedStatement preparedSt = con.prepareStatement(query);
			
			preparedSt.setString(1, itemCode);
			preparedSt.setString(2, itemName);
			preparedSt.setString(3, itemPrice);
			preparedSt.setString(4, itemDesc);
			preparedSt.setInt(5, Integer.parseInt(id));

			preparedSt.execute();
			con.close();
			
			result = "Record has been Updated Successfully";
		}catch(Exception e) {
			result = "Error while Updating the Student details.";
			System.err.println(e.getMessage());
		}
		
		return result;
	}

	

}
