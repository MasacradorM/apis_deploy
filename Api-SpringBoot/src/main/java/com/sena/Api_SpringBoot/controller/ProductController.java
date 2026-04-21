package com.sena.Api_SpringBoot.controller;

import com.sena.Api_SpringBoot.entity.Product;
import com.sena.Api_SpringBoot.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin("*")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // GET / - Mensaje de bienvenida
    @GetMapping
    public Map<String, String> home() {
        return Map.of("message", "API Spring Boot - Gestión de Productos funcionando");
    }

    // GET /products - Listar todos los productos
    @GetMapping("/products")
    public List<Product> getAll() {
        return repository.findAll();
    }

    // GET /products/:id - Obtener producto por ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /products - Crear nuevo producto
    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product saved = repository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /products/:id - Actualizar producto
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return repository.findById(id).map(existing -> {
            existing.setName(product.getName() != null ? product.getName() : existing.getName());
            existing.setPrice(product.getPrice() != null ? product.getPrice() : existing.getPrice());
            existing.setStock(product.getStock() != null ? product.getStock() : existing.getStock());
            existing.setCategory(product.getCategory() != null ? product.getCategory() : existing.getCategory());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /products/:id - Eliminar producto
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Producto eliminado"));
    }
}
