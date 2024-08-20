package kr.co.webmarket.product.management.infrastructure;

import kr.co.webmarket.product.management.domain.Product;
import kr.co.webmarket.product.management.domain.EntityNotFoundException;
import kr.co.webmarket.product.management.domain.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class ListProductRepository implements ProductRepository {
    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong seq = new AtomicLong(1L);

    public List<Product> findAll(){
        return products;
    }

    public Product findById(Long id){
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(()->new EntityNotFoundException("Product 를 찾지 못하였습니다."));
    }

    public Product add(Product p){
        p.setId(seq.getAndAdd(1L));
        products.add(p);
        return p;
    }

    public List<Product> findByNameContaining(String name){
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product p){
        Integer index = products.indexOf(p);
        products.set(index, p);
        return p;
    }

    public void delete(Long id){
        Product p = this.findById(id);
        products.remove(p);
    }
}
