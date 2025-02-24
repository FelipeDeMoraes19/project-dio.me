package dio.me.project_dio.me_2025.service;

import dio.me.project_dio.me_2025.exception.BusinessException;
import dio.me.project_dio.me_2025.model.Product;
import dio.me.project_dio.me_2025.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static dio.me.project_dio.me_2025.util.MessageUtils.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    @Transactional(readOnly = true)
    @Cacheable(value = "products")
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAllActive(pageable);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "product", key = "#id")
    public Product findById(Long id) {
        return repository.findActiveById(id)
                .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND));
    }

    @Transactional
    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Product create(Product product) {
        validateProductName(product.getName());
        return repository.save(product);
    }

    @Transactional
    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Product update(Long id, Product product) {
        Product existingProduct = findById(id);
        if (!existingProduct.getName().equals(product.getName())) {
            validateProductName(product.getName());
        }
        product.setId(id);
        return repository.save(product);
    }

    @Transactional
    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public void delete(Long id) {
        Product product = findById(id);
        product.setActive(false);
        repository.save(product);
    }

    private void validateProductName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException(PRODUCT_ALREADY_EXISTS);
        }
    }
}