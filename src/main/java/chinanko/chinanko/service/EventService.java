package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;

public interface EventService {

    EventResponse create(EventRequest request);

    EventResponse update(Integer id, EventRequest request);

    EventResponse getById(Integer id);

    // Filtros
    List<EventResponse> getByTownAndType(Integer townId, Integer typeId, int page, int pageSize);

    List<EventResponse> getByPostalCode(String postalCode);

    List<EventResponse> getByStreet(String street);

    List<EventResponse> getByTown(Integer townId, int page, int pageSize);

    List<EventResponse> getByTownAndState(Integer townId, Integer stateId, int page, int pageSize);
}
