package lv.venta.centrollers;

import java.lang.reflect.Array;
import java.security.AllPermission;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;

@Controller
public class FirstController {

  private ArrayList < Product > allProductList = new ArrayList < > (Arrays.asList(
    new Product("Watermelon", "Pink", 1.23f, 4),
    new Product("Tomato", "Red", 0.99f, 3),
    new Product("Grapes", "Purple", 12.3f, 4)
  ));

  @GetMapping("/hello") //localhost:8080/hello
  public String getHelloFunc() {
    System.out.println("hello world");
    return "hello-page"; //there will be hello-page.html
  }

  @GetMapping("/msg") //localhost:8080/msg
  public String getMsgFunc(Model model) {
    model.addAttribute("packet", "Hello world! x2");
    return "msg-page"; //will show msg-page.html
  }

  @GetMapping("/one-product") //localhost:8080/onne-product
  public String getProduct(Model model) {
    Product prod = new Product("Apple", "Tasty", 1.2f, 9);
    model.addAttribute("packet", prod);
    return "one-product"; //will show one product.html
  }

  @GetMapping("/all-products") //localhost:8080/all-products
  public String getAllProducts(Model model) {
    model.addAttribute("packet", allProductList);
    return "all-product-page";
  }

  @GetMapping("/all-products-find") //localhost:8080/all-products-find?id=2
  public String getAllProductsFindFunc(@RequestParam("id") long id, Model model) {
    if (id > 0) {
      for (Product temp: allProductList) {
        if (temp.getId() == id) {
          model.addAttribute("packet", temp);
          return "one-product"; //will call one-product-page.html
        }
      }
    }
    model.addAttribute("packetERROR", "Product does not exist!");
    return "err-page"; //will call error-page.html	
  }

  @GetMapping("/all-products/{id}") //localhost:8080/all-products/2
  public String getOneProduct(@PathVariable("id") long id, Model model) {
    if (id > 0) {
      for (Product product: allProductList) {
        if (product.getId() == id) {
          model.addAttribute("packet", product);
          return "one-product";
        }
      }
    }
    model.addAttribute("packet-error", "Product does not exist");
    return "err-page";
  }

  @GetMapping("/add-product") //localhost:8080/add-product
  public String getAddProduct(Model model) {
    model.addAttribute("product", new Product());
    return "add-product-page";
  }

  @PostMapping("/add-product")
  public String postAddProduct(Product product) {
    //TODO check if this product already exists
    Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
    allProductList.add(newProduct);
    return "redirect:/all-products";
  }

  @GetMapping("/update-product/{id}") //localhost:8080/update-product/2
  public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {
    if (id > 0) {
      for (Product temp: allProductList) {
        if (temp.getId() == id) {
          model.addAttribute("product", temp);
          return "update-product"; //will call update-product-page.html
        }
      }
    }

    model.addAttribute("packetError", "Wrong ID");
    return "err-page"; //will call error-page.html

  }

  @PostMapping("/update-product/{id}")
  public String postUpdateProductFunc(@PathVariable("id") long id, Product product) //edited product
  {
    for (Product temp: allProductList) {
      if (temp.getId() == id) {
        temp.setTitle(product.getTitle());
        temp.setDescription(product.getDescription());
        temp.setPrice(product.getPrice());
        temp.setQuantity(product.getQuantity());

        return "redirect:/all-products/" + id; //will call localhost:8080/all-products/2 endpoint
      }

    }

    return "redirect:/error"; //will call localhost:8080/error

  }

  @GetMapping("/error") //localhost:8080/error
  public String getErrorFunc(Model model) {
    model.addAttribute("packetError", "Wrong id");
    return "err-page"; //will call error-page.html
  }
  
  @GetMapping("/delete-product/{id}") //localhost:8080/update-product/2
  public String getDeleteProduct(@PathVariable("id") long id, Model model) {
    if (id > 0) {
      for (Product temp: allProductList) {
        if (temp.getId() == id) {
          allProductList.remove(temp);
          model.addAttribute("packet", allProductList);
          return "all-product-page"; //will call update-product-page.html
        }
      }
    }

    model.addAttribute("packetError", "Wrong ID");
    return "err-page"; //will call error-page.html

  }
}