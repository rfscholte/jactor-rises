package nu.hjemme.persistence.client;

import nu.hjemme.persistence.client.dao.DefaultUserDao;
import nu.hjemme.persistence.client.dao.UserDao;
import nu.hjemme.persistence.client.domain.DefaultAddressEntity;
import nu.hjemme.persistence.client.domain.DefaultBlogEntity;
import nu.hjemme.persistence.client.domain.DefaultBlogEntryEntity;
import nu.hjemme.persistence.client.domain.DefaultGuestBookEntity;
import nu.hjemme.persistence.client.domain.DefaultGuestBookEntryEntity;
import nu.hjemme.persistence.client.domain.DefaultPersistentEntry;
import nu.hjemme.persistence.client.domain.DefaultPersonEntity;
import nu.hjemme.persistence.client.domain.DefaultUserEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class PersistentData {
    private static final Map<Class, Class> SUPPORTED_CLASSES = new HashMap<>();

    private static PersistentData instance;

    protected PersistentData() {
        instance = this;
    }

    @SuppressWarnings("unchecked") public <T> T provideInstanceFor(Class<T> interfaceToInitiate, Object... arguments) {
        if (SUPPORTED_CLASSES.containsKey(interfaceToInitiate)) {
            if (arguments == null || arguments.length == 0) {
                return (T) initializeWithoutArgumentsUsing(interfaceToInitiate);
            }

            return (T) initializeWithConstructorUsing(SUPPORTED_CLASSES.get(interfaceToInitiate), arguments);
        }

        throw newIllegalArgumentException(interfaceToInitiate, arguments);
    }

    private Object initializeWithoutArgumentsUsing(Class<?> interfaceToInitiate) {
        try {
            return SUPPORTED_CLASSES.get(interfaceToInitiate).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw newIllegalArgumentException(interfaceToInitiate, e);
        }
    }

    private Object initializeWithConstructorUsing(Class<?> classToInitialize, Object[] arguments) {
        for (Constructor<?> constructor : classToInitialize.getConstructors()) {
            if (arguments.length == constructor.getParameterCount()) {
                try {
                    return constructor.newInstance(arguments);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw newIllegalArgumentException(classToInitialize, e, arguments);
                }
            }
        }

        throw newIllegalArgumentException(classToInitialize, arguments);
    }

    private IllegalArgumentException newIllegalArgumentException(Class<?> theClass, Object... arguments) {
        return new IllegalArgumentException(
                "unable to create instance of " + theClass + (arguments != null && arguments.length > 0 ? " with arguments like " + asList(arguments) : "")
        );
    }

    private IllegalArgumentException newIllegalArgumentException(Class<?> theClass, Exception e, Object... arguments) {
        return new IllegalArgumentException(
                "unable to create instance of " + theClass + (arguments != null && arguments.length > 0 ? " with arguments like " + asList(arguments) : ""), e
        );
    }

    static {
        new PersistentData();
    }

    public static PersistentData getInstance() {
        return instance;
    }

    public static void reset() {
        new PersistentData();
    }

    private static <F, I extends F> void put(Class<F> face, Class<I> impl) {
        SUPPORTED_CLASSES.put(face, impl);
    }

    static {
        put(AddressEntity.class, DefaultAddressEntity.class);
        put(BlogEntity.class, DefaultBlogEntity.class);
        put(BlogEntryEntity.class, DefaultBlogEntryEntity.class);
        put(GuestBookEntity.class, DefaultGuestBookEntity.class);
        put(GuestBookEntryEntity.class, DefaultGuestBookEntryEntity.class);
        put(PersistentEntry.class, DefaultPersistentEntry.class);
        put(PersonEntity.class, DefaultPersonEntity.class);
        put(UserEntity.class, DefaultUserEntity.class);
        put(UserDao.class, DefaultUserDao.class);
    }
}
