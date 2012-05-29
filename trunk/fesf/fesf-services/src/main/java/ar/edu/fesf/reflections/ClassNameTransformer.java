package ar.edu.fesf.reflections;

import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author mbusso
 *
 */
public class ClassNameTransformer implements Transformer {

	@Override
	public Object transform(Object input) {
		String className = (String) input;		
		return StringUtils.substringBeforeLast(className, ".class").replace("/", ".");
	}

}
