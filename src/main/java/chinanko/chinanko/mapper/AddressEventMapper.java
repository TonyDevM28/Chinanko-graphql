package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;
import chinanko.chinanko.dto.AddressEventRequest;
import chinanko.chinanko.dto.AddressEventResponse;
import chinanko.chinanko.model.AddressEvent;
import chinanko.chinanko.model.Event;

@Component
public class AddressEventMapper {

    public static AddressEvent toEntity(AddressEventRequest r) {
        if (r == null) return null;

        AddressEvent.AddressEventBuilder builder = AddressEvent.builder()
                .street(r.getStreet())
                .exteriorNumber(r.getExteriorNumber())
                .interiorNumber(r.getInteriorNumber())
                .neighborhood(r.getNeighborhood())
                .postalCode(r.getPostalCode())
                .latitude(r.getLatitude())
                .longitude(r.getLongitude());

        // CORRECCIÓN: Solo asignamos el evento si el ID viene en el request.
        // Si es null (creación en cascada), EventMapper se encargará de esto.
        if (r.getEventId() != null) {
            builder.event(Event.builder().idEvent(r.getEventId()).build());
        }

        return builder.build();
    }

    public static AddressEventResponse toResponse(AddressEvent e) {
        if (e == null) return null;

        return AddressEventResponse.builder()
                .idAddressEvent(e.getIdAddressEvent())
                .street(e.getStreet())
                .exteriorNumber(e.getExteriorNumber())
                .interiorNumber(e.getInteriorNumber())
                .neighborhood(e.getNeighborhood())
                .postalCode(e.getPostalCode())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .eventName(e.getEvent() != null ? e.getEvent().getNameEvent() : "Unknown")
                .build();
    }
    
    // ... copyToEntity (se mantiene igual)
}