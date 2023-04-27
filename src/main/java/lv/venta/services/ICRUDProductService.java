package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface ICRUDProductService {
	
	//CRUD
	
	//C - create
	public abstract void addNewProduct(String title, String description, float price, int quantity) throws Exception;
	
	//R - read - all
	public abstract ArrayList<Product> retriveAllProducts(); 
	
	//R -  - by id
	public abstract Product retriveProductById(long id) throws Exception;
	
	//U - update
	public abstract void updateProductById(long id, String title, String description, float price, int quantity) throws Exception;
	
	//D - delete
	public abstract void deleteProductById(long id) throws Exception;
	
	
}
