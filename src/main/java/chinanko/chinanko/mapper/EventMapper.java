package chinanko.chinanko.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;
import chinanko.chinanko.model.AddressEvent;
import chinanko.chinanko.model.Event;

@Component
public class EventMapper {

    public static Event toEntity(EventRequest r) {
        if (r == null) return null;

        Event event = Event.builder()
                .nameEvent(r.getNameEvent())
                .description(r.getDescription())
                .timeBegin(r.getTimeBegin())
                .timeEnd(r.getTimeEnd())
                .price(r.getPrice())
                // Coincidiendo con tus variables del Modelo:
                .townId(r.getTownId())
                .typeOfEventId(r.getTypeOfEventId())
                .stateOfEvent(r.getStateOfEventId()) // <--- AQUÍ ESTABA EL ERROR
                .build();

        // Mapeo en Cascada: Direcciones
        if (r.getAddresses() != null && !r.getAddresses().isEmpty()) {
            List<AddressEvent> addresses = r.getAddresses().stream()
                    .map(AddressEventMapper::toEntity)
                    .collect(Collectors.toList());
            
            addresses.forEach(addr -> addr.setEvent(event));
            // Usamos el nombre en singular como está en tu modelo
            event.setAddressEvent(addresses); 
        }

        return event;
    }

    public static EventResponse toResponse(Event e) {
        if (e == null) return null;

        return EventResponse.builder()
                .idEvent(e.getIdEvent())
                .nameEvent(e.getNameEvent())
                .description(e.getDescription())
                .timeBegin(e.getTimeBegin())
                .timeEnd(e.getTimeEnd())
                .price(e.getPrice())
                .townId(e.getTownId())
                .typeOfEventId(e.getTypeOfEventId())
                .stateOfEventId(e.getStateOfEvent()) // <--- AQUÍ LEEMOS TU VARIABLE
                .addresses(e.getAddressEvent() != null 
                        ? e.getAddressEvent().stream().map(AddressEventMapper::toResponse).collect(Collectors.toList()) 
                        : null)
                .build();
    }

    public static void copyToEntity(Event e, EventRequest r) {
        if (e == null || r == null) return;

        e.setNameEvent(r.getNameEvent());
        e.setDescription(r.getDescription());
        e.setTimeBegin(r.getTimeBegin());
        e.setTimeEnd(r.getTimeEnd());
        e.setPrice(r.getPrice());
        e.setTownId(r.getTownId());
        e.setTypeOfEventId(r.getTypeOfEventId());
        e.setStateOfEvent(r.getStateOfEventId()); // <--- CORREGIDO

        // Actualización en Cascada: Direcciones
        if (r.getAddresses() != null) {
            if (e.getAddressEvent() != null) {
                e.getAddressEvent().clear();
            } else {
                e.setAddressEvent(new ArrayList<>());
            }

            List<AddressEvent> newAddresses = r.getAddresses().stream()
                    .map(AddressEventMapper::toEntity)
                    .collect(Collectors.toList());

            newAddresses.forEach(addr -> addr.setEvent(e));
            e.getAddressEvent().addAll(newAddresses);
        }
    }
}