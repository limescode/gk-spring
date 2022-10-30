package pl.limescode.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.limescode.spring.repository.ProductRepository;
import pl.limescode.spring.service.Cart;

public class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext("pl.limescode.spring");

        var productRepository = context.getBean(ProductRepository.class);
        var catalogue = productRepository.getCatalogue();

        var cart1 = context.getBean(Cart.class);
        System.out.println("Has been created a new cart: " + cart1);
        for (int i = 0; i < catalogue.size(); i++) {
            if (i % 2 == 0) {
                cart1.addToCart(catalogue.get(i).getId());
            }
        }
        System.out.println(cart1);
        System.out.println("-------------------------------------------");

        var cart2 = context.getBean(Cart.class);
        System.out.println("Has been created a new cart: " + cart2);
        for (int i = 0; i < catalogue.size(); i++) {
            if (i % 2 == 1) {
                cart2.addToCart(catalogue.get(i).getId());
            }
        }
        System.out.println(cart2);
        System.out.println("-------------------------------------------");

        for (int i = 0; i <= cart1.getProducts().size() - 1; i++) {
            cart1.removeFromCart(cart1.getProducts().get(i).getId());
        }
        System.out.println(cart1);
    }
}
