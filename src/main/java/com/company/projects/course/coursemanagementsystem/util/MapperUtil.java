package com.company.projects.course.coursemanagementsystem.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MapperUtil {

    @Nullable
    public <T, U> U map(@Nullable T object, @NonNull Function<T, U> mapper) {
        if (object == null) return null;
        return mapper.apply(object);
    }

    @NonNull
    public <T, U> List<U> mapCollection(@Nullable Collection<T> objects, @NonNull Function<T, U> mapper) {
        if (objects == null) return Collections.emptyList();
        return objects.stream().map(mapper).toList();
    }
}