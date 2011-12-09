package ar.edu.fesf.others;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.fesf.dtos.NewBookDTO;
import ar.edu.fesf.services.BookService;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Json2BookConverter implements JsonDeserializer<NewBookDTO>, Serializable {
    private static final long serialVersionUID = 1969297385628960747L;

    @Autowired
    private BookService bookService;

    @Override
    public NewBookDTO deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final NewBookDTO bookDTO = new NewBookDTO();
        // final Book book;

        final JsonObject volumeInfo = json.getAsJsonObject().get("volumeInfo").getAsJsonObject();
        bookDTO.setTitle(this.getPropertyAsString("title", volumeInfo));
        bookDTO.setIsbn(this.getISBN13(volumeInfo));
        bookDTO.setPublisher(this.getPropertyAsString("publisher", volumeInfo));
        bookDTO.setDescription(this.getDescription(volumeInfo));
        bookDTO.setCategories(this.getCategories(volumeInfo));
        bookDTO.setAuthors(this.getAuthors(volumeInfo));
        JsonObject jsonObject = volumeInfo.get("imageLinks").getAsJsonObject();
        bookDTO.setImageLink(this.getPropertyAsString("thumbnail", jsonObject));
        return bookDTO;
    }

    private String getDescription(final JsonObject volumeInfo) {
        return volumeInfo.get("description").getAsString();
    }

    private String getISBN13(final JsonObject jsonObject) {
        final JsonArray isbns = jsonObject.get("industryIdentifiers").getAsJsonArray();
        final JsonObject isbn13 = isbns.get(1).getAsJsonObject(); // TODO: 1 o 0?
        return isbn13.get("identifier").getAsString();
    }

    private Set<String> getAuthors(final JsonObject volumeInfo) {
        return this.getPropAsSetOfStrings("authors", volumeInfo);
    }

    private Set<String> getCategories(final JsonObject volumeInfo) {
        return this.getPropAsSetOfStrings("categories", volumeInfo);
    }

    private Set<String> getPropAsSetOfStrings(final String property, final JsonObject volumeInfo) {
        Set<String> set = new HashSet<String>();
        final JsonElement jsonIterableElement = volumeInfo.get(property);
        if (jsonIterableElement != null) { // if property present in JSON Object

            JsonArray jsonArray = jsonIterableElement.getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                set.add(jsonElement.getAsString());
            }

        }
        return set;
    }

    private String getPropertyAsString(final String property, final JsonObject jsonObject) {
        final JsonElement jsonElement = jsonObject.get(property);
        return jsonElement == null ? "" : jsonElement.getAsString();
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

}
