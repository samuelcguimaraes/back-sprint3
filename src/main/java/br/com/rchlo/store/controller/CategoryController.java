package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.CategoryDto;
import br.com.rchlo.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/*@GetMapping
	public List<CategoryDto> categories() {
		return CategoryDto.convert(this.categoryRepository.findAllByOrderByPosition());
	}*/
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> categories() {
		return ResponseEntity.ok(CategoryDto.convert(this.categoryRepository.findAllByOrderByPosition()));
	}
	
}
