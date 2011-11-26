package ar.edu.fesf.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.lang.StringUtils;

import ar.edu.fesf.dtos.EditBookDTO;
import ar.edu.fesf.dtos.NewBookDTO;
import ar.edu.fesf.services.BookService;

@Path("/books")
public class BookServiceRest {

    private BookService bookService;

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<EditBookDTO> findAllBooks() {
        return this.getBookService().findAllBooks();
    }

    @POST
    @Path("/newBook")
    @Produces("application/json")
    public void createNewBook(@DefaultValue(StringUtils.EMPTY) @FormParam(value = "title") final String title,
            @DefaultValue(StringUtils.EMPTY) @FormParam(value = "isbn") final String isbn,
            @DefaultValue(StringUtils.EMPTY) @FormParam(value = "publisher") final String publisher,
            @DefaultValue(StringUtils.EMPTY) @FormParam(value = "description") final String description,
            @DefaultValue("true") @FormParam(value = "available") final boolean available,
            @DefaultValue("1") @FormParam(value = "countOfCopies") final int countOfCopies) {
        this.getBookService().registerNewBookDTO(
                new NewBookDTO(title, isbn, publisher, description, available, countOfCopies));
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

}
