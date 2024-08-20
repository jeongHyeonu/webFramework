package kr.co.webmarket.product.management.infrastructure;

import kr.co.webmarket.product.management.domain.Product;
import kr.co.webmarket.product.management.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
public class DatabaseProductRepository implements ProductRepository {

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public DatabaseProductRepository(NamedParameterJdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public Product add(Product p){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParam = new BeanPropertySqlParameterSource(p);
        jdbc.update("INSERT INTO products (name,price,amount) VALUES (:name, :price, :amount)", namedParam, keyHolder);
        Long generatedId = keyHolder.getKey().longValue();
        p.setId(generatedId);

        return p;
    }

    public Product findById(Long id){
        SqlParameterSource namedParam = new MapSqlParameterSource("id",id);

        Product p = jdbc.queryForObject(
                "SELECT id, name, price, amount FROM products WHERE id=:id",
                namedParam, new BeanPropertyRowMapper<>(Product.class)
        );

        return p;
    }

    public List<Product> findAll(){
        List<Product> p = jdbc.query(
                "SELECT * FROM products",
                new BeanPropertyRowMapper<>(Product.class)
        );
        return p;
    }

    public List<Product> findByNameContaining(String name){
        SqlParameterSource namedParam = new MapSqlParameterSource("name", "%"+name+"%");
        List<Product> p = jdbc.query(
                "SELECT * FROM products WHERE name LIKE :name", namedParam, new BeanPropertyRowMapper<>(Product.class)
        );
        return p;
    }

    public Product update(Product p){
        return null;
    }

    public void delete(Long id){
        SqlParameterSource namedParam = new MapSqlParameterSource("id",id);

        jdbc.update(
                "DELETE FROM products WHERE id=:id", namedParam
        );
    }
}
