package com.india.elastic.elasticsearchdemo.resource;

import com.india.elastic.elasticsearchdemo.model.Users;
import com.india.elastic.elasticsearchdemo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @GetMapping(value = "/name/{text}")
    public List<Users> searchName(@PathVariable final String text){
       return usersRepository.findByName(text);
    }

    @GetMapping(value = "/salary/{salary}")
    public List<Users> searchBySalary(@PathVariable final Long salary){
        return usersRepository.findBySalary(salary);
    }

    @GetMapping(value = "/all")
    public List<Users> searchAll() {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }

    @GetMapping(value="/delete")
    public boolean delete(){
        return elasticsearchTemplate.deleteIndex(Users.class);
    }
}
