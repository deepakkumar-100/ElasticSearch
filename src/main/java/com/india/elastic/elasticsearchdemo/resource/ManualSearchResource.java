package com.india.elastic.elasticsearchdemo.resource;

import com.india.elastic.elasticsearchdemo.builder.SearchQueryBulder;
import com.india.elastic.elasticsearchdemo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.openmbean.CompositeDataSupport;
import java.util.List;

@RestController
@RequestMapping("/rest/manual/search")
public class ManualSearchResource {

    @Autowired
    private SearchQueryBulder searchQueryBuilder;

    @GetMapping(value="/{text}")
    public List<Users> getAllUser(@PathVariable final String text){
        return searchQueryBuilder.getAll(text);

    }
}
