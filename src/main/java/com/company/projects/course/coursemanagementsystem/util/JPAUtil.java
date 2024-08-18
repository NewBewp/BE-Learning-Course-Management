package com.company.projects.course.coursemanagementsystem.util;

import org.springframework.data.domain.Sort;

import java.util.Arrays;

public class JPAUtil {
    public static Sort getSortRequestParam(String sortBy) {
        String[] sortParams = sortBy.split(",");
        return Sort.by(
                Arrays.stream(sortParams).map(param -> {
                    String[] sortOrder = param.split(":");
                    if(sortOrder.length == 2 && sortOrder[1].equalsIgnoreCase("desc")) {
                        return Sort.Order.desc(sortOrder[0]);
                    } else {
                        return Sort.Order.asc(sortOrder[0]);
                    }
                }).toList()
        );
    }
}
