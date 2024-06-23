package pl.edu.wszib.infrastructure.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.api.product.ProductResult;
import pl.edu.wszib.application.product.ProductFacade;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ProductInitData implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(ProductInitData.class);

    private final ProductFacade productFacade;

    public ProductInitData(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @Override
    public void afterPropertiesSet() {
        log.debug("Running product init");
        ProductResult product1 = productFacade.create(new ProductApi(UUID.randomUUID().toString(),
                "Testowy produkt 1",
                BigDecimal.TEN,
                Instant.now(),
                Instant.now()));
        ProductResult product2 = productFacade.create(new ProductApi(UUID.randomUUID().toString(),
                "Testowy produkt 2",
                BigDecimal.TEN,
                Instant.now(),
                Instant.now()));
        log.trace("Created product {}", product1);
        log.trace("Created product {}", product2);
    }
}
