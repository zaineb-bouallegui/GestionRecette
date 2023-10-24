package com.example.events1.Service;

import com.example.events1.Model.Event;

import java.util.List;

public interface IEventService {
    Event AddEvent(Event event) ;

    Event DeleteEvent(Integer id) ;

    List<Event> GetEvents() ;

    List<Event> GetEventbyName(String name);

    void UpdateEvent(Integer id, Event event) ;
}
