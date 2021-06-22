package br.com.rchlo.store.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product {
	
	@Id
	private Long code;
	
	private String name;
	
	private String description;
	
	private String slug;
	
	private String brand;
	
	private BigDecimal price;
	
	private BigDecimal discount;
	
	@Enumerated(EnumType.STRING)
	private Color color;
	
	private Integer weightInGrams;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "product_available_sizes", joinColumns = @JoinColumn(name = "product_code"))
	@Column(name = "available_sizes")
	@Enumerated(EnumType.STRING)
	private List<Size> availableSizes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<ProductImage> productImages;
	
	/**
	 * @deprecated
	 */
	protected Product() {
	}
	
	public Product(Long code, String name, String description, String slug, String brand, BigDecimal price,
	               BigDecimal discount, Color color, Integer weightInGrams) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.slug = slug;
		this.brand = brand;
		this.price = price;
		this.discount = discount;
		this.color = color;
		this.weightInGrams = weightInGrams;
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
	
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public BigDecimal getDiscount() {
		return this.discount;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Integer getWeightInGrams() {
		return this.weightInGrams;
	}
	
	public List<ProductImage> getProductImages() {
		return this.productImages;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public List<Size> getAvailableSizes() {
		return this.availableSizes;
	}
}
