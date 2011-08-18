package ar.edu.fesf.model;

import java.util.List;

/**
 * TODO: description
 */
public class Review {
    private Integer calification;

    private List<Integer> califications;

    private List<Comment> comments;

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
