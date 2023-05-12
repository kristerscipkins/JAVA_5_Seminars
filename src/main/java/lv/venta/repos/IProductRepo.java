package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Long>{
		
	boolean existsByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
	Product findByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
}