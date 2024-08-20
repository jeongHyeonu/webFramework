package kr.co.webmarket.product.management.application;

import kr.co.webmarket.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SimpleProductServiceTest {
    @Autowired
    SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.")
    void productAddAndFindByIdTest(){
        ProductDto productDto = new ProductDto("연필", 300, 20);
        ProductDto savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();
        ProductDto foundProductDto = simpleProductService.findById(savedProductId);

        System.out.println(savedProductDto.getId() == foundProductDto.getId());
        System.out.println(savedProductDto.getName() == foundProductDto.getName());
        System.out.println(savedProductDto.getPrice().compareTo(foundProductDto.getPrice()));
        System.out.println(savedProductDto.getAmount() == foundProductDto.getAmount());

    }
}