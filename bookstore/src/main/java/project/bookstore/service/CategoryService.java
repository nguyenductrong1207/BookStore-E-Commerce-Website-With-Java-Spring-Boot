package project.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.bookstore.entity.Category;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> listAll() {
        return categoryRepo.findAll();
    }

    public Category findByCategoryName(String categoryName){
        return categoryRepo.findByName(categoryName);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void save(Category category) {
        categoryRepo.save(category);
    }

    public Category get(Integer id) throws CategoryNotFoundException {
        Optional<Category> result = categoryRepo.findById(id);

        if (result.isPresent()) {
            return result.get();
        }
        throw new CategoryNotFoundException("Could not find any categories with ID " + id);
    }

    public void delete(Integer id) throws CategoryNotFoundException {
        Long count = categoryRepo.countById(id);
        if (count == null || count == 0) {
            throw new CategoryNotFoundException("Could not find any category with ID " + id);
        }
        categoryRepo.deleteById(id);
    }
}
