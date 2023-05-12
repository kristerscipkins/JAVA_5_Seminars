package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Long>{
		
	boolean exexistsByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
	Product finfindByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
}