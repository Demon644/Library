package ua.logos.service;

import ua.logos.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void saveCategory (CategoryDTO dto);

    CategoryDTO changeName(Long id);

    CategoryDTO changeShortInfo(Long id);

    CategoryDTO findCategoryById(Long id);

    List<CategoryDTO> findAllCategories();

    void deleteCategoryById(Long id);
}
