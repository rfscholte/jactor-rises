package nu.hjemme.persistence;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.domain.Profile;

/**
 * @author Tor Egil Jacobsen
 */
public interface ProfileEntity extends Profile {
    void setDescription(String descritpion);
}
