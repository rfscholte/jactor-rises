package com.github.jactorrises.persistence.client.converter;

public interface TypeConverter<T, F> {
    T convertTo(F from);

    F convertFrom(T to);
}