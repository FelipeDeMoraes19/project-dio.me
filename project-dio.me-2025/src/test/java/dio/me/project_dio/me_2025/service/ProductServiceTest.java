package dio.me.project_dio.me_2025.service;

import dio.me.project_dio.me_2025.exceptions.BusinessException;
import dio.me.project_dio.me_2025.model.Product;
import dio.me.project_dio.me_2025.repository.ProductRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    
    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setUp() {
        when(messageSource.getMessage(anyString(), any(), any()))
            .thenAnswer(invocation -> {
                String key = invocation.getArgument(0);
                Object[] args = invocation.getArgument(1);
                if (key.equals("product.exists")) {
                    return String.format("Product with name '%s' already exists", args);
                }
                return key;
            });
    }

    @Test
    void shouldCreateProduct() {
        Product product = Product.builder()
                .name("Notebook")
                .description("Ultrabook")
                .price(new BigDecimal("4500.00"))
                .build();

        when(repository.save(any(Product.class))).thenReturn(product);
        when(repository.existsByName(any())).thenReturn(false);

        Product saved = service.create(product);
        assertNotNull(saved);
        assertEquals("Notebook", saved.getName());
    }

    @Test
    void shouldThrowWhenDuplicateName() {
        String productName = "Duplicated";
        
        when(repository.existsByName(productName)).thenReturn(true);

        BusinessException exception = assertThrows(
            BusinessException.class, 
            () -> service.create(Product.builder()
                .name(productName)
                .description("Produto duplicado")
                .price(BigDecimal.TEN)
                .build())
        );
        
        assertEquals(
            "Product with name 'Duplicated' already exists", 
            exception.getMessage()
        );
    }

    @Test
    void shouldThrowWhenInvalidPrice() {
        Product product = Product.builder()
                .name("Produto Inválido")
                .description("Descrição inválida")
                .price(new BigDecimal("-50.00")) 
                .build();

        assertThrows(
            ConstraintViolationException.class, 
            () -> service.create(product)
        );
    }

    @Test
    void shouldFindActiveProducts() {
        Product product = Product.builder()
                .name("Monitor")
                .description("Monitor LED 24''")
                .price(new BigDecimal("799.99"))
                .build();
        
        Page<Product> page = new PageImpl<>(Collections.singletonList(product));
        when(repository.findAllActive(any(Pageable.class))).thenReturn(page);

        Page<Product> result = service.findAll(Pageable.unpaged());
        assertEquals(1, result.getTotalElements());
        assertEquals("Monitor", result.getContent().get(0).getName());
    }
}