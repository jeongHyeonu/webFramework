package kr.co.webmarket.product.management.presentation;

import jakarta.validation.constraints.NotNull;
import kr.co.webmarket.product.management.domain.Product;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private Integer amount;

    public ProductDto(String name, int price, int amount) {
        this.name=name; this.price=price; this.amount=amount;
    }

    public static Product toEntity(ProductDto productDto){
        Product product = new Product();

        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setAmount(productDto.getAmount());
        return product;
    }

    public static ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto(
                product.getName(), product.getPrice(), product.getAmount()
        );
        productDto.setId(product.getId());
        return productDto;
    }
}
