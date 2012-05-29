package ar.edu.fesf.reflections;

import org.reflections.scanners.ResourcesScanner;

public class ResourcesClassScanner extends ResourcesScanner {
	
	@Override
	public boolean acceptsInput(String file) {
		return file.endsWith(".class");
	}

}
