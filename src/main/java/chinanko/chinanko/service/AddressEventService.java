package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.AddressEventRequest;
import chinanko.chinanko.dto.AddressEventResponse;

public interface AddressEventService {

    AddressEventResponse create(AddressEventRequest request);

    AddressEventResponse update(Integer id, AddressEventRequest request);

    AddressEventResponse getById(Integer id);

    void delete(Integer id);

    // Filtros
    List<AddressEventResponse> getByPostalCode(String postalCode, int page, int pageSize);

    List<AddressEventResponse> getByEvent(Integer eventId, int page, int pageSize);

    List<AddressEventResponse> getByTown(Integer townId, int page, int pageSize);
}
