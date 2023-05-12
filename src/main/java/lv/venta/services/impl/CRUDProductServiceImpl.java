package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repos.IProductRepo;
import lv.venta.services.ICRUDProductService;

@Service
public class CRUDProductServiceImpl implements ICRUDProductService {

	@Autowired
	private IProductRepo productRepo;

//---------------------------------------------------------------------------------------------------------------------------------------------------------------  
	@Override
	public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
		if (productRepo.exexistsByTitleAndDescriptionAndPrice(title, description, price)) {
			Product product = productRepo.finfindByTitleAndDescriptionAndPrice(title, description, price);
			product.setQuantity(product.getQuantity() + quantity);
			productRepo.save(product);
		} else {
			Product newProduct = new Product(title, description, price, quantity);
			productRepo.save(newProduct);
		}

	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public ArrayList<Product> retriveAllProducts() {
		return (ArrayList<Product>) productRepo.findAll();
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public Product retriveProductById(long id) throws Exception {
		if (id > 0) {

			if (productRepo.existsById(id)) {
				Product temp = productRepo.findById(id).get();
				return temp;
			} else {
				throw new Exception("There is no product with this Id");
			}

		} else {
			throw new Exception("Incorrect Id needs to be pozitive");
		}
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void updateProductById(long id, String title, String description, float price, int quantity)
			throws Exception {
		if (id > 0) {
			if (title != null && description != null && price > 0 && price < 10000 && quantity > 0
					&& quantity < 100000) {
				boolean isFound = false;
				for (Product product : allProductList) {
					if (product.getId() == id) {
						product.setTitle(title);
						product.setDescription(description);
						product.setPrice(price);
						product.setQuantity(quantity);
						break;
					}
				}
				if (isFound) {
					throw new Exception("No product with this id");
				}
			} else {
				throw new Exception("incorrect params");
			}

		} else {
			throw new Exception("ID needs to be positive");
		}

	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void deleteProductById(long id) throws Exception {
		if (productRepo.existsById(id)) {
			productRepo.deleteById(id);
		} else {
			throw new Exception("There is no product with this Id");
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------
}