package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.dto.ProductDto;
import br.com.rchlo.store.dto.ProductWithImagesAndCategoryDto;
import br.com.rchlo.store.dto.ProductWithImagesCategoryAndSizesDto;
import br.com.rchlo.store.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
	
	private final ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping("/products_first")
	public List<ProductDto> productsFirst() {
		return this.productRepository.findAllByOrderByName().stream().map(ProductDto::new).collect(Collectors.toList());
	}
	
	@GetMapping("/products_second")
	public List<ProductWithImagesAndCategoryDto> productsSecond() {
		return this.productRepository.findAllByOrderByNameWithProductImageAndCategory()
		                             .stream()
		                             .map(ProductWithImagesAndCategoryDto::new)
		                             .collect(Collectors.toList());
	}
	
	@GetMapping("/products_third") //availableSizes está Lazy e não está sendo chamado com Join Fetch
	public List<ProductWithImagesCategoryAndSizesDto> productsThird() {
		return this.productRepository.findAllByOrderByNameWithProductImageCategoryAndSize()
		                             .stream()
		                             .map(ProductWithImagesCategoryAndSizesDto::new)
		                             .collect(Collectors.toList());
	}
	
	@GetMapping("/reports/products/by-color")
	public List<ProductByColorDto> productByColorReport() {
		return this.productRepository.productsByColor();
	}
	
}
