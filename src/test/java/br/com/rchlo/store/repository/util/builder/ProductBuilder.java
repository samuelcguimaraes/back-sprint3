package br.com.rchlo.store.repository.util.builder;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;

import java.math.BigDecimal;

public class ProductBuilder {
	
	private Long code;
	private String name;
	private String description;
	private String slug;
	private String brand;
	private BigDecimal price;
	private BigDecimal discount;
	private Color color;
	private Integer weightInGrams;
	
	public ProductBuilder setCode(final Long code) {
		this.code = code;
		return this;
	}
	
	public ProductBuilder setName(final String name) {
		this.name = name;
		return this;
	}
	
	public ProductBuilder setDescription(final String description) {
		this.description = description;
		return this;
	}
	
	public ProductBuilder setSlug(final String slug) {
		this.slug = slug;
		return this;
	}
	
	public ProductBuilder setBrand(final String brand) {
		this.brand = brand;
		return this;
	}
	
	public ProductBuilder setPrice(final BigDecimal price) {
		this.price = price;
		return this;
	}
	
	public ProductBuilder setDiscount(final BigDecimal discount) {
		this.discount = discount;
		return this;
	}
	
	public ProductBuilder setColor(final Color color) {
		this.color = color;
		return this;
	}
	
	public ProductBuilder setWeightInGrams(final Integer weightInGrams) {
		this.weightInGrams = weightInGrams;
		return this;
	}
	
	public Product build() {
		return new Product(this.code, this.name, this.description, this.slug, this.brand, this.price, this.discount,
		                   this.color, this.weightInGrams);
	}
}
