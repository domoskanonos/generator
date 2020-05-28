package com.dbr.generator.basic.converter;

public interface ConverterInterface<T, S> {
    S convert(T source);
}
