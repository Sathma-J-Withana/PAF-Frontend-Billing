package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class billing {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogriddb", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readbilling()  
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
			output = "<table border=\"1\">"
					+ "		<tr>"
					+ "			<th>bill ID</th>"
					+ "			<th>account ID</th>"
					+ "			<th>unit usage</th>"
					+ "			<th>month</th>"
					+ "			<th>year</th>"
					+ "			<th>amount</th>"
					+ "		</tr>";
	 
			String query = "SELECT * FROM billing";
			Statement stat = con.createStatement();
			ResultSet rSet = stat.executeQuery(query);
	 
			// iterate through the rows in the result set    
			while(rSet.next()) {
				String billID = Integer.toString(rSet.getInt("billID"));
				String accountID = Integer.toString(rSet.getInt("accountID"));
				String unitUsage = Double.toString(rSet.getDouble("unitUsage"));
				String month = rSet.getString("month");
				String year = rSet.getString("year");
				String amount = rSet.getString("amount");

				// Add into the HTML table 
				output += "<tr><td><input id='hidbillIDUpdate' name='hidbillIDUpdate' type='hidden' value='" + billID + "'>" + accountID + "</td>";
				output += 	"<td>" + unitUsage +  "</td>";
				output += 	"<td>" + month +  "</td>";
				output += 	"<td>" + year +  "</td>";
				output += 	"<td>" + amount +  "</td>";
				output += 	"</tr>";

				// buttons     
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-billId='" + billID + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billId='" + billID + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the bill.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//insert bills data
	public String insertbilling(String accountID, String unitUsage, String month, String year, String amount)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database";
			} 
	 
			// create a prepared statement 
			String query = "INSERT INTO bill (`billID`, `accountID`,`unitUsage`, `month`, `year`, `amount` )\"\r\n"
					+ "							+ \"VALUES (?,?,?,?,?,?)"; 
	 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
	 
			// binding values    
			prepStat.setInt(1, 0);
			prepStat.setInt(2, Integer.parseInt(accountID));
			prepStat.setInt(3, Integer.parseInt(unitUsage));
			prepStat.setString(4, month);
			prepStat.setString(5, year);
			prepStat.setDouble(6, Double.parseDouble (amount));
			
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	   
			String newbilling = readbilling(); 
//			output =  "{\"status\":\"success\", \"data\": \"" + newbilling + "\"}";   
			output = "bill Inserted Successfully!";

		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the bill.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updatebilling(String billID, String accountID, String unitUsage, String month, String year, String amount)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE billing SET accountID=?, unitUsage=?, month=?, year=?, amount=?, where billID=?"; 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
			
			//binding values
			prepStat.setInt(1, 0);
			prepStat.setInt(2, Integer.parseInt(accountID));
			prepStat.setInt(3, Integer.parseInt(unitUsage));
			prepStat.setString(4, month);
			prepStat.setString(5, year);
			prepStat.setDouble(6, Double.parseDouble (amount));
			
			
			// execute the statement
			prepStat.execute();    
			con.close(); 
	 
			String newbilling = readbilling();    
			output = "{\"status\":\"success\", \"data\": \"" + newbilling + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the bill.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deletebilling(String billID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "DELETE FROM bills WHERE billID=?";  
	 
			PreparedStatement prepStat = con.prepareStatement(query); 
	 
			// binding values    
			prepStat.setInt(1, Integer.parseInt(billID)); 
	 
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	 
			String newbilling = readbilling();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newbilling + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the bill.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
