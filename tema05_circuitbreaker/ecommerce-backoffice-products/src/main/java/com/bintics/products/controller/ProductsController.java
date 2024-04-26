package com.bintics.products.controller;

import com.bintics.products.dto.ProductRequest;
import com.bintics.products.dto.ProductResponse;
import com.bintics.products.service.ProductsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService service;

    private final Environment environment;

    @GetMapping("/ping")
    public String ping() {
        String serverPort = environment.getProperty("local.server.port");
        if (serverPort == null) {
            serverPort = environment.getProperty("server.port");
        }
        return String.format("serverPort: %s", serverPort);
    }

    @GetMapping("/retry/{id}")
    public ResponseEntity retry(@PathVariable("id") String id) {
        log.error("error de reintento: " + id);
        return ResponseEntity.internalServerError().body(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ProductRequest request) {
        log.info("create-product");
        var id = this.service.create(request);
        return ResponseEntity.created(URI.create(String.format("/products/%s", id))).build();
    }

    @GetMapping
    public ResponseEntity getAll() {
        log.info("get-all-product");
        var list = this.service.getAll();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody ProductRequest request) {
        request.setId(id);
        this.service.update(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        ProductResponse product = this.service.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
