package br.com.rchlo.store.domain;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String slug;
	
	private Integer position;
	
	public Category() {
	}
	
	public Category(final String name, final String slug, final Integer position) {
		this.name = name;
		this.slug = slug;
		this.position = position;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSlug() {
		return this.slug;
	}
	
	public Integer getPosition() {
		return this.position;
	}
}
