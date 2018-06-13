package com.github.jactor.rises.client.domain;

import com.github.jactor.rises.client.datatype.EmailAddress;
import com.github.jactor.rises.client.datatype.UserName;

import java.util.Set;

public interface User extends Persistent {

    UserName getUserName();

    Person getPerson();

    EmailAddress getEmailAddress();

    default boolean isUserNameEmailAddress() {
        return getEmailAddress() != null && getEmailAddress().isSameAs(getUserName());
    }
}
