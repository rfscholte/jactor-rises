package com.github.jactor.rises.client.domain;

import com.github.jactor.rises.client.datatype.Name;

import java.util.Locale;

public interface Person extends Persistent {

    String getDescription();

    Address getAddress();

    Name getFirstName();

    Name getSurname();

    Locale getLocale();

}
