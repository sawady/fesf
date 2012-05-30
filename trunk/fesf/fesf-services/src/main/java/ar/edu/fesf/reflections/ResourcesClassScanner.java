package ar.edu.fesf.reflections;

import org.reflections.scanners.AbstractScanner;

public class ResourcesClassScanner extends AbstractScanner {

	@Override
	public void scan(Object cls) {
		@SuppressWarnings("unchecked")
		final String className = getMetadataAdapter().getClassName(cls);
		if (!className.contains("$")) {
			this.getStore().put(className, className);
		}
	}
	


}
