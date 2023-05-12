package lv.venta.centrollers;

import java.lang.reflect.Array;
import java.security.AllPermission;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.services.ICRUDProductService;

@Controller
public class FirstController {

  @Autowired
  private ICRUDProductService CRUDservice;

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

  @GetMapping("/one-product") //localhost:8080/one-product
  public String getProduct(Model model) {
    Product prod = new Product("Apple", "Tasty", 1.2f, 9);
    model.addAttribute("packet", prod);
    return "one-product"; //will show one product.html
  }

  @GetMapping("/all-products") //localhost:8080/all-products
  public String getAllProducts(Model model) {
    model.addAttribute("packet", CRUDservice.retriveAllProducts());
    return "all-product-page";
  }

  @GetMapping("/all-products-find") //localhost:8080/all-products-find?id=2
  public String getAllProductsFindFunc(@RequestParam("id") long id, Model model) {
    try {
      Product prod = CRUDservice.retriveProductById(id);
      model.addAttribute("packet", prod);
      return "one-product"; //will call one-product-page.html
    } catch (Exception e) {
      model.addAttribute("packetERROR", "Product does not exist!");
      return "err-page"; //will call error-page.html	
    }
  }

  @GetMapping("/all-products/{id}") //localhost:8080/all-products/2
  public String getOneProduct(@PathVariable("id") long id, Model model) {
    try {
      Product prod = CRUDservice.retriveProductById(id);
      model.addAttribute("packet", prod);
      return "one-product"; //will call one-product-page.html
    } catch (Exception e) {
      model.addAttribute("packetERROR", "Product does not exist!");
      return "err-page"; //will call error-page.html	
    }
  }

  @GetMapping("/add-product") //localhost:8080/add-product
  public String getAddProduct(Model model) {
    model.addAttribute("product", new Product());
    return "add-product-page";
  }

  @PostMapping("/add-product")
  public String postAddProduct(@Valid Product product, BindingResult result) {
	  if (!result.hasErrors()) {
		
	
		  try {
			  CRUDservice.addNewProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
			  return "redirect:/all-products";
		  } catch (Exception e) {
			  return "redirect:/error"; //will call localhost:8080/error
		  }
	  }
	  else {
		return "add-product-page";
	}

  }

  @GetMapping("/update-product/{id}") //localhost:8080/update-product/2
  public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {

    try {
      Product prod = CRUDservice.retriveProductById(id);
      model.addAttribute("product", prod);
      return "update-product-page"; //will call update-product-page.html
    } catch (Exception e) {
      model.addAttribute("packetError", e.getMessage());
      return "error-page"; //will call error-page.html
    }

  }

  @PostMapping("/update-product/{id}")
  public String postUpdateProductFunc(@PathVariable("id") long id, Product product) //edited product
  {
    try {
      CRUDservice.updateProductById(id, product.getTitle(), product.getDescription(),
        product.getPrice(), product.getQuantity());
      return "redirect:/all-products/" + id; //will call localhost:8080/all-products/2 endpoint
    } catch (Exception e) {
      return "redirect:/error"; //will call localhost:8080/error
    }

  }

  @GetMapping("/error") //localhost:8080/error
  public String getErrorFunc(Model model) {
    model.addAttribute("packetError", "Wrong id");
    return "error-page"; //will call error-page.html
  }

  @GetMapping("/delete-product/{id}") //localhost:8080/delete-product/2
  public String getDeleteProductFunc(@PathVariable("id") long id, Model model) {
    try {
      CRUDservice.deleteProductById(id);
      model.addAttribute("packet", CRUDservice.retriveAllProducts());
      return "all-products-page"; //will call all-products-page.html
    } catch (Exception e) {
      model.addAttribute("packetError", e.getMessage());
      return "error-page"; //will call error-page.html
    }

  }

}