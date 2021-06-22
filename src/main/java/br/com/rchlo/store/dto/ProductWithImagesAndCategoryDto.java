package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.domain.ProductImage;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductWithImagesAndCategoryDto {
	
	private final Long code;
	
	private final String name;
	
	private final String description;
	
	private final String slug;
	
	private final String brand;
	
	private final BigDecimal originalPrice;
	
	private final boolean hasDiscount;
	
	private final BigDecimal effectivePrice;
	
	private final String color;
	
	private final Integer weightInGrams;
	
	private final List<String> images;
	
	private final String categoryName;
	
	private final String categorySlug;
	
	public ProductWithImagesAndCategoryDto(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.description = product.getDescription();
		this.slug = product.getSlug();
		this.brand = product.getBrand();
		this.originalPrice = product.getPrice();
		this.hasDiscount = product.getDiscount() != null;
		this.effectivePrice = this.hasDiscount ? this.originalPrice.subtract(product.getDiscount())
		                                       : this.originalPrice;
		this.color = product.getColor().getDescription();
		this.weightInGrams = product.getWeightInGrams();
		this.images = product.getProductImages().stream().map(ProductImage::getImageUrl).collect(Collectors.toList());
		this.categoryName = product.getCategory().getName();
		this.categorySlug = product.getCategory().getSlug();
	}
	
	public Long getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getSlug() {
		return this.slug;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public BigDecimal getOriginalPrice() {
		return this.originalPrice;
	}
	
	public boolean isHasDiscount() {
		return this.hasDiscount;
	}
	
	public BigDecimal getEffectivePrice() {
		return this.effectivePrice;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public Integer getWeightInGrams() {
		return this.weightInGrams;
	}
	
	public List<String> getImages() {
		return this.images;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
	
	public String getCategorySlug() {
		return this.categorySlug;
	}
}
