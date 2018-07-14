package com.gitlab.jactor.rises.client.domain;

import com.gitlab.jactor.rises.client.datatype.Country;

public interface Address extends Persistent {

    String getAddressLine1();

    String getAddressLine2();

    String getAddressLine3();

    String getCity();

    Country getCountry();

    Integer getZipCode();
}
