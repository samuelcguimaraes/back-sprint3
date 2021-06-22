package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.repository.util.JPAUtil;
import br.com.rchlo.store.repository.util.builder.ProductBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest {
	
	private EntityManager entityManager;
	private ProductRepository productRepository;
	
	@BeforeEach
	public void beforeEach() {
		this.entityManager = JPAUtil.getEntityManager();
		this.productRepository = new ProductRepository(this.entityManager);
		this.entityManager.getTransaction().begin();
	}
	
	@AfterEach
	public void afterEach() {
		this.entityManager.getTransaction().rollback();
	}
	
	@Test
	void shouldListAllProductsOrderedByName() {
		persistProducts();
		
		List<Product> products = this.productRepository.findAllByOrderByName();
		
		assertEquals(3, products.size());
		
		final Product firstProduct = products.get(0);
		assertEquals(3L, firstProduct.getCode());
		assertEquals("Camiseta Infantil Mario Bros Azul", firstProduct.getName());
		
		final Product secondProduct = products.get(1);
		assertEquals(7L, secondProduct.getCode());
		assertEquals("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco", secondProduct.getName());
		
		final Product thirdProduct = products.get(2);
		assertEquals(1L, thirdProduct.getCode());
		assertEquals("Regata Infantil Mario Bros Branco", thirdProduct.getName());
	}
	
	@Test
	void shouldListProductsByColor() {
		persistProducts();
		
		final List<ProductByColorDto> productsByColor = this.productRepository.productsByColor();
		
		assertEquals(2, productsByColor.size());
		
		final ProductByColorDto firstColor = productsByColor.get(0);
		assertEquals(1L, firstColor.getAmount());
		assertEquals("Azul", firstColor.getColor());
		
		final ProductByColorDto secondColor = productsByColor.get(1);
		assertEquals(2L, secondColor.getAmount());
		assertEquals("Branca", secondColor.getColor());
	}
	
	private void persistProducts() {
		this.entityManager.persist(new ProductBuilder().setCode(7L)
		                                               .setName("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco")
		                                               .setDescription(
				                                               "A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.")
		                                               .setSlug(
				                                               "jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku")
		                                               .setBrand("Nintendo")
		                                               .setPrice(new BigDecimal("199.90"))
		                                               .setDiscount(null)
		                                               .setColor(Color.WHITE)
		                                               .setWeightInGrams(147)
		                                               .build());
		this.entityManager.persist(new ProductBuilder().setCode(1L)
		                                               .setName("Regata Infantil Mario Bros Branco")
		                                               .setDescription(
				                                               "A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!")
		                                               .setSlug("regata-infantil-mario-bros-branco-14040174_sku")
		                                               .setBrand("Nintendo")
		                                               .setPrice(new BigDecimal("29.90"))
		                                               .setDiscount(null)
		                                               .setColor(Color.WHITE)
		                                               .setWeightInGrams(98)
		                                               .build());
		this.entityManager.persist(new ProductBuilder().setCode(3L)
		                                               .setName("Camiseta Infantil Mario Bros Azul")
		                                               .setDescription(
				                                               "A Camiseta Infantil Mario Bros Azul é confeccionada em fibra natural. Aposte!")
		                                               .setSlug("camiseta-infantil-mario-bros-azul-14040175_sku")
		                                               .setBrand("Nintendo")
		                                               .setPrice(new BigDecimal("35.90"))
		                                               .setDiscount(null)
		                                               .setColor(Color.BLUE)
		                                               .setWeightInGrams(105)
		                                               .build());
	}
}