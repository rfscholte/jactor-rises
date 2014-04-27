package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.BlogEntry;
import nu.hjemme.module.persistence.BlogEntity;
import nu.hjemme.module.persistence.PersonEntity;

/** @author Tor Egil Jacobsen */
public interface MutableBlogEntry extends BlogEntry {
    void setCreatorName(Name creatorName);

    void setCreator(PersonEntity creator);

    void setEntry(String entry);

    void setBlogEntity(BlogEntity blogEntity);
}
