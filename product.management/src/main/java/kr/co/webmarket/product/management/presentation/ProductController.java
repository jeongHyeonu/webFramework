package kr.co.webmarket.product.management.presentation;

import jakarta.validation.Valid;
import kr.co.webmarket.product.management.application.SimpleProductService;
import kr.co.webmarket.product.management.presentation.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    @Autowired
    ProductController(SimpleProductService simpleProductService){
        this.simpleProductService = simpleProductService;
    }

    @RequestMapping(value="/products", method = RequestMethod.POST)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDao){
        return simpleProductService.add(productDao);
    }

//    @RequestMapping(value="/products", method = RequestMethod.GET)
//    public List<ProductDto> findAllProduct(){
//        return simpleProductService.findAll();
//    }

    @RequestMapping(value="/products", method = RequestMethod.GET)
    public List<ProductDto> findProducts(@RequestParam(required = false) String name){
        if(null == name) return simpleProductService.findAll();
        return simpleProductService.findNameContaining(name);
    }

    @RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public ProductDto findProductById(@PathVariable Long id){
        return simpleProductService.findById(id);
    }

    @RequestMapping(value="/products/{id}", method = RequestMethod.PUT)
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }

    @RequestMapping(value="/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id){
        simpleProductService.delete(id);
    }

}
