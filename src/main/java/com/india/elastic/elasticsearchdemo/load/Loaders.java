package com.india.elastic.elasticsearchdemo.load;


import com.india.elastic.elasticsearchdemo.jparepository.UsersJpaRepository;
import com.india.elastic.elasticsearchdemo.model.Users;
import com.india.elastic.elasticsearchdemo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersJpaRepository userJpaRepository;

    @PostConstruct
    @Transactional
    public void loadAll(){
        operations.putMapping(Users.class);
        System.out.println("Loading Data");
        List<Users> data = getData();
        userJpaRepository.save(data); //saves to H2 DB

        List<Users> usersList = userJpaRepository.findAll(); //Get from H2 DB
        usersRepository.save(usersList); //loads into Elastic
        System.out.printf("Loading Completed");
    }

    private List<Users> getData() {
        List<Users> userses = new ArrayList<>();
        userses.add(new Users("Deepak",123L,"Accounting",122000L));
        userses.add(new Users("Bharat",1234L,"Finance",152000L));
        userses.add(new Users("Richa",1235L,"IT",125000L));
        userses.add(new Users("Ajay",1236L,"Accounting",2122000L));
        return userses;

    }
}
