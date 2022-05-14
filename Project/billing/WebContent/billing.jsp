<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

<title>Billing</title>
</head>
<body>


	<div class="container">
		 <div class="row">
			 <div class="col-8">
			 
				 <h1 class="m-3">Billing details</h1>
				 <form id="formbilling">
				 
				 	<!-- AccountID -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblAccountID">Account ID: </span>
						</div>
						
						<input type="text" id="txtAccountID" name="txtAccountID">
					</div>
					
					<!-- unitUsage -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblunitUsage">unitUsage: </span>
						</div>
						
						<input type="text" id="txtunitUsage" name="txtunitUsage">
					</div>
					
					<!-- month -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblmonth">month: </span>
						</div>
						
						<input type="text" id="txtmonth" name="txtmonth" >
					</div>
					
					<!-- year -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblyear">year: </span>
						</div>
						
						<input type="text" id="txtyear" name="txtyear">
					</div>
					
					<!-- amount -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblamount">amount: </span>
						</div>
						
						<input type="text" id="txtamount" name="txtamount">
					</div>

					
					<div id="alertSuccess" class="alert alert-success"></div>
 					<div id="alertError" class="alert alert-danger"></div>
					
					
					<input type="button" id="btnSave" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidbillIDSave" name="hidbillIDSave" value="">
				 
				 </form>
			 </div>
		 </div>
		
		 <br>
		 <div class="row">
			 <div class="col-12" id="colbilling">
				<%
					Billing billObj = new Billing();
					out.print(billObj.readbilling());
				%>
			 </div>
		 </div>
	</div>

</body>
</html>