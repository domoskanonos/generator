package com.dbr.generator.basic.converter;

import java.util.Collection;
import java.util.List;

public interface ConverterInterface<T, S> {

    S convert(T source);

    List<S> convert(Collection<T> source);

}
