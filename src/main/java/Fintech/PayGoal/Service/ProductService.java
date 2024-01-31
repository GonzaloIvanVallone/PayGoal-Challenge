package Fintech.PayGoal.Service;

import Fintech.PayGoal.Exceptions.DuplicatedProductException;
import Fintech.PayGoal.Exceptions.ProductNotFoundException;
import Fintech.PayGoal.Model.Product;
import Fintech.PayGoal.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product saveProduct(Product product){
        try{
            Optional<Product> p = productRepository.findByName(product.getName());
            if(p.isPresent()){
                throw new DuplicatedProductException("Product already exist");
            }
            return productRepository.save(product);
        }catch(DuplicatedProductException duplicatedProductException){
            throw duplicatedProductException;
        }catch(Exception e){
            throw new RuntimeException("Product couldn't be saved", e);
        }
    }
    public Optional<Product> updateProduct(Product product){
        try{
            Optional<Product> p = productRepository.findById(product.getId());
            if(p.isPresent()){
                Product newProduct = p.get();
                newProduct.setName(product.getName());
                newProduct.setDescription(product.getDescription());
                newProduct.setPrice(product.getPrice());
                newProduct.setQuantity(product.getQuantity());
                productRepository.save(newProduct);
                return Optional.of(product);
            }
            throw new ProductNotFoundException("Product not found");
        }catch(ProductNotFoundException productNotFoundException){
            throw productNotFoundException;
        }catch(Exception e){
            throw new RuntimeException("Product couldn't be updated", e);
        }
    }
    public Optional<Product> deleteProduct(String name){
        try{
            Optional<Product> p = productRepository.findByName(name);
            if(p.isEmpty()){
                throw new ProductNotFoundException("Product not found");
            }else{
                productRepository.delete(p.get());
                return p;
            }
        }catch(ProductNotFoundException productNotFoundException) {
            throw productNotFoundException;
        }catch(Exception e){
            throw new RuntimeException("Product couldn't be deleted", e);
        }
    }
    public Optional<Product> getProductById(Long id){
        try{
            Optional<Product> p = productRepository.findById(id);
            if(p.isEmpty()){
                throw new ProductNotFoundException("Product not found");
            }
            return p;
        }catch(ProductNotFoundException productNotFoundException) {
            throw productNotFoundException;
        }catch(Exception e){
            throw new RuntimeException("Error while retrieving product", e);
        }
    }
    public Optional<Product> getProductByName(String name){
        try{
            Optional<Product> p = productRepository.findByName(name);
            if(p.isEmpty()){
                throw new ProductNotFoundException("Product not found");
            }
            return p;
        }catch(ProductNotFoundException productNotFoundException) {
            throw productNotFoundException;
        }catch(Exception e){
            throw new RuntimeException("Error while retrieving product", e);
        }
    }
    public List<Product> getAllProductsSorted(){
        try{
            List<Product> productList = productRepository.getAllSorted();
            if(productList.isEmpty()){
                throw new ProductNotFoundException("Products not found");
            }
            return productList;
        }catch(ProductNotFoundException productNotFoundException) {
            throw productNotFoundException;
        }catch(Exception e){
            throw new RuntimeException("Error while retrieving all products", e);
        }
    }
}