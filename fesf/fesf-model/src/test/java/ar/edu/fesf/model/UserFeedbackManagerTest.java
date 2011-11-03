package ar.edu.fesf.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserFeedbackManagerTest {

    private UserFeedbackManager review = new UserFeedbackManager();

    @Test
    public void initialState() {
        assertTrue("The list of comments must be empty", this.review.getComments().isEmpty());
        assertEquals("The count of califications must be zero", this.review.getCountOfCalifications(), 0);
        assertEquals("The sum of califications must be zero", this.review.getSumOfCalifications(), 0);
        assertEquals("The initial calification must be zero", this.review.getAvgCalification(), 0);
    }

    @Test
    public void addCalification() {
        int sum = 9 + 10 + 4 + 3 + 5;
        int count = 5;
        int calif = sum / count;

        Calification c9 = new Calification();
        c9.setUserCalification(9);

        Calification c10 = new Calification();
        c10.setUserCalification(10);

        Calification c4 = new Calification();
        c4.setUserCalification(4);

        Calification c3 = new Calification();
        c3.setUserCalification(3);

        Calification c5 = new Calification();
        c5.setUserCalification(5);

        this.review.addCalification(c9);
        this.review.addCalification(c10);
        this.review.addCalification(c4);
        this.review.addCalification(c3);
        this.review.addCalification(c5);

        assertEquals("The count of califications must be " + count, this.review.getCountOfCalifications(), count);
        assertEquals("The sum of califications must be " + sum, this.review.getSumOfCalifications(), sum);
        assertEquals("The calification must be " + calif, this.review.getAvgCalification(), calif);
    }

}
