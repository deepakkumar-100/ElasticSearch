package com.india.elastic.elasticsearchdemo.builder;

import com.india.elastic.elasticsearchdemo.model.Users;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchQueryBulder {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public List<Users> getAll(String text){

        QueryBuilder query = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(text)
                                .lenient(true)
                                .field("name")
                                .field("teamName")
                )// Query to search a text in name and teamName field
                .should(
                        QueryBuilders.queryStringQuery("*"+text+"*")
                        .lenient(true)
                        .field("name")
                        .field("teamName")
                ); // Query to search a wildcard text in name and teamName field


        NativeSearchQuery build =
                new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<Users> userses = elasticsearchTemplate.queryForList(build,Users.class);

        return userses;
    }
}
