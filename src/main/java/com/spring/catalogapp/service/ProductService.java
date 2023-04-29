package com.spring.catalogapp.service;

import com.spring.catalogapp.entity.ReturnObject;
import com.spring.catalogapp.entity.Product;
import com.spring.catalogapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private Example<Product> example = Example.of(new Product(null, null, null, null));

    public boolean idExists(Long id, ArrayList<Product> list) {
        final Optional<Product> first = list.stream().filter(p -> p.getID().equals(id)).findFirst();
        return !first.isEmpty();
    }

    public ReturnObject getProducts(final int page, final int pageSize,
                                    String sortElem, final String direction) {
        final ArrayList<Product> response;
        final ArrayList<Product> returnValue = new ArrayList<>();
        final int currentSize;

        if (sortElem.equals("id")) {
            sortElem = "ID";
        }

        if (direction.equalsIgnoreCase("asc")) {
            response = (ArrayList<Product>) productRepository
                    .findAll(this.example, Sort.by(sortElem).ascending());
        } else {
            response = (ArrayList<Product>) productRepository
                    .findAll(this.example, Sort.by(sortElem).descending());
        }

        currentSize = response.size();

        for (int i = page * pageSize; i < (page + 1) * pageSize && i < response.size(); i++) {
            returnValue.add(response.get(i));
        }

        int x = currentSize % pageSize != 0 ? currentSize / pageSize : (currentSize - 1) / pageSize;

        return new ReturnObject(returnValue, x);
    }

    public void extracted(Product product) {
        for (int i = 1; i <= productRepository.findAll().size() + 2; i++) {
            if (!this.idExists((long) i, (ArrayList<Product>) productRepository.findAll())) {
                product.setID((long) i);
                break;
            }
        }
        productRepository.save(product);
    }

    public ArrayList<Product> filter(Product product) {

        if (product.getName().equals(""))
            product.setName(null);

        if (product.getCategory().equals(""))
            product.setCategory(null);

        if (product.getDescription().equals(""))
            product.setDescription(null);

        this.example = Example.of(product);

        return (ArrayList<Product>) productRepository.findAll(example);
    }

    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    public void update(Product original) {
        productRepository.save(original);
    }
}
