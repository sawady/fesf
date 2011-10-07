package ar.edu.fesf.controllers;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

/**
 * Filter for accepting only feedback messages with this error level.
 * 
 * You can use this code under Apache 2.0 license, as long as you retain the copyright messages.
 * 
 * Tested with Wicket 1.3.4
 * 
 * @author Daan, StuQ.nl
 */
public class ErrorLevelsFeedbackMessageFilter implements IFeedbackMessageFilter {

    private static final long serialVersionUID = 1L;

    /** The minimum error level */
    private int[] filteredErrorLevels;

    /**
     * Constructor
     * 
     * @param filteredErrorLevels
     *            The FeedbackMessages that have thes error levels will not be shown.
     */
    public ErrorLevelsFeedbackMessageFilter(final int[] filteredErrorLevels) {
        this.setFilteredErrorLevels(filteredErrorLevels);
    }

    /**
     * Method accept, only accept FeedbackMessages that are not in the list of error levels to filter.
     * 
     * @param message
     *            of type FeedbackMessage
     * @return boolean
     */
    @Override
    public boolean accept(final FeedbackMessage message) {
        for (int errorLevel : this.getFilteredErrorLevels()) {
            if (message.getLevel() == errorLevel) {
                return false;
            }
        }

        return true;
    }

    private void setFilteredErrorLevels(final int[] filteredErrorLevels) {
        this.filteredErrorLevels = filteredErrorLevels.clone();
    }

    private int[] getFilteredErrorLevels() {
        return this.filteredErrorLevels.clone();
    }
}
