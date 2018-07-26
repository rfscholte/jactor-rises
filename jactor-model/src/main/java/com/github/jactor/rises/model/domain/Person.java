package com.github.jactor.rises.model.domain;

import com.github.jactor.rises.commons.datatype.Name;

import java.util.Locale;

public interface Person extends Persistent {

    String getDescription();

    Address getAddress();

    Name getFirstName();

    Name getSurname();

    Locale getLocale();

}
