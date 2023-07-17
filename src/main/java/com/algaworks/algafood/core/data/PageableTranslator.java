package com.algaworks.algafood.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

public class PageableTranslator {

    public static Pageable translator(Pageable page, Map<String,String> fieldsMapping){

        System.out.println(fieldsMapping);
        var orders = page.getSort().stream()
                .filter(order -> fieldsMapping.containsKey(order.getProperty()))
                .map(order -> new Sort.Order(order.getDirection(),
                          fieldsMapping.get(order.getProperty())))
                .collect(Collectors.toList());

        return PageRequest.of(page.getPageNumber(), page.getPageSize(),Sort.by(orders));

    }
}
