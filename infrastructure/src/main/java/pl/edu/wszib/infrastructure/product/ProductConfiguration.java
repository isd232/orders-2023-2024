package pl.edu.wszib.infrastructure.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.edu.wszib.application.product.ProductFacade;
import pl.edu.wszib.application.product.ProductRepository;

@Configuration(proxyBeanMethods = false)
public class ProductConfiguration {

    @Bean
    ProductFacade productFacade(final ProductRepository productRepository) {
        return new ProductFacade(productRepository);
    }

//    @Bean
//    ProductRepository productRepository() {
//        return new InMemoryProductRepository();
//    }

    @Bean
    ProductRepository springDataJpaProductRepository(final SpringDataJpaProductDao productDao) {
        return new SpringDataJpaProductRepository(productDao);
    }

    @Profile("!prod")
//    @Profile("dev")
    @Bean
    ProductInitData productInitData(final ProductFacade productFacade) {
        return new ProductInitData(productFacade);
    }
}
