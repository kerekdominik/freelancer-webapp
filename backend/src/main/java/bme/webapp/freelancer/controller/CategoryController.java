package bme.webapp.freelancer.controller;

import bme.webapp.freelancer.dto.CategoryDto;
import bme.webapp.freelancer.entity.Category;
import bme.webapp.freelancer.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        return optCategory
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());

        categoryRepository.save(category);

        return new ResponseEntity<>("Category saved to database!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        Optional<Category> optCategory = categoryRepository.findById(id);

        if (optCategory.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Category category = optCategory.get();
        category.setName(categoryDto.getName());

        categoryRepository.save(category);

        return new ResponseEntity<>("Category updated!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        Optional<Category> optCategory = categoryRepository.findById(id);

        if (optCategory.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        categoryRepository.delete(optCategory.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
