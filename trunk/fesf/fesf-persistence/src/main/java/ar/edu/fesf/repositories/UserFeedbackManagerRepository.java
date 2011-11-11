package ar.edu.fesf.repositories;

import ar.edu.fesf.model.UserFeedbackManager;

public class UserFeedbackManagerRepository extends HibernateGenericDAO<UserFeedbackManager> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<UserFeedbackManager> getDomainClass() {
        return UserFeedbackManager.class;
    }
}
