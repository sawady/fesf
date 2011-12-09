package ar.edu.fesf.others;

import java.util.List;

import ar.edu.fesf.dtos.NewBookDTO;

public class GBSearchResults {
    private List<NewBookDTO> items;

    public List<NewBookDTO> getItems() {
        return this.items;
    }

    public void setItems(final List<NewBookDTO> bookDTOs) {
        this.items = bookDTOs;
    }

}
