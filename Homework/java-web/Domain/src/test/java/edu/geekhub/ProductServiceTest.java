package edu.geekhub;

import edu.geekhub.product.Product;
import edu.geekhub.product.ProductRepository;
import edu.geekhub.product.ProductService;
import edu.geekhub.product.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository repository;
    
    @Mock
    private ProductValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(repository);
    }

    @Test
    void addImage_whenFileIsNotEmpty_setsImageBytes() {
        Product product = new Product("Test Product", 10);
        byte[] imageBytes = {1, 2, 3};
        MultipartFile file = new MockMultipartFile("test.jpg", imageBytes);

        Product result = productService.addImage(product, file);

        assertArrayEquals(imageBytes, result.getImgBytes());
    }

    @Test
    void addImage_whenFileIsEmpty_doesNotSetImageBytes() {
        Product product = new Product("Test Product", 10);
        MultipartFile file = new MockMultipartFile("test.jpg", new byte[]{});

        Product result = productService.addImage(product, file);

        assertNull(result.getImgBytes());
    }

    @Test
    void addProduct_whenProductIsValid_addsProductToRepository() {
        Product product = new Product("Test Product", 10);

        productService.addProduct(product);

        verify(repository, times(1)).addProduct(product);
    }

    @Test
    void addProduct_whenProductIsNotValid_doesNotAddProductToRepository() throws Exception {
        Product product = new Product("Test Product", -10);

        productService.addProduct(product);

        verify(repository, never()).addProduct(product);
    }


    @Test
    void deleteProduct_whenProductExists_deletesProductFromRepository() {

        Product product = new Product("Test Product", 10);
        int productId = product.getId();
        when(repository.getProductById(productId)).thenReturn(product);


        when(validator.validateIdExist(productId, repository, Logger.getLogger(Product.class.getName()))).thenReturn(true);
        productService.setValidator(validator);

        productService.deleteProduct(productId);
        verify(repository, times(1)).deleteProduct(productId);
    }

    @Test
    void deleteProduct_whenProductDoesNotExist_doesNotDeleteProductFromRepository() {
        int productId = 1;
        when(repository.getProductById(productId)).thenReturn(null);

        productService.deleteProduct(productId);

        verify(repository, never()).deleteProduct(productId);
    }

    @Test
    void getProductById_whenProductExists_returnsProduct() {
        int productId = 1;
        Product product = new Product("Test Product", 10);
        when(repository.getProductById(productId)).thenReturn(product);
        when(validator.validateIdExist(productId, repository, Logger.getLogger(Product.class.getName()))).thenReturn(true);
        productService.setValidator(validator);

        Product result = productService.getProductById(productId);

        assertEquals(product, result);
    }

}