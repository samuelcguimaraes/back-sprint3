package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
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
	
	public List<ProductByColorDto> productsByColor() {
		return this.entityManager.createQuery(
				"select new br.com.rchlo.store.dto.ProductByColorDto(p.color, count(p)) from Product p group by p.color",
				ProductByColorDto.class).getResultList();
	}
	
}
