package ar.edu.fesf.services;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.repositories.CategoryRepository;

public class BookService extends GenericTransactionalRepositoryService<Book> {

    private static final long serialVersionUID = 7521127091837519541L;

    private CategoryRepository categoryRepository;

    public void setCategoryRepository(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return this.categoryRepository;
    }

}
