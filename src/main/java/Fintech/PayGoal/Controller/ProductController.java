package Fintech.PayGoal.Controller;

import Fintech.PayGoal.Exceptions.DuplicatedProductException;
import Fintech.PayGoal.Exceptions.ProductNotFoundException;
import Fintech.PayGoal.Model.Product;
import Fintech.PayGoal.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product){
        try{
            return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
        }catch(DuplicatedProductException duplicatedProductException){
            return new ResponseEntity<>(duplicatedProductException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            return new ResponseEntity<>("Error while saving the product", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam("name") String name){
        try{
            return new ResponseEntity<>(productService.deleteProduct(name), HttpStatus.OK);
        }catch(ProductNotFoundException productNotFoundException){
            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>("Error while deleting the product", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        try{
            return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
        }catch(ProductNotFoundException productNotFoundException){
            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>("Error while updating the product", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/product/id")
    public ResponseEntity<?> getProductById(@RequestParam("id") Long id){
        try{
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }catch(ProductNotFoundException productNotFoundException){
            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Error while searching for the product", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/product/name")
    public ResponseEntity<?> getProductByName(@RequestParam("name") String name){
        try{
            return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
        }catch(ProductNotFoundException productNotFoundException){
            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Error while searching for the product", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/product/all")
    public ResponseEntity<?> getAllProducts(){
        try{
            return new ResponseEntity<>(productService.getAllProductsSorted(), HttpStatus.OK);
        }catch(ProductNotFoundException productNotFoundException){
            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>("Error while searching for the product", HttpStatus.BAD_REQUEST);
        }
    }
}