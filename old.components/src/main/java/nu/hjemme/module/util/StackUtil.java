package nu.hjemme.module.util;


/**
 * A util class which provides util methods used for stack processing. It will also check the stack trace for the presence of a
 * test or a test case.
 * @author Tor Egil Jacobsen
 */
public class StackUtil {

    private static final String LINEFEED = "\n";
    private static final String TAB = "\t";
    private static final String EMPTY = "";

    // The static instance
    protected static volatile StackUtil instance = null;

    /**
     * @return the instance using double checked locking (which is not broken in java 1.5.0)
     */
    private static StackUtil getInstance() {
        if (instance == null) {
            synchronized (StackUtil.class) {
                if (instance == null) {
                    instance = new StackUtil();
                }
            }
        }

        return instance;
    }

    /**
     * Returns <code>true</code> if the class name is found in the stack.
     * @param className The name of the class to be found in the stack trace.
     * @return <code>true</code> if the class name is found in the stack.
     */
    public static boolean isInStack(final String className) {
        return getInstance().isInTheStack(className);
    }

    /**
     * Retrieves information from the stack trace element which is invoking this method.
     * @return The name of the class, the method, and line number.
     */
    public static String getStackInfo() {
        return getStackInfo(getInvokerElement());
    }

    /**
     * Retrieves information from the stack trace element which is invoking this method.
     * @return The name of the class, the method, and line number.
     * @param notTheseClasses The stack trace element returned should not be from these class names.
     */
    public static String getStackInfo(final String ... notTheseClasses) {
        return getStackInfo(getInstance().getValidElement(notTheseClasses));
    }

    /**
     * Retrieves information from the stack trace element which is invoking this method.
     * @return The name of the class, the method, and line number.
     * @param notThis The stack trace element returned should not be from this class.
     * @param <T> The type of Class which is used...
     */
    public static <T extends Object> String getStackInfo(final Class <T> notThis) {
        return getStackInfo(getInstance().getValidElement(new String[] {
            notThis.getName()
        }));
    }

    /**
     * Retrieves information from the stack trace element which is submitted.
     * @param elem The element from the stack trace where the information is to be retrieved.
     * @return The name of the class, the method, and line number.
     */
    public static String getStackInfo(final StackTraceElement elem) {
        return elem.toString();
    }

    /**
     * Retrieves the first valid stack trace element in the stack (i.e: not this utilty and the class submitted).
     * @param notTheseClasses The stack trace element returned should not be from these class names.
     * @return The first valid stack trace element in the stack.
     */
    public static StackTraceElement getValidStackTraceElement(final String ... notTheseClasses) {
        return getInstance().getValidElement(notTheseClasses);
    }

    /**
     * Retrieves the stack trace as a String.
     * @param trace The trace which is to be transformed to a string.
     * @return The stack trace as a String.
     */
    public static String getStringTrace(final StackTraceElement[] trace) {
        return getInstance().getStringStackTrace(trace);
    }

    /**
     * @return The stack trace element of the invoking class of the element invoking this method.
     */
    public static StackTraceElement getInvokerElement() {
        return new Throwable().getStackTrace()[2];
    }

    // The bean

    /**
     * To avoid initialising by other beans than this or sub, if present
     */
    protected StackUtil() {}

    /**
     * Retrieves the first valid stack trace element in the stack (i.e: not this utility and the class submitted).
     * @param notTheseClasses The stack trace element returned should not be from these class names.
     * @return The first valid stack trace element in the stack.
     * @param <T> The type of Class which is used...
     */
    private <T extends Object> StackTraceElement getValidElement(final String[] notTheseClasses) {
        if (notTheseClasses != null) {
            final StackTraceElement[] trace = new Throwable().getStackTrace();

            for (StackTraceElement elem : trace) {
                if (!elem.getClassName().equals(StackUtil.class.getName()) && !isOfThese(notTheseClasses, elem.getClassName())) {
                    return elem;
                }
            }
        }

        return null;
    }

    /**
     * Returns <code>true</code> if the class name is found in the stack.
     * @param className The name of the class to be found in the stack trace.
     * @return <code>true</code> if the class name is found in the stack.
     */
    private boolean isInTheStack(final String className) {
        final StackTraceElement[] trace = new Throwable().getStackTrace();
        boolean inStack = false;

        // The first and second element is this class, i.e. start array indexing on 2
        for (int i = 2; i < trace.length; i++) {
            final StackTraceElement elem = trace[i];

            if (elem.getClassName().indexOf(className) != -1) {
                inStack = true;
                break;
            }
        }

        return inStack;
    }

    /**
     * @param <T> The type of Class which is used...
     * @param theseClasses The class names which should checked against
     * @param className The class name checked for.
     * @return If the class name is equal to any of the classes provided.
     */
    private <T extends Object> boolean isOfThese(final String[] theseClasses, final String className) {
        for (String clazz : theseClasses) {
            if (className.equals(clazz)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves the stack trace as a String.
     * @param trace The trace which is to be transformed to a string.
     * @return The stack trace as a String.
     */
    private String getStringStackTrace(final StackTraceElement[] trace) {
        final StringBuilder stringTrace = new StringBuilder(EMPTY);

        for (StackTraceElement element : trace) {
            stringTrace.append(TAB).append(element).append(LINEFEED);
        }

        return stringTrace.toString();
    }
}
