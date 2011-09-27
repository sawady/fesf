package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of califications and a list of simple comments without califications.
 */
public class UserFeedbackManager extends Entity {

    private List<Calification> califications = new ArrayList<Calification>();

    private int avgCalification = 0;

    private int sumOfCalifications = 0;

    private int countOfCalifications = 0;

    private List<Comment> comments = new ArrayList<Comment>();

    /* Methods */
    public void addCalification(final Calification calif) {
        this.getCalifications().add(calif);
        this.setSumOfCalifications(this.getSumOfCalifications() + calif.getUserCalification());
        this.setCountOfCalifications(this.getCountOfCalifications() + 1);

        this.setAvgCalification(this.getSumOfCalifications() / this.getCountOfCalifications());

    }

    public void removeCalification(final Calification calif) {
        this.getCalifications().remove(calif);
        this.setSumOfCalifications(this.getSumOfCalifications() - calif.getUserCalification());
        this.setCountOfCalifications(this.getCountOfCalifications() - 1);

        this.setAvgCalification(this.getSumOfCalifications() / this.getCountOfCalifications());

    }

    public void addComment(final Comment comment) {
        this.addCalification(comment.getCalification());
        this.comments.add(comment);
    }

    public void removeComment(final Comment comment) {
        this.removeCalification(comment.getCalification());
        this.comments.remove(comment);
    }

    /* Accessors */
    public int getAvgCalification() {
        return this.avgCalification;
    }

    public void setAvgCalification(final int calification) {
        this.avgCalification = calification;
    }

    public int getSumOfCalifications() {
        return this.sumOfCalifications;
    }

    public void setSumOfCalifications(final int sumOfCalifications) {
        this.sumOfCalifications = sumOfCalifications;
    }

    public int getCountOfCalifications() {
        return this.countOfCalifications;
    }

    public void setCountOfCalifications(final int countOfCalifications) {
        this.countOfCalifications = countOfCalifications;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    public List<Calification> getCalifications() {
        return this.califications;
    }

    public void setCalifications(final List<Calification> califications) {
        this.califications = califications;
    }

}
