package ar.edu.fesf.reflections;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
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
		Collection<String> classNames = CollectionUtils.collect(classes, new ClassNameTransformer());
		CollectionUtils.filter(classNames, new Predicate() {

			@Override
			public boolean evaluate(Object object) {
				return !object.toString().contains("$");
			}
		});
		return ReflectionUtils.forNames(classNames, this.configuration.getClassLoaders());
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
