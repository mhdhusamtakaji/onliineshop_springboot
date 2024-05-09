package com.onlineshopping.onlineshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.onlineshopping.onlineshop.models.ProductModel;
import com.onlineshopping.onlineshop.services.ProductRepo;

import org.springframework.ui.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductRepo productRepo;
		
	// Display list of products
	@GetMapping({"", "/"})
	public String listProducts(Model model) {
		model.addAttribute("products", productRepo.findAll());
		System.out.println(productRepo.findAll());
		return "products/products";
	}

	// Display form for adding a new product
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "products/add-product";
    }

	// Process form submission to add a new product
    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductModel product, RedirectAttributes redirectAttributes) {
        productRepo.save(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully.");
        return "redirect:/products";
    }

	// Display form for editing an existing product
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") int id, Model model) {
        ProductModel product = productRepo.findById(id).get();
        model.addAttribute("product", product);
        return "products/edit-product";
    }

	// Process form submission to update an existing product
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") int id, @ModelAttribute ProductModel product, RedirectAttributes redirectAttributes) {
        productRepo.save(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully.");
        return "redirect:/products";
    }

	// Process request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		final ProductModel product = productRepo.findById(id).get();
        productRepo.delete(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully.");
        return "redirect:/products";
    }

	
	// @GetMapping("/{id}")
	// public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
	//     ProductModel product = productRepo.findById(id).orElse(null);
	//     if (product != null) {
	//         return ResponseEntity.ok(product);
	//     } else {
	//     	CustomResponse errorResponse = new CustomResponse("Product not found");
	//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	//     }
	// }
	
	// @PostMapping({"", "/"})
	// public ResponseEntity<?> addProduct(@RequestBody ProductModel product) {
	// 	ProductModel savedProduct = productRepo.save(product);
	// 	  return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);	
	// }
	
	// @PutMapping("/{id}")
	// public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody ProductModel productDetails) {
	// 	final ProductModel product = productRepo.findById(id).orElse(null);
	// 	if (product == null) {
	// 		CustomResponse error = new CustomResponse("Product not found");
	// 		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	// 	} else {
	// 		 if (productDetails.getName() != null) {
	// 	            product.setName(productDetails.getName());
	// 	        }
	// 	        if (productDetails.getPrice() != null) {
	// 	            product.setPrice(productDetails.getPrice());
	// 	        }
	// 	        if (productDetails.getDescription() != null) {
	// 	            product.setDescription(productDetails.getDescription());
	// 	        }
			
	// 		ProductModel savedProduct = productRepo.save(product);
	// 		return ResponseEntity.ok(savedProduct);
	// 	}
	// }
	
	// @DeleteMapping("/{id}")
	// public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
	// 	ProductModel product = productRepo.findById(id).orElse(null);
		
	// 	if (product == null) {
	// 		CustomResponse error = new CustomResponse("Product not found");
	// 		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	// 	}
	// 	else {
	// 		productRepo.delete(product);
	// 		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse("Deleted Successfully"));
	// 	}
	// }
}