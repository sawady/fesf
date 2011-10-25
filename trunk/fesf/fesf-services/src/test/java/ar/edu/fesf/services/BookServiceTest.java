package ar.edu.fesf.services;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring-services-context.xml",
        "classpath*:META-INF/spring-persistence-context.xml" })
@Transactional
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void bookSearch() {
        assertTrue("Must be empty for sarasa", this.getBookService().bookSearch("sarasa").isEmpty());
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

}
