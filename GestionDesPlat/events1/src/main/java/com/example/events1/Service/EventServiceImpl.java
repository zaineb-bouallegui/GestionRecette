package com.example.events1.Service;

import com.example.events1.Model.Event;
import com.example.events1.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventServiceImpl implements IEventService {

@Autowired
     EventRepository eventRepository ;

    @Override
    public Event AddEvent(Event event) {

        return  eventRepository.save(event) ;
    }

    @Override
    public Event DeleteEvent(Integer id) {
        eventRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Event> GetEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> GetEventbyName(String name) {

        return eventRepository.findEventByTitle(name) ;
    }

    @Override
    public void UpdateEvent(Integer id, Event event) {
        if (eventRepository.existsById(id))
        {
            event.setId(id);
            eventRepository.save(event) ;

        }
        else System.out.println("Event Not Found");
    }


}
