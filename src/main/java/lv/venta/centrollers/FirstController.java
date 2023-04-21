package lv.venta.centrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;

@Controller
public class FirstController {

	@GetMapping("/hello") //localhost:8080/hello
	public String getHelloFunc() {
		System.out.println("hello world");
		return "hello-page"; //there will be hello-page.html
	}
	
	@GetMapping("/msg") //localhost:8080/msg
	public String getMsgFunc (Model model) {
		model.addAttribute("packet", "Hello world! x2");
		return "msg-page"; //will show msg-page.html
	}
	
	@GetMapping("/one-product") //localhost:8080/onne-product
	public String getProduct (Model model) {
	Product prod = new Product ("Apple", "Tasty", 1.2f, 9);
	model.addAttribute("packet", prod);
	return"one-product"; //will show one product.html
	}
	
	
	
	
	
}
