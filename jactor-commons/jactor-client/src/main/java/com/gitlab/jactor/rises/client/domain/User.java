package com.gitlab.jactor.rises.client.domain;

import com.gitlab.jactor.rises.client.datatype.EmailAddress;
import com.gitlab.jactor.rises.client.datatype.Username;

public interface User extends Persistent {

    Username getUsername();

    Person getPerson();

    EmailAddress getEmailAddress();

    default boolean isUserNameEmailAddress() {
        return getEmailAddress() != null && getEmailAddress().isSameAs(getUsername());
    }
}
