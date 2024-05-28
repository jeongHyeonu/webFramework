package kr.ac.hansung.cse.hellospringdatajpa.repository;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository : CRUD 자동으로 만들어줌, DAO 클래스 대신해줌
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name); // 검색 시 완전히 일치해야함
    List<Product> findByNameContaining(String searchKeyword, Pageable paging); // 검색 시 일부 일치 (substring)

    @Query("select p from Product p where p.name like %?1%")
    List<Product> searchByName(String name);
}
