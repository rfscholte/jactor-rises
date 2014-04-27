package nu.hjemme.business.persistence.mutable;

import nu.hjemme.business.persistence.BlogEntity;
import nu.hjemme.business.persistence.PersonEntity;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.BlogEntry;

/** @author Tor Egil Jacobsen */
public interface MutableBlogEntry extends BlogEntry {
    void setCreatorName(Name creatorName);

    void setCreator(PersonEntity creator);

    void setEntry(String entry);

    void setBlogEntity(BlogEntity blogEntity);
}
