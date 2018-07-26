package com.github.jactor.rises.model.domain;

import com.github.jactor.rises.commons.datatype.Name;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Any persistent domain must have an identifier and data about creation from the persistent layer
 */
public interface Persistent {
    Serializable getId();

    Name getCreatedBy();

    LocalDateTime getCreationTime();

    Name getUpdatedBy();

    LocalDateTime getUpdatedTime();
}
