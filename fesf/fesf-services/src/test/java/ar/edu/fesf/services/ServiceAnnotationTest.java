package ar.edu.fesf.services;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class ServiceAnnotationTest {

    private static final String PACKAGE_PATH_SEPARATOR = ".";

    /*
     * A string which is used to identify getter methods. All methods whose name contains the given string are
     * considered as getter methods.
     */
    private static final String GETTER_METHOD_NAME_ID = "get";

    private static final String FILE_PATH_SEPARATOR = System.getProperty("file.separator");

    /*
     * The file path to the root folder of service package. If the absolute path to the service package is
     * /users/foo/classes/com/bar/service and the classpath base directory is /users/foo/classes, the value of this
     * constant must be /com/bar/service.
     */
    private static final String SERVICE_BASE_PACKAGE_PATH = "/ar/edu/fesf/services";

    /*
     * A string which is used to identify setter methods. All methods whose name contains the given string are
     * considered as setter methods.
     */
    private static final String SETTER_METHOD_NAME_ID = "set";

    /*
     * A string which is used to identify the test classes. All classes whose name contains the given string are
     * considered as test classes.
     */
    private static final String TEST_CLASS_FILENAME_ID = "Test";

    private List<Class<?>> serviceClasses;

    /**
     * Iterates through all the classes found under the service base package path (and its sub directories) and inserts
     * all service classes to the serviceClasses array.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Before
    public void findServiceClasses() throws IOException, ClassNotFoundException {
        this.serviceClasses = new ArrayList<Class<?>>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:" + SERVICE_BASE_PACKAGE_PATH + "/**/*.class");
        for (Resource resource : resources) {
            if (this.isNotTestClass(resource)) {
                String serviceClassCandidateNameWithPackage = this.parseClassNameWithPackage(resource);
                ClassLoader classLoader = resolver.getClassLoader();
                Class<?> serviceClassCandidate = classLoader.loadClass(serviceClassCandidateNameWithPackage);
                if (this.isNotInterface(serviceClassCandidate)) {
                    if (this.isNotException(serviceClassCandidate)) {
                        if (this.isNotEnum(serviceClassCandidate)) {
                            if (this.isNotAnonymousClass(serviceClassCandidate)) {
                                this.serviceClasses.add(serviceClassCandidate);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if the resource given a as parameter is a test class. This method returns true if the resource is not a
     * test class and false otherwise.
     * 
     * @param resource
     * @return
     */
    private boolean isNotTestClass(final Resource resource) {
        return !resource.getFilename().contains(TEST_CLASS_FILENAME_ID);
    }

    /**
     * Checks if the resource given as a parameter is an exception class. This method returns true if the class is not
     * an exception class and false otherwise.
     * 
     * @param exceptionCanditate
     * @return
     */
    private boolean isNotException(final Class<?> exceptionCanditate) {
        return !Exception.class.isAssignableFrom(exceptionCanditate)
                && !RuntimeException.class.isAssignableFrom(exceptionCanditate)
                && !Throwable.class.isAssignableFrom(exceptionCanditate);
    }

    /**
     * Parses a class name from the absolute path of the resource given as a parameter and returns the parsed class
     * name. E.g. if the absolute path of the resource is /user/foo/classes/com/foo/Bar.class, this method returns
     * com.foo.Bar.
     * 
     * @param resource
     * @return
     * @throws IOException
     */
    private String parseClassNameWithPackage(final Resource resource) throws IOException {
        String pathFromClasspathRoot = this.parsePathFromClassPathRoot(resource.getFile().getAbsolutePath());
        String pathWithoutFilenameSuffix = this.parsePathWithoutFilenameSuffix(pathFromClasspathRoot);
        return this.buildClassNameFromPath(pathWithoutFilenameSuffix);
    }

    /**
     * Parses the path which starts from the classpath root directory by using the absolute path given as a parameter.
     * Returns the parsed path. E.g. If the absolute path is /user/foo/classes/com/foo/Bar.class and the classpath root
     * directory is /user/foo/classes/, com/foo/Bar.class is returned.
     * 
     * @param absolutePath
     * @return
     */
    private String parsePathFromClassPathRoot(final String absolutePath) {
        int classpathRootIndex = absolutePath.indexOf(SERVICE_BASE_PACKAGE_PATH);
        return absolutePath.substring(classpathRootIndex + 1);
    }

    /**
     * Removes the file suffix from the path given as a parameter and returns new path without the suffix. E.g. If path
     * is com/foo/Bar.class, com/foo/Bar is returned.
     * 
     * @param path
     * @return
     */
    private String parsePathWithoutFilenameSuffix(final String path) {
        int prefixIndex = path.indexOf(PACKAGE_PATH_SEPARATOR);
        return path.substring(0, prefixIndex);
    }

    /**
     * Builds a class name with package information from a path given as a parameter and returns the class name with
     * package information. e.g. If a path com/foo/Bar is given as a parameter, com.foo.Bar is returned.
     * 
     * @param path
     * @return
     */
    private String buildClassNameFromPath(final String path) {
        return path.replace(FILE_PATH_SEPARATOR, PACKAGE_PATH_SEPARATOR);
    }

    /**
     * Checks if the class given as an argument is an interface or not. Returns false if the class is not an interface
     * and true otherwise.
     * 
     * @param interfaceCanditate
     * @return
     */
    private boolean isNotInterface(final Class<?> interfaceCanditate) {
        return !interfaceCanditate.isInterface();
    }

    /**
     * Checks if the class given as an argument is an Enum or not. Returns false if the class is not Enum and true
     * otherwise.
     * 
     * @param enumCanditate
     * @return
     */
    private boolean isNotEnum(final Class<?> enumCanditate) {
        return !enumCanditate.isEnum();
    }

    /**
     * Checks if the class given as a parameter is an anonymous class. Returns true if the class is not an anonymous
     * class and false otherwise.
     * 
     * @param anonymousClassCanditate
     * @return
     */
    private boolean isNotAnonymousClass(final Class<?> anonymousClassCanditate) {
        return !anonymousClassCanditate.isAnonymousClass();
    }

    /**
     * Verifies that each method which is declared in a service class and which is not a getter or setter method is
     * annotated with Transactional annotation. This test also ensures that the rollbackFor property of Transactional
     * annotation specifies all checked exceptions which are thrown by the service method.
     */
    @Test
    public void eachServiceMethodHasTransactionalAnnotation() {
        for (Class<?> serviceClass : this.serviceClasses) {
            Method[] serviceMethods = serviceClass.getMethods();
            for (Method serviceMethod : serviceMethods) {
                if (this.isMethodDeclaredInServiceClass(serviceMethod, serviceClass)) {
                    if (this.isNotGetterOrSetterMethod(serviceMethod)) {
                        boolean transactionalAnnotationFound = serviceMethod.isAnnotationPresent(Transactional.class);
                        assertTrue("Method " + serviceMethod.getName() + " of " + serviceClass.getName()
                                + " class must be annotated with @Transactional annotation.",
                                transactionalAnnotationFound);
                        if (transactionalAnnotationFound) {
                            if (this.methodThrowsCheckedExceptions(serviceMethod)) {
                                boolean rollbackPropertySetCorrectly = this
                                        .rollbackForPropertySetCorrectlyForTransactionalAnnotation(
                                                serviceMethod.getAnnotation(Transactional.class),
                                                serviceMethod.getExceptionTypes());
                                assertTrue("Method " + serviceMethod.getName() + "() of " + serviceClass.getName()
                                        + " class must set rollbackFor property of Transactional annotation correctly",
                                        rollbackPropertySetCorrectly);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks that the method given as a parameter is declared in a service class given as a parameter. Returns true if
     * the method is declated in service class and false otherwise.
     * 
     * @param method
     * @param serviceClass
     * @return
     */
    private boolean isMethodDeclaredInServiceClass(final Method method, final Class<?> serviceClass) {
        return method.getDeclaringClass().equals(serviceClass);
    }

    /**
     * Checks if the method given as parameter is a getter or setter method. Returns true if the method is a getter or
     * setter method an false otherwise.
     * 
     * @param method
     * @return
     */
    private boolean isNotGetterOrSetterMethod(final Method method) {
        return !method.getName().contains(SETTER_METHOD_NAME_ID) && !method.getName().contains(GETTER_METHOD_NAME_ID);
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

    /**
     * Verifies that each service class is annotated with @Service annotation.
     */
    @Test
    public void eachServiceClassIsAnnotatedWithServiceAnnotation() {
        for (Class<?> serviceClass : this.serviceClasses) {
            assertTrue(serviceClass.getSimpleName() + " must be annotated with @Service annotation",
                    serviceClass.isAnnotationPresent(Service.class));
        }
    }
}
