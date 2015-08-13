package nu.hjemme.persistence;

import org.reflections.Reflections;

import java.util.Set;

/**
 * @author Tor Egil Jacobsen
 */
public class PersistentData {
    private static final String PACKAGE_TO_SCAN = "nu.hjemme.persistence.db";
    private static PersistentData instance;

    protected PersistentData() {
        instance = this;
    }

    public <T> T provideFor(Class<T> persistentInterface) {
        Reflections reflections = new Reflections(getPackageToScan());
        Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(persistentInterface);

        return provideInstanceFrom(subTypes, persistentInterface);
    }

    private <T> T provideInstanceFrom(Set<Class<? extends T>> subTypes, Class<T> persistentInterface) {
        if (!subTypes.iterator().hasNext()) {
            return null;
        }

        try {
            return subTypes.iterator().next().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("unable to create instance of " + persistentInterface + " from " + subTypes);
        }
    }

    protected String getPackageToScan() {
        return PACKAGE_TO_SCAN;
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
