SPRING_BOOT_CODE: '==========================================================
PROJET SPRING BOOT GENERE: ProductCatalogSystem
==========================================================



========================================
FICHIER: pom.xml
========================================
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.example</groupId>
    <artifactId>productcatalogsystem</artifactId>
    <version>1.0.0</version>
    <name>ProductCatalogSystem</name>
    <description>Generated Spring Boot Application with Jakarta EE</description>
    
    <properties>
        <java.version>17</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- PostgreSQL Driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- H2 for testing (optional) -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        
        <!-- Spring Boot DevTools (optional) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        
        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

========================================
FICHIER: src/main/resources/application.yml
========================================
spring:
  application:
    name: ProductCatalogSystem
  
  # PostgreSQL Configuration
  datasource:
    url: jdbc:postgresql://localhost:5432/productcatalogsystem_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    
    # Connection Pool Configuration
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  
  # JPA/Hibernate Configuration
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        jdbc:
          lob:
            non_contextual_creation: true
        default_schema: public
    open-in-view: false

# Server Configuration
server:
  port: 8080
  error:
    include-message: always
    include-binding-errors: always

# Logging Configuration
logging:
  level:
    root: INFO
    com.example.productcatalogsystem: DEBUG
    org.springframework.web: INFO
    org.hibernate: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

========================================
FICHIER: src/main/resources/application-dev.yml
========================================
spring:
  # Development Database (H2 or local PostgreSQL)
  datasource:
    url: jdbc:postgresql://localhost:5432/productcatalogsystem_dev
    username: postgres
    password: postgres
  
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true

logging:
  level:
    com.example.productcatalogsystem: TRACE

