/**
 * 
 */
package ar.edu.fesf.reflections;

import java.lang.reflect.Method;

import org.apache.commons.collections.Predicate;

/**
 * @author mbusso
 *
 */
public class GetSetMethodPredicate implements Predicate {

	private static final String SETTER_METHOD_NAME_ID = "set";
	private static final String GETTER_METHOD_NAME_ID = "get";

	@Override
	public boolean evaluate(Object object) {
		Method method = (Method) object;
		return isNotGetterOrSetterMethod(method);
	}
	
	/**
	 * Checks if the method given as parameter is a getter or setter method.
	 * Returns true if the method is a getter or setter method an false
	 * otherwise.
	 * 
	 * @param method
	 * @return
	 */
	private boolean isNotGetterOrSetterMethod(final Method method) {
		return !method.getName().contains(SETTER_METHOD_NAME_ID) && !method.getName().contains(GETTER_METHOD_NAME_ID);
	}

}
