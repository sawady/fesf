package ar.edu.fesf.reflections;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

public class MethodClosure implements Closure {
	
	private Set<Method> methods = new HashSet();

	public MethodClosure(Set<Method> methods) {
		this.methods = methods;
	}

	@Override
	public void execute(Object input) {
		Class clazz = (Class) input;
		Collection methodsPublic = Arrays.asList(clazz.getMethods());
		Collection declaredMethod = Arrays.asList(clazz.getDeclaredMethods());
		methods.addAll(CollectionUtils.intersection(declaredMethod, methodsPublic));
	}

}
