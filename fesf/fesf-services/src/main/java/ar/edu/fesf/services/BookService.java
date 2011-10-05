package ar.edu.fesf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Category;
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

    public void initialize() {

        Book mago = new Book("Un Mago de Terramar");
        Category drama = new Category("Drama");
        drama.addBook(mago);

        this.save(mago);
        this.save(new Book("Los Crimenes de la Calle Morgue"));
        this.save(new Book("Maleficio"));
        this.save(new Book("Cementerio de Animales"));
        this.save(new Book("El camino hacia el dodario"));
        this.save(new Book("Las Aventuras de Crista y Zack"));
        this.save(new Book("Mi Planta de Naranja Lima"));
        this.save(new Book("Locademia de Policias"));
        this.save(new Book("La pistola"));
        this.save(new Book("Caroso y narizota"));

        this.getCategoryRepository().save(drama);
        this.getCategoryRepository().save(new Category("Accion"));
        this.getCategoryRepository().save(new Category("Policial"));
        this.getCategoryRepository().save(new Category("Aventura"));
        this.getCategoryRepository().save(new Category("Comedia"));
    }

    public String getFieldForSort() {
        return "title";
    }

    public List<String> getFieldNames() {
        List<String> names = new ArrayList<String>();
        names.add("title");
        return names;
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return this.getCategoryRepository().findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findByCategory(final Category category) {
        List<Book> books = new ArrayList<Book>();
        for (Category booksEx : this.getCategoryRepository().findByExample(category)) {
            books.addAll(booksEx.getBooks());
        }
        return books;
    }

}
