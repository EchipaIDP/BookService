package com.spring.catalogapp.controller;

import com.spring.catalogapp.service.ProductService;
import com.spring.catalogapp.entity.ReturnObject;
import com.spring.catalogapp.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products.json")
    public ResponseEntity<ReturnObject> getProducts(@RequestParam int page, @RequestParam int size,
                                                    @RequestParam String sortElem, @RequestParam String direction) {
        return new ResponseEntity<>(productService.getProducts(page, size, sortElem, direction), HttpStatus.ACCEPTED);
    }

    @PostMapping("/products/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        productService.extracted(product);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/products/filteredList")
    public ResponseEntity<ArrayList<Product>> filterList(@RequestBody Product product) {
        return new ResponseEntity<>(productService.filter(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("products/modify")
    public ResponseEntity<?> modifyProduct(@RequestBody Product original) {
        productService.update(original);
        return ResponseEntity.ok().build();
    }
}
