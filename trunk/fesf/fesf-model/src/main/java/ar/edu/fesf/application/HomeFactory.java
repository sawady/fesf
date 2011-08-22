package ar.edu.fesf.application;

import java.util.List;
import java.util.Map;

public abstract class HomeFactory {

    /* METODOS ************************************************** */

    public final void addHomes(final Map<Class<?>, Home<?>> homes) {

        List<Class<?>> entities = new FesfEntities().getEntities();

        for (Class<?> clazz : entities) {
            homes.put(clazz, this.newHomeInstance(clazz));
        }

    }

    public abstract Home<?> newHomeInstance(Class<?> clazz);

}