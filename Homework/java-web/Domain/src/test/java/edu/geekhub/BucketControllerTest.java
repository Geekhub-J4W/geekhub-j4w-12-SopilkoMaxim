package edu.geekhub;

import edu.geekhub.bucket.Bucket;
import edu.geekhub.bucket.BucketController;
import edu.geekhub.bucket.OrderRepository;
import edu.geekhub.product.Product;
import edu.geekhub.product.ProductService;
import edu.geekhub.user.User;
import edu.geekhub.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BucketControllerTest {

    private BucketController bucketController;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderRepository orderRepository;

    private Bucket bucket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bucketController = new BucketController();
        bucketController.setCustomerService(userService);
        bucketController.setProductService(productService);
        bucketController.setOrderRepository(orderRepository);
        bucket = new Bucket();
        bucketController.setBucket(bucket);
    }

    @Test
    void setCustomer_withValidId_shouldSetCustomer() {
        int customerId = 1;
        User user = new User(customerId, "John Doe", "johndoe@example.com");
        when(userService.getUserById(customerId)).thenReturn(user);

        bucketController.setCustomer(user);

        assertEquals(user, bucket.getCustomer());
    }

    @Test
    void setCustomer_withInvalidId_shouldNotSetCustomer() {
        int customerId = 1;
        when(userService.getUserById(customerId)).thenReturn(null);

        bucketController.setCustomer(customerId);

        assertNull(bucket.getCustomer());
    }

    @Test
    void setCustomer_withValidEmail_shouldSetCustomer() {
        String customerEmail = "johndoe@example.com";
        User user = new User(1, "John Doe", customerEmail);
        when(userService.getUserByEmail(customerEmail)).thenReturn(Optional.of(user));

        bucketController.setCustomer(customerEmail);

        assertEquals(user, bucket.getCustomer());
    }


    @Test
    void addProductWithQuantity_withInsufficientStock_shouldNotAddProduct() {
        int productId = 1;
        int quantity = 5;
        Product product = new Product(productId, "Product",  500, 2);
        when(productService.getProductById(productId)).thenReturn(product);

        assertFalse(bucketController.addProductWithQuanity(productId, quantity));
        assertEquals(2, product.getQuantityOnStock());
        assertEquals(0, product.getQuantityToOrder());
        assertTrue(bucket.getProducts().isEmpty());
    }

    @Test
    void addProductWithQuantity_withSufficientStock_shouldAddProduct() {
        int productId = 1;
        int quantity = 1;
        Product product = new Product(productId, "Product",  500, 2);
        when(productService.getProductById(productId)).thenReturn(product);

        assertTrue(bucketController.addProductWithQuanity(productId, quantity));
        assertEquals(1, product.getQuantityOnStock());
        assertEquals(quantity, product.getQuantityToOrder());
        assertEquals(List.of(product), bucket.getProducts());
    }

    @Test
    void deleteProduct_shouldRemoveProductFromBucket() {
        Product product = new Product(1, "Product 1", 10, 5);
        Bucket bucket = new Bucket();
        bucket.addProduct(product);
        BucketController bucketController = new BucketController();
        bucketController.setBucket(bucket);

        bucketController.deleteProduct(product.getId());

        assertFalse(bucket.getProducts().contains(product));
    }

}
