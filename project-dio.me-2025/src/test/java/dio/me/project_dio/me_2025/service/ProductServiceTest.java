package dio.me.project_dio.me_2025.service;

import dio.me.project_dio.me_2025.exceptions.BusinessException;
import dio.me.project_dio.me_2025.model.Product;
import dio.me.project_dio.me_2025.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldCreateProduct() {
        Product product = Product.builder()
                .name("Notebook")
                .description("Ultrabook")
                .price(4500.0)
                .build();

        when(repository.save(any(Product.class))).thenReturn(product);

        Product saved = service.create(product);
        assertNotNull(saved);
        assertEquals("Notebook", saved.getName());
    }

    @Test
    void shouldThrowWhenDuplicateName() {
        Product product = Product.builder().name("Duplicated").build();
        when(repository.existsByName("Duplicated")).thenReturn(true);

        assertThrows(BusinessException.class, () -> service.create(product));
    }
}