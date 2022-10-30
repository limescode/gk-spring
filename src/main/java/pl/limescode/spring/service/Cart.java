package pl.limescode.spring.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.limescode.spring.model.Product;
import pl.limescode.spring.repository.ProductRepository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class Cart {
    public static int counter = 0;
    public final ProductRepository productRepository;
    @Getter
    private int instanceNumber;
    @Getter
    private List<Product> products = new ArrayList<>();

    @PostConstruct
    private void init() {
        counter ++;
        instanceNumber = counter;
    }

    public void addToCart(long id) {
        var found = productRepository.getProductById(id);
        if (found.isPresent()) {
            products.add(found.get());
            System.out.println("Product with id='" + id + "' has been successfully added to the cart");
        } else {
            System.out.println("Product with id='" + id + "' hasn't been found in the catalogue");
        }
    }

    public void removeFromCart(long id) {
        var found = products.stream().filter(item -> item.getId() == id).findFirst();
        if (found.isPresent()) {
            products.remove(found.get());
            System.out.println("Product with id='" + id + "' has been successfully removed from the cart");
        } else {
            System.out.println("Product with id='" + id + "' hasn't been found in the cart");
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "instanceNumber=" + instanceNumber +
                ", cart=" + products +
                '}';
    }
}
