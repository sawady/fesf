package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.fesf.application.Entity;

/**
 * 
 * TODO El review tambien tiene un comentario, por ende la calificacion deberia
 * tambien acompaniar el comentario
 */
public class Review extends Entity {
    private int calification = 0;

    private int sumOfCalifications = 0;

    private int countOfCalifications = 0;

    private List<Comment> comments = new ArrayList<Comment>();

    /* Methods */
    public void addCalification(final int calif) {

        this.setSumOfCalifications(this.getSumOfCalifications() + calif);
        this.setCountOfCalifications(this.getCountOfCalifications() + 1);

        this.setCalification(this.getSumOfCalifications() / this.getCountOfCalifications());

    }

    /* Accessors */
    public int getCalification() {
        return calification;
    }

    public void setCalification(final int calification) {
        this.calification = calification;
    }

    public int getSumOfCalifications() {
        return sumOfCalifications;
    }

    public void setSumOfCalifications(final int sumOfCalifications) {
        this.sumOfCalifications = sumOfCalifications;
    }

    public int getCountOfCalifications() {
        return countOfCalifications;
    }

    public void setCountOfCalifications(final int countOfCalifications) {
        this.countOfCalifications = countOfCalifications;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

}
