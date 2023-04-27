package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IFilteringProduct {
	
	//filter product: price less than X
	public abstract ArrayList<Product> filterByPriceLessThan(float priceThreshold)  throws Exception;
	
	//filter product: quantity less than X
	public abstract ArrayList<Product> quantityLessThan(int quantity)  throws Exception;
	
	//filter product: sorting
	public abstract ArrayList<Product> sort();
	
}
