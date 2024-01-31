package Fintech.PayGoal.Repository;

import Fintech.PayGoal.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> getAllSorted();

    Optional<Product> findByName(String name);
}
