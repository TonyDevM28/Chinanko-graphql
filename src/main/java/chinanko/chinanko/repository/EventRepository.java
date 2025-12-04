package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // 1. Obtener por Tipo y Pueblo
    Page<Event> findByTownIdAndTypeOfEventId(Integer townId, Integer typeOfEventId, Pageable pageable);

    // 2. Obtener por Dirección (CP)
    // Busca dentro del objeto relacionado 'addressEvent' el campo 'postalCode'
    List<Event> findDistinctByAddressEvent_PostalCode(String postalCode);

    // 3. Obtener por Calle (Búsqueda parcial)
    // Busca dentro del objeto relacionado 'addressEvent' el campo 'street'
    List<Event> findDistinctByAddressEvent_StreetContainingIgnoreCase(String street);

    // 4. Obtener por Pueblo (CORREGIDO)
    // Debe devolver Page<Event> y buscar directamente por la propiedad townId de la entidad Event
    Page<Event> findByTownId(Integer townId, Pageable pageable);

    // 5. Obtener por Estado y Pueblo
    Page<Event> findByTownIdAndStateOfEvent(Integer townId, Integer stateOfEvent, Pageable pageable);
}