package pl.edu.wszib.infrastructure.product;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.api.product.ProductResult;
import pl.edu.wszib.application.product.ProductFacade;

import java.util.Collection;

@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    private final ProductFacade productFacade;

    public ProductEndpoint(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public Collection<ProductApi> getAll() {
        return productFacade.getAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable("productId") String productId) {
        final ProductResult result = productFacade.getById(productId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.success());
        } else {
            if (result.code() == ProductResult.Code.NOT_FUND) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().body(result);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final ProductApi product) {
        final ProductResult result = productFacade.create(product);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.success());
        } else {
            if (result.code() == ProductResult.Code.ALREADY_EXISTS) {
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(409))
                        .body(result);
            }
            return ResponseEntity.internalServerError().body(result);
        }
    }

}