========================================
FICHIER: src/main/resources/application-prod.yml
========================================
spring:
  # Production Database
  datasource:
    url: ${DATABASE_URL:jdbc:postgresql://localhost:5432/productcatalogsystem_prod}
    username: ${DATABASE_USERNAME:postgres}
    password: ${DATABASE_PASSWORD}
    
    hikari:
      maximum-pool-size: 20
  
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

logging:
  level:
    root: WARN
    com.example.productcatalogsystem: INFO

========================================
 FICHIER: src/main/java/com.example.productcatalogsystem/Application.java
========================================
package com.example.productcatalogsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main Application class for ProductCatalogSystem
 * Spring Boot application with Jakarta EE and PostgreSQL
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("
========================================");
        System.out.println(" ProductCatalogSystem Application Started");
        System.out.println("Database: PostgreSQL");
        System.out.println("Server: http://localhost:8080");
        System.out.println("API Base: http://localhost:8080/api");
        System.out.println("========================================
");
    }
}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.entity/Product.java
========================================
package com.example.productcatalogsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;
import java.util.Set;

/**
 * Entity Product Table: products
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid", nullable = false, unique = true)
    private Integer productId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;


}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.repository/ProductRepository.java
========================================
package com.example.productcatalogsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.productcatalogsystem.entity.Product;

/**
 * Repository interface for Product
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    

    // Exemple: List<Product> findByName(String name);
    // Exemple: Optional<Product> findByEmail(String email);
    
}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.dto/ProductRequestDTO.java
========================================
package com.example.productcatalogsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO ProductRequestDTO
 * Type: REQUEST
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    @NotNull
    private STRING name;


}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.dto/ProductResponseDTO.java
========================================
package com.example.productcatalogsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO ProductResponseDTO
 * Type: RESPONSE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    @NotNull
    private INTEGER productId;

    @NotNull
    private STRING name;
}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.mapper/ProductMapper.java
========================================
package com.example.productcatalogsystem.mapper;

import org.springframework.stereotype.Component;
import com.example.productcatalogsystem.entity.Product;
import com.example.productcatalogsystem.dto.ProductRequestDTO;
import com.example.productcatalogsystem.dto.ProductResponseDTO;

/**
 * Mapper for Product entity and DTOs
 */
@Component
public class ProductMapper {

    /**
     * Convert RequestDTO to Entity
     */
    public Product toEntity(ProductRequestDTO dto) {
        if (dto == null) return null;
        
        return Product.builder()
                .name(dto.getName())
                .build();
    }

    /**
     * Convert Entity to ResponseDTO
     */
    public ProductResponseDTO toResponseDTO(Product entity) {
        if (entity == null) return null;
        
        return ProductResponseDTO.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .build();
    }

    /**
     * Update existing entity from RequestDTO
     */
    public void updateEntityFromDTO(ProductRequestDTO dto, Product entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
    }
}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.service/ProductService.java
========================================
package com.example.productcatalogsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;
import com.example.productcatalogsystem.repository.ProductRepository;
import com.example.productcatalogsystem.entity.Product;
import com.example.productcatalogsystem.mapper.ProductMapper;
import com.example.productcatalogsystem.dto.ProductRequestDTO;
import com.example.productcatalogsystem.dto.ProductResponseDTO;

/**
 * Service for Product
 * Business logic layer using DTOs
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    /**
     * Create a new Product
     */
    public ProductResponseDTO create(ProductRequestDTO requestDTO) {
        log.debug("Creating new Product: {}", requestDTO);
        Product entity = mapper.toEntity(requestDTO);
        Product saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    /**
     * Find Product by ID
     */
    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        log.debug("Finding Product with id: {}", id);
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    /**
     * Find all Products
     */
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        log.debug("Finding all Products");
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update an existing Product
     */
    public ProductResponseDTO update(Long id, ProductRequestDTO requestDTO) {
        log.debug("Updating Product with id: {}", id);
        Product entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        mapper.updateEntityFromDTO(requestDTO, entity);
        Product updated = repository.save(entity);
        return mapper.toResponseDTO(updated);
    }

    /**
     * Delete Product by ID
     */
    public void deleteById(Long id) {
        log.debug("Deleting Product with id: {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Check if Product exists
     */
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.controller/ProductController.java
========================================
package com.example.productcatalogsystem.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import java.util.List;
import com.example.productcatalogsystem.service.ProductService;
import com.example.productcatalogsystem.dto.ProductRequestDTO;
import com.example.productcatalogsystem.dto.ProductResponseDTO;

/**
 * REST Controller for Product
 * Exposes CRUD operations via HTTP endpoints
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;

    /**
     * getProductById
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        log.info("GET /api/products/{id}");
        ProductResponseDTO response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * getAllProducts
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        log.info("GET /api/products");
        List<ProductResponseDTO> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    /**
     * createProduct
     */
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO request) {
        log.info("POST /api/products");
        ProductResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * updateProduct
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO request) {
        log.info("PUT /api/products/{id}");
        ProductResponseDTO response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * deleteProduct
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/products/{id}");
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

========================================
FICHIER: src/main/java/com.example.productcatalogsystem.entity/Category.java
========================================
package com.example.productcatalogsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;
import java.util.Set;

/**
 * Entity Category Table: categories
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid", nullable = false, unique = true)
    private Integer categoryId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Product> products;


}

========================================
Ã°ÂŸÂ“Â„ FICHIER: src/main/java/com.example.productcatalogsystem.repository/CategoryRepository.java
========================================
package com.example.productcatalogsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.productcatalogsystem.entity.Category;

/**
 * Repository interface for Category
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // MÃƒÂ©thodes de requÃƒÂªte personnalisÃƒÂ©es
    // Exemple: List<Category> findByName(String name);
    // Exemple: Optional<Category> findByEmail(String email);
    
}

========================================
Ã°ÂŸÂ“Â„ FICHIER: src/main/java/com.example.productcatalogsystem.dto/CategoryRequestDTO.java
========================================
package com.example.productcatalogsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO CategoryRequestDTO
 * Type: REQUEST
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDTO {

    @NotNull
    private STRING name;


}

========================================
Ã°ÂŸÂ“Â„ FICHIER: src/main/java/com.example.productcatalogsystem.dto/CategoryResponseDTO.java
========================================
package com.example.productcatalogsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO CategoryResponseDTO
 * Type: RESPONSE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDTO {

    @NotNull
    private INTEGER categoryId;

    @NotNull
    private STRING name;


}

========================================
Ã°ÂŸÂ“Â„ FICHIER: src/main/java/com.example.productcatalogsystem.mapper/CategoryMapper.java
========================================
package com.example.productcatalogsystem.mapper;

import org.springframework.stereotype.Component;
import com.example.productcatalogsystem.entity.Category;
import com.example.productcatalogsystem.dto.CategoryRequestDTO;
import com.example.productcatalogsystem.dto.CategoryResponseDTO;

/**
 * Mapper for Category entity and DTOs
 */
@Component
public class CategoryMapper {

    /**
     * Convert RequestDTO to Entity
     */
    public Category toEntity(CategoryRequestDTO dto) {
        if (dto == null) return null;
        
        return Category.builder()
                .name(dto.getName())
                .build();
    }

    /**
     * Convert Entity to ResponseDTO
     */
    public CategoryResponseDTO toResponseDTO(Category entity) {
        if (entity == null) return null;
        
        return CategoryResponseDTO.builder()
                .categoryId(entity.getCategoryId())
                .name(entity.getName())
                .build();
    }

    /**
     * Update existing entity from RequestDTO
     */
    public void updateEntityFromDTO(CategoryRequestDTO dto, Category entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
    }
}

========================================
Ã°ÂŸÂ“Â„ FICHIER: src/main/java/com.example.productcatalogsystem.service/CategoryService.java
========================================
package com.example.productcatalogsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;
import com.example.productcatalogsystem.repository.CategoryRepository;
import com.example.productcatalogsystem.entity.Category;
import com.example.productcatalogsystem.mapper.CategoryMapper;
import com.example.productcatalogsystem.dto.CategoryRequestDTO;
import com.example.productcatalogsystem.dto.CategoryResponseDTO;

/**
 * Service for Category
 * Business logic layer using DTOs
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    /**
     * Create a new Category
     */
    public CategoryResponseDTO create(CategoryRequestDTO requestDTO) {
        log.debug("Creating new Category: {}", requestDTO);
        Category entity = mapper.toEntity(requestDTO);
        Category saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    /**
     * Find Category by ID
     */
    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {
        log.debug("Finding Category with id: {}", id);
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    /**
     * Find all Categorys
     */
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {
        log.debug("Finding all Categorys");
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update an existing Category
     */
    public CategoryResponseDTO update(Long id, CategoryRequestDTO requestDTO) {
        log.debug("Updating Category with id: {}", id);
        Category entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        mapper.updateEntityFromDTO(requestDTO, entity);
        Category updated = repository.save(entity);
        return mapper.toResponseDTO(updated);
    }

    /**
     * Delete Category by ID
     */
    public void deleteById(Long id) {
        log.debug("Deleting Category with id: {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Check if Category exists
     */
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}

========================================
Ã°ÂŸÂ“Â„ FICHIER: src/main/java/com.example.productcatalogsystem.controller/CategoryController.java
========================================
package com.example.productcatalogsystem.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import java.util.List;
import com.example.productcatalogsystem.service.CategoryService;
import com.example.productcatalogsystem.dto.CategoryRequestDTO;
import com.example.productcatalogsystem.dto.CategoryResponseDTO;

/**
 * REST Controller for Category
 * Exposes CRUD operations via HTTP endpoints
 */
@RestController
@RequestMapping("/api/categorys")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService service;

    /**
     * getCategoryById
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        log.info("GET /api/categorys/{id}");
        CategoryResponseDTO response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * getAllCategorys
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategorys() {
        log.info("GET /api/categorys");
        List<CategoryResponseDTO> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    /**
     * createCategory
     */
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO request) {
        log.info("POST /api/categorys");
        CategoryResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * updateCategory
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO request) {
        log.info("PUT /api/categorys/{id}");
        CategoryResponseDTO response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * deleteCategory
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.info("DELETE /api/categorys/{id}");
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


