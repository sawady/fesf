package ar.edu.fesf.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Publisher;
import ar.edu.fesf.others.GenericTransactionalRepositoryService;

@Service
public class PublisherService extends GenericTransactionalRepositoryService<Publisher> {

    private static final long serialVersionUID = 1L;

    @Transactional(readOnly = true)
    public Iterator<String> getPublishersNamedLike(final String input) {
        List<String> names = new ArrayList<String>();
        // this.findByPropertyLike("name", input)
        for (Publisher publisher : this.findAll()) {
            names.add(publisher.getName());
        }

        return names.iterator();
    }

}
