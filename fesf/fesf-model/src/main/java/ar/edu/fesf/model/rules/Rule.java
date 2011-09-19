package ar.edu.fesf.model.rules;

public interface Rule<T> {

    boolean apply(T value);

}
