package ar.edu.fesf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.fesf.model.Review;

public class ReviewTest {

    private Review review = new Review();

    @Test
    public void initialState() {
        assertTrue("The list of comments must be empty", this.review.getComments().isEmpty());
        assertEquals("The count of califications must be zero", this.review.getCountOfCalifications(), 0);
        assertEquals("The sum of califications must be zero", this.review.getSumOfCalifications(), 0);
        assertEquals("The initial calification must be zero", this.review.getCalification(), 0);
    }

    @Test
    public void addCalification() {
        int sum = 9 + 10 + 4 + 3 + 5;
        int count = 5;
        int calif = sum / count;
        this.review.addCalification(9);
        this.review.addCalification(10);
        this.review.addCalification(4);
        this.review.addCalification(3);
        this.review.addCalification(5);

        assertEquals("The count of califications must be " + count, this.review.getCountOfCalifications(), count);
        assertEquals("The sum of califications must be " + sum, this.review.getSumOfCalifications(), sum);
        assertEquals("The calification must be " + calif, this.review.getCalification(), calif);
    }

}
