package ar.edu.fesf.application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the repository of entity homes
 */
public class HomeRepository {

    /* VARIABLES ************************************************** */

    private Map<Class<?>, Home<?>> homes = new HashMap<Class<?>, Home<?>>();

    /* CONSTRUCTOR ************************************************** */

    public HomeRepository(final HomeFactory factory) {
        factory.addHomes(this.homes);
    }

    /* GET&SET************************************************** */

    public Map<Class<?>, Home<?>> getHomes() {
        return this.homes;
    }

    public void setHomes(final Map<Class<?>, Home<?>> homes) {
        this.homes = homes;
    }

    public Collection<Home<?>> getAllHomes() {
        return this.getHomes().values();
    }

    @SuppressWarnings("unchecked")
    public <T extends Persistible> Home<T> getHome(final Class<? extends T> type) {
        return (Home<T>) this.homes.get(type);
    }

}
