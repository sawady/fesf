package ar.edu.fesf.services;

import java.io.InputStream;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.dtos.NewBookDTO;
import ar.edu.fesf.exceptions.TransactionException;
import ar.edu.fesf.others.GBSearchResults;
import ar.edu.fesf.others.Json2BookConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class GoogleBookService implements Serializable {

    private static final long serialVersionUID = -9077418873030417905L;

    private transient Gson gson;

    @Autowired
    private BookService bookService;

    public GoogleBookService() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(NewBookDTO.class, new Json2BookConverter());
        this.gson = gsonBuilder.create();
    }

    @Transactional(readOnly = true)
    @Secured("ROLE_LIBRARIAN")
    public GBSearchResults search(final String searchValue) {
        return this.getGson().fromJson(this.stringSearchResult(searchValue), GBSearchResults.class);
    }

    @Transactional(readOnly = true)
    private String stringSearchResult(final String searchValue) {
        final DefaultHttpClient httpclient = new DefaultHttpClient();
        final HttpContext localContext = new BasicHttpContext();
        final HttpGet httpget = new HttpGet(
                "https://www.googleapis.com/books/v1/volumes?q=isbn:"
                        + searchValue
                        + "&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/publisher,volumeInfo/categories,volumeInfo/industryIdentifiers,volumeInfo/description,volumeInfo/imageLinks/thumbnail)");
        try {
            httpclient.execute(httpget, localContext);
            localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            final HttpResponse response = (HttpResponse) localContext.getAttribute(ExecutionContext.HTTP_RESPONSE);
            final HttpEntity entity = response.getEntity();
            final InputStream content = entity.getContent();
            final StringBuilder builder = new StringBuilder();
            int code = content.read();
            while (code > -1) {
                builder.append(Character.valueOf((char) code));
                code = content.read();
            }
            return builder.toString();

        } catch (final Exception e) {
            throw new TransactionException(e.getMessage());// TODO: Check if this is the appropriate exception
        }
    }

    public Gson getGson() {
        return this.gson;
    }

    public void setGson(final Gson gson) {
        this.gson = gson;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

}
