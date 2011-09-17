package ar.edu.fesf.application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * TODO No entiendo que hace esta clase, la utilizan para algo ??
 */
public class Application {

    /* VARIABLES ************************************************** */

    private Map<Class<?>, Home<?>> homes = new HashMap<Class<?>, Home<?>>();

    /* CONSTRUCTOR ************************************************** */

    public Application(final HomeFactory factory) {
        factory.addHomes(homes);
    }

    /* GET&SET************************************************** */

    public Map<Class<?>, Home<?>> getHomes() {
        return homes;
    }

    public void setHomes(final Map<Class<?>, Home<?>> homes) {
        this.homes = homes;
    }

    public Collection<Home<?>> getAllHomes() {
        return this.getHomes().values();
    }

    @SuppressWarnings("unchecked")
    public <T extends Persistible> Home<T> getHome(final Class<? extends T> type) {
        return (Home<T>) homes.get(type);
    }

}
