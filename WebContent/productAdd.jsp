<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Product Add</title>
	<script src="assetsz/js/bootstrap.min.js"></script>
    <script src="assetsz/js/croppie.js"></script>
    <link rel="stylesheet" href="assetsz/css/croppie.css" />

    <link href="assetsz/css/bootstrap.min.css" rel="stylesheet">
    <script src="assetsz/js/sweetalert.min.js"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>

	<div class="container">
		<form id="productFrom">
			<div class="form-group">
				<label for="name">Product Name</label>
				<input type="text" class="form-control" id="name" placeholder="Enter Name" required>
			</div>
			<div class="form-group">
				<label for="Price">Product Price</label>
				<input type="number" step="0.01" min="0" class="form-control" id="price" placeholder="Enter Price" required>
			</div>
			<div class="form-group">
				<label for="category">Product Category</label>
				<select id="category" class="form-control" required>
		        	<option value="" >Select Category</option>
		        	<option value="1" >Electronics</option>
		        	<option value="2" >Fashion</option>
		        	<option value="3" >Toys</option>
		        	<option value="4" >Music</option>
		        	<option value="5" >Accessories</option>
		        </select>
	        </div>
	        <div class="form-group">
				<label for="quantity">Product Category</label>
	        	<input type="number" id="quantity" class="form-control" min="0" step="1" placeholder="Enter Quantity" required>
	        </div>
			<input type="submit"  class="btn btn-primary" value="Add Product">
		</form>
	</div>

</body>
</html>
<script>
	
	$(document).ready(function(){
		
		$("#productFrom").submit(function(e){
	        	
	    	var jsonfile = JSON.stringify({
				"name" : $('#name').val(),
				"price" : $('#price').val(),
				"category" : $('#category').val(),
				"quantity" : $('#quantity').val()
			});
			
			var ans = $.ajax({
				type : 'POST',
				url : 'http://localhost:8080/pafAssignmentProduct/rest/product/product',
				dataType : 'json',
				contentType : 'application/json',
				data : jsonfile
			});
			
			ans.done(function(data){
				if(data['success']=="1"){
					swal("Success!", "Product Added Successfull!", "success");
					$('#name').val("");
					$('#price').val("");
					$('#category').val("");
					$('#quantity').val("");
				}else if(data['success']=="0"){
					swal({
			            title: "Error",
			            text: "This Product is Already Exists",
			            icon: "warning",
			            dangerMode: true,
			        });
					$('#name').val("");
					$('#category').val("");
				}
			});
			ans.fail(function(data){
				swal({
	                title: "Error",
	                text: "Connection Error !",
	                icon: "warning",
	                dangerMode: true,
	            });
			});
			
			e.preventDefault();
		});
		
	});
	
</script>