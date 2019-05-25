package paf.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import paf.connection.DBConnect;
import paf.model.Product;


public class productService {

private int success;
	
	public void addProduct(Product product) {
		Connection connection;
		PreparedStatement preparedStatement;
		String name=null;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//check product name
			preparedStatement = connection.prepareStatement("select * from product where name=? and category=?");
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getCategory());
			ResultSet rs = preparedStatement.executeQuery();
			 
			while(rs.next())
			{
				name = rs.getString(2);	
			}
			
			if(name==null) {
				
				//insert value
				preparedStatement = connection.prepareStatement("insert into product (name,price,category,quantity) values (?,?,?,?)");
				preparedStatement.setString(1, product.getName());
				preparedStatement.setDouble(2, product.getPrice());
				preparedStatement.setInt(3,product.getCategory());
				preparedStatement.setInt(4,product.getQuantity());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess(1);
				
			}else {
				setSuccess(0);
			}
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}
	
	public ArrayList<Product> getProduct() {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from product");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Product product = new Product();
				
				product.setId(Integer.parseInt(resultSet.getString(1)));
				product.setName(resultSet.getString(2));
				product.setPrice(Double.parseDouble(resultSet.getString(3)));
				product.setCategory(Integer.parseInt(resultSet.getString(4)));
				product.setQuantity(Integer.parseInt(resultSet.getString(5)));
				
				productList.add(product);
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return productList;
	}

	public void editProduct(Product product) {
		Connection connection;
		PreparedStatement preparedStatement;
		String name=null;
		int id=0;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//check product name
			preparedStatement = connection.prepareStatement("select * from product where name=? and category=?");
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getCategory());
			ResultSet rs = preparedStatement.executeQuery();
			 
			while(rs.next())
			{
				id= rs.getInt(1);
				name = rs.getString(2);	
			}
			
			if(name==null||id==product.getId()) {
				
				//insert value
				preparedStatement = connection.prepareStatement("UPDATE product SET name=?,price=?,category=?,quantity=? where id=?");
				preparedStatement.setString(1, product.getName());
				preparedStatement.setDouble(2, product.getPrice());
				preparedStatement.setInt(3,product.getCategory());
				preparedStatement.setInt(4,product.getQuantity());
				preparedStatement.setInt(5,product.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess(1);
				
			}else {
				setSuccess(0);
			}
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteProduct(Product product) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//delete product
			preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id=?");
			preparedStatement.setInt(1, product.getId());
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}
	
}
