package com.gitlab.jactor.rises.client.domain;

import com.gitlab.jactor.rises.client.datatype.Name;

import java.util.Locale;

public interface Person extends Persistent {

    String getDescription();

    Address getAddress();

    Name getFirstName();

    Name getSurname();

    Locale getLocale();

}