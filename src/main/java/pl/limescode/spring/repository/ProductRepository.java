package pl.limescode.spring.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import pl.limescode.spring.model.Product;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    public final int PRODUCT_CATALOGUE_SIZE = 5;
    @Getter
    private List<Product> catalogue = new ArrayList<>();

    @PostConstruct
    private void initializeProductCatalogue() {
        for (int i = 1; i <= PRODUCT_CATALOGUE_SIZE; i++) {
            catalogue.add(
                    new Product((long) i, "Product " + i, BigDecimal.valueOf(i * 10))
            );
        }
    }

    public Optional<Product> getProductById(long id) {
        return catalogue.stream().filter(item -> item.getId() == id).findFirst();
    }

}
