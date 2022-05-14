 $(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 $("#alertError").hide(); 

}); 


$(document).on("click", "#btnSave", function(event)
{
	//Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form validation-------------------
	var status = validatebillForm();
	
	//If not valid
	if (status != true)
	{
		 $("#alertError").text(status);
		 $("#alertError").show();
		return;
	}
	
	//If valid
	$("#formbilling").submit();
	
	var type = ($("#hidbillIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "billingAPI",
 		type : type,
 		data : $("#formbilling").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onbillSaveComplete(response.responseText, status);
 		}
 	}); 
});



function onbillSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#hidMeterIDSave").val("");
 		$("#formMeter")[0].reset();
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidbillIDSave").val($(this).data("billID"));
	$("#txtAccountID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#txtunitUsage").val($(this).closest("tr").find('td:eq(1)').text());
	$("#txtmonth").val($(this).closest("tr").find('td:eq(2)').text());
	$("#txtyear").val($(this).closest("tr").find('td:eq(3)').text());
	$("#txtamount").val($(this).closest("tr").find('td:eq(4)').text());
}); 


// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 		{
 			url : "billingAPI",
 			type : "DELETE",
 			data : "billID=" + $(this).data("billID"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onMeterDeleteComplete(response.responseText, status);
 			}
 		});
}); 

function onbillDeleteComplete(response, status)
{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}


function validatebillForm()
{
	//accountID
	if ($("#txtAccountID").val().trim() == "")
	{
		return "Insert Account ID";
	}
	
	//unitUsage
	if ($("#txtunitUsage").val().trim() == "")
	{
		return "Insert unitUsage";
	}
	
	//month
	if ($("#txtmonth").val() == "")
	{
		return "Insert month";
	}
	
	//year
	if ($("#txtyear").val() == "")
	{
		return "Insert year";
	}

	//amount
	if ($("#txtamount").val() == "")
	{
		return "Insert amount";
	}
	
	return true;
}


// function getbillCard(accountID, unitUsage, month, year, amount)
// {
	
// 	var billing = "";
// 	billing += "<div class=\"student card bg-light m-2\" style=\"max-width: 10rem; float: left;\">";
// 	billing += "<div class=\"card-body\">";
// 	billing += "accountID: " + accountID + ",";
// 	payment += "<br>";
// 	payment += "Amount: " + amount + ",";
// 	payment += "<br>";
// 	payment += "Pay Method: " + payMethod + ",";
// 	payment += "<br>";
// 	payment += "Date: " + payDate;
// 	payment += "</div>";
// 	payment += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">";
// 	payment += "</div>";
	
// 	return payment;
// }

