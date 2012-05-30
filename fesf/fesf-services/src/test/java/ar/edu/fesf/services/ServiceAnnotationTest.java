package ar.edu.fesf.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.reflections.FestReflections;
import ar.edu.fesf.reflections.ResourcesClassScanner;



public class ServiceAnnotationTest {
	
	private static final String PACKAGE_NAME = "ar.edu.fesf.services";
	private static final String SERVICE_FILTER = "+ar\\.edu\\.fesf\\.services.*Service.*";
	private static final String TEST_FILTER = "-ar\\.edu\\.fesf\\.services.*Test.*";


    /**
     * Verifies that each method which is declared in a service class and which is not a getter or setter method is
     * annotated with Transactional annotation. This test also ensures that the rollbackFor property of Transactional
     * annotation specifies all checked exceptions which are thrown by the service method.
     */
	@Test
	public void eachServiceMethodHasTransactionalAnnotation() {
		FestReflections reflections = new FestReflections(PACKAGE_NAME);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Service.class);
		Collection<Method> methods = reflections.getPublicMethods(annotated);
		for (Method serviceMethod : methods) {
			boolean transactionalAnnotationFound = serviceMethod.isAnnotationPresent(Transactional.class);
			assertTrue(this.missingTransactionAnnotationsMessage(serviceMethod), transactionalAnnotationFound);
			if (transactionalAnnotationFound) {
				if (this.methodThrowsCheckedExceptions(serviceMethod)) {
					boolean rollbackPropertySetCorrectly = this.rollbackForPropertySetCorrectlyForTransactionalAnnotation(
									serviceMethod.getAnnotation(Transactional.class),serviceMethod.getExceptionTypes());
					assertTrue(this.missingRollbackForPropertyMessage(serviceMethod), rollbackPropertySetCorrectly);
				}
			}
		}
	}	
 
	/**
     * Verifies that each service class is annotated with @Service annotation.
     */
    @Test
    public void eachServiceClassIsAnnotatedWithServiceAnnotation() {
		FestReflections reflections = new FestReflections(
				new ConfigurationBuilder()
						.filterInputsBy(FilterBuilder.parse(SERVICE_FILTER + " , " + TEST_FILTER))
						.setScanners(new ResourcesClassScanner())
						.addClassLoader(ClasspathHelper.staticClassLoader())
						.setUrls(ClasspathHelper.forPackage(PACKAGE_NAME)));
		List<Class<?>> classes = reflections.getClasses(ResourcesClassScanner.class);
		
		assertFalse(classes.isEmpty());
		for (Class<?> serviceClass : classes) {
			assertTrue(serviceClass.getSimpleName()	+ " must be annotated with @Service annotation", serviceClass.isAnnotationPresent(Service.class));
		}
    }

    
    /**
     * Checks if the method given as a parameter throws checked exceptions. Returns true if the method throws checked
     * exceptions and false otherwise.
     * 
     * @param method
     * @return
     */
    private boolean methodThrowsCheckedExceptions(final Method method) {
        return method.getExceptionTypes().length > 0;
    }

    /**
     * Checks if the transactional annotation given as a parameter specifies all checked exceptions given as a parameter
     * as a value of rollbackFor property. Returns true if all exceptions are specified and false otherwise.
     * 
     * @param annotation
     * @param thrownExceptions
     * @return
     */
    private boolean rollbackForPropertySetCorrectlyForTransactionalAnnotation(final Annotation annotation,
            final Class<?>[] thrownExceptions) {
        boolean rollbackForSet = true;

        if (annotation instanceof Transactional) {
            Transactional transactional = (Transactional) annotation;
            List<Class<? extends Throwable>> rollbackForClasses = Arrays.asList(transactional.rollbackFor());
            for (Class<?> thrownException : thrownExceptions) {
                if (!rollbackForClasses.contains(thrownException)) {
                    rollbackForSet = false;
                    break;
                }
            }
        }

        return rollbackForSet;
    }

	private String missingRollbackForPropertyMessage(Method serviceMethod) {
		return "Method "
				+ serviceMethod.getName()
				+ "() of "
				+ serviceMethod.getDeclaringClass().getName()
				+ " class must set rollbackFor property of Transactional annotation correctly";
	}

	private String missingTransactionAnnotationsMessage(Method serviceMethod) {
		return "Method " + serviceMethod.getName() + " of "
				+ serviceMethod.getDeclaringClass().getName()
				+ " class must be annotated with @Transactional annotation.";
	}

}
