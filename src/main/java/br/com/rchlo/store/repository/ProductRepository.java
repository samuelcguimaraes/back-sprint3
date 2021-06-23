package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductRepository {
	
	private final EntityManager entityManager;
	
	public ProductRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Product> findAllByOrderByName() {
		return this.entityManager.createQuery("select p from Product p ORDER BY p.name", Product.class).getResultList();
	}
	
	public List<Product> findAllByOrderByNameWithProductImageAndCategory() {
		return this.entityManager.createQuery("SELECT p " +
		                                      "FROM Product p " +
		                                      "JOIN FETCH p.productImages " +
		                                      "JOIN FETCH p.category " +
		                                      "ORDER BY p.name", Product.class).getResultList();
	}
	
	public List<Product> findAllByOrderByNameWithProductImageCategoryAndSize() {
		
		List<Product> products = this.entityManager.createQuery("SELECT DISTINCT p " +
		                                                        "FROM Product p " +
		                                                        "JOIN FETCH p.productImages " +
		                                                        "JOIN FETCH p.category ", Product.class)
		                                           .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
		                                           .getResultList();
		
		products = this.entityManager.createQuery("SELECT DISTINCT p " +
		                                          "FROM Product p " +
		                                          "JOIN FETCH p.availableSizes " +
		                                          "WHERE p IN :products " +
		                                          "ORDER BY p.name", Product.class)
		                             .setParameter("products", products)
		                             .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
		                             .getResultList();
		
		return products;
	}
	
	public List<ProductByColorDto> productsByColor() {
		return this.entityManager.createQuery(
				"select new br.com.rchlo.store.dto.ProductByColorDto(p.color, count(p)) from Product p group by p.color ORDER BY p.color",
				ProductByColorDto.class).getResultList();
	}
	
}
