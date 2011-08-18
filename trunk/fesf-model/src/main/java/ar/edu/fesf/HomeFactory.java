package ar.edu.fesf;

import java.util.Map;

public abstract class HomeFactory {

    /* METODOS ************************************************** */

    public final void addHomes(final Map<Class<?>, Home<?>> homes) {

        for (Class<?> clazz : new FesfEntities().getEntities()) {
            homes.put(clazz, this.newHomeInstance(clazz));
        }

    }

    public abstract Home<?> newHomeInstance(Class<?> clazz);

}