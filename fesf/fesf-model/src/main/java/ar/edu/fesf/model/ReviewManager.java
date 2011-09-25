package ar.edu.fesf.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Contains a list of califications and a list of simple comments without califications.
 */
public class ReviewManager extends Entity {

    private List<Calification> califications = new ArrayList<Calification>();

    private int avgCalification = 0;

    private int sumOfCalifications = 0;

    private int countOfCalifications = 0;

    private List<SimpleComment> comments = new ArrayList<SimpleComment>();

    /* Methods */
    public void addCalification(final Calification calif) {

        this.setSumOfCalifications(this.getSumOfCalifications() + calif.getUserCalification());
        this.setCountOfCalifications(this.getCountOfCalifications() + 1);

        this.setAvgCalification(this.getSumOfCalifications() / this.getCountOfCalifications());

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

    public List<SimpleComment> getComments() {
        return this.comments;
    }

    public void setComments(final List<SimpleComment> comments) {
        this.comments = comments;
    }

    public List<Calification> getCalifications() {
        return this.califications;
    }

    public void setCalifications(final List<Calification> califications) {
        this.califications = califications;
    }

}
