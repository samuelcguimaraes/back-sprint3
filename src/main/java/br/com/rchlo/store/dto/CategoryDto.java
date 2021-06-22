package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {
	
	private String name;
	private String slug;
	
	public CategoryDto(Category category) {
		this.name = category.getName();
		this.slug = category.getSlug();
	}
	
	public static List<CategoryDto> convert(final List<Category> categories) {
		return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSlug() {
		return this.slug;
	}
}
