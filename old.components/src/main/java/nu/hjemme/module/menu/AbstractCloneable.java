package nu.hjemme.module.menu;

import nu.hjemme.client.domain.AbstractDomain;

/** @author Tor Egil Jacobsen */
public abstract class AbstractCloneable<Clone> extends AbstractDomain implements Cloneable {

    public abstract Clone clone() throws CloneNotSupportedException;

    @SuppressWarnings(value = "unchecked")
    protected <Clone> Clone getClone() {
        Clone clone;

        try {
            clone = (Clone) super.clone();
        } catch (CloneNotSupportedException cnse) {
            throw new IllegalStateException(cnse); // Should not occur
        }

        return clone;
    }
}
