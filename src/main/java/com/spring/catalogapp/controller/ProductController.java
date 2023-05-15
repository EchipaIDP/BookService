package com.spring.catalogapp.controller;

import com.spring.catalogapp.entity.LoginUser;
import com.spring.catalogapp.service.LoginService;
import com.spring.catalogapp.service.ProductService;
import com.spring.catalogapp.entity.ReturnObject;
import com.spring.catalogapp.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("")
@RequiredArgsConstructor
public class ProductController {

    private final String ROLE_ADMIN = "ROLE_EMPLOYEE";
    private final ProductService productService;
    @Autowired
    private final LoginService loginService;


    @GetMapping("/products.json")
    public ResponseEntity<ReturnObject> getProducts(@RequestParam int page, @RequestParam int size,
                                                    @RequestParam String sortElem, @RequestParam String direction, @RequestParam String token) {

        if (loginService.getMyUser() == null) {
            return ResponseEntity.notFound().build();
        }

        if (!loginService.getMyUser().getToken().equals(token)) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(token);
        return new ResponseEntity<>(productService.getProducts(page, size, sortElem, direction), HttpStatus.ACCEPTED);
    }

    @PostMapping("/products/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product, @RequestParam String token) {
        productService.extracted(product);
        if (loginService.getMyUser() == null) {
            return ResponseEntity.notFound().build();
        }

        if (!loginService.getMyUser().getToken().equals(token)) {
            return ResponseEntity.notFound().build();
        }
        if (!loginService.getMyUser().getRoles().contains(ROLE_ADMIN)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products/filteredList")
    public ResponseEntity<ArrayList<Product>> filterList(@RequestBody Product product, @RequestParam String token) {
        if (loginService.getMyUser() == null) {
            return ResponseEntity.notFound().build();
        }

        if (!loginService.getMyUser().getToken().equals(token)) {
            return ResponseEntity.notFound().build();
        }
        if (!loginService.getMyUser().getRoles().contains(ROLE_ADMIN)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productService.filter(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long productId, @RequestParam String token) {
        if (loginService.getMyUser() == null) {
            return ResponseEntity.notFound().build();
        }

        if (!loginService.getMyUser().getToken().equals(token)) {
            return ResponseEntity.notFound().build();
        }
        if (!loginService.getMyUser().getRoles().contains(ROLE_ADMIN)) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/products/modify")
    public ResponseEntity<?> modifyProduct(@RequestBody Product original, @RequestParam String token) {
        if (loginService.getMyUser() == null) {
            return ResponseEntity.notFound().build();
        }

        if (!loginService.getMyUser().getToken().equals(token)) {
            return ResponseEntity.notFound().build();
        }
        if (!loginService.getMyUser().getRoles().contains(ROLE_ADMIN)) {
            return ResponseEntity.notFound().build();
        }
        productService.update(original);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUser myuser) {
        if (loginService.getMyUser() != null) {
            System.out.println(loginService.getMyUser().getEmail());
        }
        loginService.setMyUser(myuser);
        System.out.println(myuser.toString());
        return ResponseEntity.ok().build();
    }
}
