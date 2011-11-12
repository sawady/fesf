package ar.edu.fesf.services.dtos;

import ar.edu.fesf.model.Comment;

public class CommentDTO {

    private String body;

    private int calification;

    private String person;

    public CommentDTO() {
        super();
    }

    public CommentDTO(final Comment comment) {
        super();
        this.body = comment.getBody();
        this.calification = comment.getCalification().getValue();
        this.person = comment.getPerson().getFullName();
    }

    public CommentDTO(final String body, final int calification, final String person) {
        super();
        this.body = body;
        this.calification = calification;
        this.person = person;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public int getCalification() {
        return this.calification;
    }

    public void setCalification(final int calification) {
        this.calification = calification;
    }

    public String getPerson() {
        return this.person;
    }

    public void setPerson(final String person) {
        this.person = person;
    }

}
