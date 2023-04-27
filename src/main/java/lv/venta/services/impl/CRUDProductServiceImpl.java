package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.services.ICRUDProductService;

@Service
public class CRUDProductServiceImpl implements ICRUDProductService {

  private ArrayList < Product > allProductList = new ArrayList < > (Arrays.asList(
    new Product("Watermelon", "Pink", 1.23f, 4),
    new Product("Tomato", "Red", 0.99f, 3),
    new Product("Grapes", "Purple", 12.3f, 4)
  ));

  @Override
  public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
    // TODO check title and description w regex
    if (title != null && description != null && price > 0 && price < 10000 && quantity > 0 && quantity < 100000) {
      boolean isFound = false;
      for (Product product: allProductList) {
        if (product.getTitle().equals(title) && product.getDescription().equals(description) && product.getPrice() == price) {
          product.setQuantity(product.getQuantity() + quantity);
          break;
        }
      }
      if (isFound) {
        Product newProduct = new Product(title, description, price, quantity);
        allProductList.add(newProduct);
      }
    } else {
      throw new Exception("Incorrect params");
    }
  }

  @Override
  public ArrayList < Product > retriveAllProducts() {
    return allProductList;
  }

  @Override
  public Product retriveProductById(long id) throws Exception {
    if (id > 0) {
      for (Product product: allProductList) {
        if (product.getId() == id) {
          return product;
        }
      }
      throw new Exception("There is no product with this Id");
    } else {
      throw new Exception("Incorrect Id needs to be pozitive");
    }
  }

  @Override
  public void updateProductById(long id, String title, String description, float price, int quantity) throws Exception {
    if (id > 0) {
      if (title != null && description != null && price > 0 && price < 10000 && quantity > 0 && quantity < 100000) {
        boolean isFound = false;
        for (Product product: allProductList) {
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

  @Override
  public void deleteProductById(long id) throws Exception {
    Product deletedProduct = retriveProductById(id);
    allProductList.remove(deletedProduct);

  }

}