package smit.teste.crud.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
public class ProductController {
    @GetMapping
    public ResponseEntity<String> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
