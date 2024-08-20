package kr.co.webmarket.product.management.application;

import kr.co.webmarket.product.management.domain.Product;
import kr.co.webmarket.product.management.domain.ProductRepository;
import kr.co.webmarket.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleProductServiceUnitTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후 추가된 상품이 반환")
    void productAddTest(){
        ProductDto productDto = new ProductDto("연필",300,20);
        Long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);
        when(productRepository.add(any())).thenReturn(product);

        ProductDto savedDto = simpleProductService.add(productDto);

        assertTrue(savedDto.getId().equals(PRODUCT_ID));
        assertTrue(savedDto.getName().equals(productDto.getName()));
        assertTrue(savedDto.getPrice().equals(productDto.getPrice()));
        assertTrue(savedDto.getAmount().equals(productDto.getAmount()));
    }
}
