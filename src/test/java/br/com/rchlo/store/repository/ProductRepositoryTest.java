package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.repository.util.JPAUtil;
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
		this.entityManager.persist(new Product(7L,
		                                       "Jaqueta Puffer Juvenil Com Capuz Super Mario Branco",
		                                       "A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.",
		                                       "jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku",
		                                       "Nintendo",
		                                       new BigDecimal("199.90"),
		                                       null,
		                                       Color.WHITE,
		                                       147));
		this.entityManager.persist(new Product(1L,
		                                       "Regata Infantil Mario Bros Branco",
		                                       "A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!",
		                                       "regata-infantil-mario-bros-branco-14040174_sku",
		                                       "Nintendo",
		                                       new BigDecimal("29.90"),
		                                       null,
		                                       Color.WHITE,
		                                       98));
		this.entityManager.persist(new Product(3L,
		                                       "Camiseta Infantil Mario Bros Azul",
		                                       "A Camiseta Infantil Mario Bros Azul é confeccionada em fibra natural. Aposte!",
		                                       "camiseta-infantil-mario-bros-azul-14040175_sku",
		                                       "Nintendo",
		                                       new BigDecimal("35.90"),
		                                       null,
		                                       Color.BLUE,
		                                       105));
	}
}