package nu.hjemme.persistence;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * @author Tor Egil Jacobsen
 */
public class PersistentData {
    private static final String PACKAGE_FOR_ENTITIES = "nu.hjemme.persistence.db";
    private static final String PACKAGE_FOR_DAOS = "nu.hjemme.persistence.dao";
    private static PersistentData instance;

    protected PersistentData() {
        instance = this;
    }

    public <T> T provideEntityFor(Class<T> interfaceToInitiate, Object... arguments) {
        Set<Class<? extends T>> subTypes = scanForSubTypesOf(interfaceToInitiate, getPackageForEntities());

        return arguments != null ? initWithConstructorUsing(interfaceToInitiate, subTypes, arguments) : provideInstanceFrom(subTypes, interfaceToInitiate);
    }

    public <T> T provideDaoFor(Class<T> interfaceToInitiate, Object... arguments) {
        Set<Class<? extends T>> subTypes = scanForSubTypesOf(interfaceToInitiate, getPackageForDaos());

        return arguments != null ? initWithConstructorUsing(interfaceToInitiate, subTypes, arguments) : provideInstanceFrom(subTypes, interfaceToInitiate);
    }

    private <T> Set<Class<? extends T>> scanForSubTypesOf(Class<T> persistentInterface, String packageToScan) {
        Reflections reflections = new Reflections(packageToScan);
        return reflections.getSubTypesOf(persistentInterface);
    }

    @SuppressWarnings("unchecked") private <T> T initWithConstructorUsing(Class<T> interfaceToInitiate, Set<Class<? extends T>> subTypes, Object[] arguments) {
        if (!subTypes.iterator().hasNext()) {
            return null;
        }

        for (Constructor<?> constructor : subTypes.iterator().next().getConstructors()) {
            if (arguments.length == constructor.getParameterCount()) {
                try {
                    return (T) constructor.newInstance(arguments);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw newIllegalArgumentException(interfaceToInitiate, arguments);
                }
            }
        }

        throw newIllegalArgumentException(interfaceToInitiate, arguments);
    }

    private <T> IllegalArgumentException newIllegalArgumentException(Class<T> interfaceToInitiate, Object[] arguments) {
        return new IllegalArgumentException(
                "unable to create instance of " + interfaceToInitiate + " with arguments like " + asList(arguments) + " from " + getPackageForDaos());
    }


    private <T> T provideInstanceFrom(Set<Class<? extends T>> subTypes, Class<T> persistentInterface) {
        if (!subTypes.iterator().hasNext()) {
            return null;
        }

        try {
            return subTypes.iterator().next().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("unable to create instance of " + persistentInterface + " from " + subTypes, e);
        }
    }

    protected String getPackageForEntities() {
        return PACKAGE_FOR_ENTITIES;
    }

    protected String getPackageForDaos() {
        return PACKAGE_FOR_DAOS;
    }

    public static PersistentData getInstance() {
        return instance;
    }

    public static void reset() {
        new PersistentData();
    }

    static {
        new PersistentData();
    }
}
