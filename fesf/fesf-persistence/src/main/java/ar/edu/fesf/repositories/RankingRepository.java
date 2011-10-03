package ar.edu.fesf.repositories;

import ar.edu.fesf.model.Ranking;

public class RankingRepository extends HibernateGenericDAO<Ranking> {

    private static final long serialVersionUID = 8674620912019311820L;

    @Override
    protected Class<Ranking> getDomainClass() {
        return Ranking.class;
    }

}
