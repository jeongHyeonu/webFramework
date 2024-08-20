package kr.co.webmarket.product.management.application;

import kr.co.webmarket.product.management.domain.Product;
import kr.co.webmarket.product.management.presentation.ProductDto;
import kr.co.webmarket.product.management.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService{

    private ProductRepository productRepository;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(ProductRepository repository,  ValidationService validationService){
        this.productRepository = repository;
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto){
        Product p = ProductDto.toEntity(productDto);
        validationService.checkValid(p);
        Product savedProduct = productRepository.add(p);
        ProductDto saveProductDto = ProductDto.toDto(savedProduct);
        return saveProductDto;
    }

    public ProductDto findById(Long id){
        Product p = productRepository.findById(id);
        ProductDto productDto = ProductDto.toDto(p);
        return productDto;
    }

    public List<ProductDto> findAll(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findNameContaining(String name){
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

    public ProductDto update(ProductDto productDto){
        Product p = ProductDto.toEntity(productDto);
        Product updatedProduct = productRepository.update(p);
        ProductDto updatedProductDto = ProductDto.toDto(updatedProduct);
        return updatedProductDto;
    }

    public void delete(Long id){
        productRepository.delete(id);
    }
}
