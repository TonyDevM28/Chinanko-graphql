package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;
import chinanko.chinanko.service.EventService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EventGraphQLController {

    private final EventService eventService;

    // --- QUERIES ---

    @QueryMapping
    public EventResponse eventById(@Argument Integer id) {
        return eventService.getById(id);
    }

    @QueryMapping
    public List<EventResponse> eventsByTown(@Argument Integer townId, 
                                            @Argument Integer page, 
                                            @Argument Integer size) {
        // Manejo de valores por defecto si graphql manda nulos
        int pageNum = (page != null) ? page : 0;
        int pageSize = (size != null) ? size : 10;
        return eventService.getByTown(townId, pageNum, pageSize);
    }

    @QueryMapping
    public List<EventResponse> eventsByPostalCode(@Argument String postalCode) {
        return eventService.getByPostalCode(postalCode);
    }

    // --- MUTATIONS ---

    @MutationMapping
    public EventResponse createEvent(@Argument("input") EventRequest input) {
        // Spring Boot mapeará el objeto 'EventInput' de GraphQL a tu clase 'EventRequest'
        return eventService.create(input);
    }

    @MutationMapping
    public EventResponse updateEvent(@Argument Integer id, @Argument("input") EventRequest input) {
        // Al enviar la lista de 'addresses' dentro de input, 
        // tu Mapper ejecutará la lógica de borrar las viejas e insertar las nuevas.
        return eventService.update(id, input);
    }
}