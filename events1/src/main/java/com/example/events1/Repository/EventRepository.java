package com.example.events1.Repository;


import com.example.events1.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findEventByTitle(String name) ;

}