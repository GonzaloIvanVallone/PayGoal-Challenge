package Fintech.PayGoal.Service;

import Fintech.PayGoal.Model.Product;
import Fintech.PayGoal.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductInitializer implements ApplicationRunner {
    @Autowired
    ProductRepository productRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Charging 4 products automatically to avoid doing it with postman");
        Product product = new Product(1L, "shovel", "A excelent tool for digging", 45.00,10);
        Product product2 = new Product(2L, "asado", "Best Argentinian dish", 120.00,30);
        Product product3 = new Product(3L, "coca", "Good with fernet", 25.00,100);
        Product product4 = new Product(4L, "glasses", "For those with myopia and astigmatism", 65.00,3);
        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }
}
