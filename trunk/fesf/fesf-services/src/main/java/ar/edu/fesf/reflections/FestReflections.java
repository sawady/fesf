package ar.edu.fesf.reflections;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.apache.commons.collections.CollectionUtils;
import org.reflections.Configuration;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;


public class FestReflections extends Reflections {

	public FestReflections(final Configuration configuration) {
		super(configuration);
	}

	public FestReflections(final String prefix, @Nullable final Scanner... scanners) {
		super((Object) prefix, scanners);
	}

	public List<Class<?>> getClasses(Class<ResourcesClassScanner> class1) {
		Collection<String> classes = this.getStore().get(class1).values();
		return ReflectionUtils.forNames(classes, this.configuration.getClassLoaders());
	}

	public Collection<Method> getPublicMethods(Set<Class<?>> classes) {
		Collection<Method> methods = this.getMethods(classes);
		CollectionUtils.filter(methods, new GetSetMethodPredicate());
		return methods;
	}

	private Set<Method> getMethods(Set<Class<?>> annotated) {
		final Set<Method> methods = new HashSet();
		CollectionUtils.forAllDo(annotated, new MethodClosure(methods));
		return methods;
	}


}
