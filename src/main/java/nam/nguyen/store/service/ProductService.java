package nam.nguyen.store.service;


import nam.nguyen.store.model.Product;
import nam.nguyen.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getAllProductForCustomer(Integer status) {
        return productRepository.getAllProductForCustomer(status);
    }
    public List<Product> getProductByName(String name) {
        return productRepository.findFoodByName(name);
    }


    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    public Product getProductById(int id) {
        Optional<Product>optional = productRepository.findById(id);
        Product product=null;
        if(optional.isPresent()){
            product=optional.get();

        }else {
            throw new RuntimeException("Product not found for id::"+id);
        }
        return product;
    }


    public void deleteProductById(int id) {
        this.productRepository.deleteById(id);
    }
}
