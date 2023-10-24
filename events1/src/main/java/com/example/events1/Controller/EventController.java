package com.example.events1.Controller;


import com.example.events1.Model.Event;
import com.example.events1.Service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/Event")
public class EventController {

    @Autowired
    IEventService eventService ;


    @PostMapping("/ajouter")
    public Event AddEvent (@RequestBody Event event) {
        System.out.println("HHHHHHHHHH");

        return eventService.AddEvent(event);
    }

    @DeleteMapping("/delete/{id}")
        public Event DeleteEvent(@PathVariable Integer id)
    {
        return eventService.DeleteEvent(id);
    }

    @GetMapping("/GetAll")
    public List<Event> GetEvents ()
    {
        List<Event> E= eventService.GetEvents();
        System.out.println(E);
        return E ;
    }

    @GetMapping("/GetbyName/{name}")
    public List<Event> GetEventbyName (@PathVariable String name)
    {
        List<Event> Ev= eventService.GetEventbyName(name) ;
        System.out.println(Ev);
        return Ev ;
    }

    @PutMapping("/update/{id}")
    public void UpdateEvent(@PathVariable Integer id,@RequestBody Event event)
    {
         eventService.UpdateEvent(id,event);
         System.out.println("Updated");
    }



}
