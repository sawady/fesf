package ar.edu.fesf.model;

import java.util.List;

import ar.edu.fesf.application.Entity;

/**
 * TODO: description
 */
public class Review extends Entity {
    private Integer calification;

    private List<Integer> califications;

    private List<Comment> comments;

    /* Accessors */
    public Integer getCalification() {
        return this.calification;
    }

    public void setCalification(final Integer calification) {
        this.calification = calification;
    }

    public List<Integer> getCalifications() {
        return this.califications;
    }

    public void setCalifications(final List<Integer> califications) {
        this.califications = califications;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

}
