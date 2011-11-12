package ar.edu.fesf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Comment;
import ar.edu.fesf.model.UserFeedbackManager;
import ar.edu.fesf.services.dtos.CommentDTO;

public class UserFeedbackService extends GenericTransactionalRepositoryService<UserFeedbackManager> {

    private static final long serialVersionUID = 1L;

    @Transactional(readOnly = true)
    public List<CommentDTO> getComments(final UserFeedbackManager userFeedbackManager, final int countOfComments) {
        UserFeedbackManager userfeedbackManagerDB = this.initializeFields(userFeedbackManager, "comments");
        List<CommentDTO> resultComments = new ArrayList<CommentDTO>();
        int listSize = userfeedbackManagerDB.getComments().size();
        List<Comment> comments = ((List<Comment>) this.getRepository().initialize(userfeedbackManagerDB.getComments(),
                countOfComments)).subList(0, listSize < countOfComments ? listSize : countOfComments);
        for (Comment comment : comments) {
            resultComments.add(new CommentDTO(comment));
        }
        return resultComments;
    }
}
