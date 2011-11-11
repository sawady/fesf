package ar.edu.fesf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Comment;
import ar.edu.fesf.model.UserFeedbackManager;

public class UserFeedbackService extends GenericTransactionalRepositoryService<UserFeedbackManager> {

    private static final long serialVersionUID = 1L;

    @Transactional(readOnly = true)
    public List<Comment> getComments(final UserFeedbackManager userFeedbackManager, final int countOfComments) {
        UserFeedbackManager userfeedbackManagerDB = this.initializeFields(userFeedbackManager, "comments");
        List<Comment> comments = new ArrayList<Comment>();
        int listSize = userfeedbackManagerDB.getComments().size();
        comments.addAll(((List<Comment>) this.getRepository().initialize(userfeedbackManagerDB.getComments(),
                countOfComments)).subList(0, listSize < countOfComments ? listSize : countOfComments));
        return comments;
    }

}
