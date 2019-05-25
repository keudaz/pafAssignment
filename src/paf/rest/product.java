package paf.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

import paf.model.Product;
import paf.service.productService;

@Path("/product")
public class product {
	
	@POST
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String myMethod(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object product
		Product product =  new Product();
		
		product.setName(jobj.get("name").toString());
		product.setPrice(Double.parseDouble(jobj.get("price").toString()));
		product.setCategory(Integer.parseInt(jobj.get("category").toString()));
		product.setQuantity(Integer.parseInt(jobj.get("quantity").toString()));
		
		productService newProduct = new productService();
		newProduct.addProduct(product);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(newProduct.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/editproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String editProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object product
		Product product =  new Product();
		
		product.setId(Integer.parseInt(jobj.get("id").toString()));
		product.setName(jobj.get("name").toString());
		product.setPrice(Double.parseDouble(jobj.get("price").toString()));
		product.setCategory(Integer.parseInt(jobj.get("category").toString()));
		product.setQuantity(Integer.parseInt(jobj.get("quantity").toString()));
		
		productService newProduct = new productService();
		newProduct.editProduct(product);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(newProduct.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/deleteproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String deleteProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object product
		Product product =  new Product();
		
		product.setId(Integer.parseInt(jobj.get("id").toString()));
		
		productService newProduct = new productService();
		newProduct.deleteProduct(product);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(newProduct.getSuccess()));
		
		return json.toString();
		
	}

}
