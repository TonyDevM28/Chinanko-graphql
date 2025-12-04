package chinanko.chinanko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.AddressEvent;

@Repository
public interface AddressEventRepository extends JpaRepository<AddressEvent, Integer> {

    // Obtener por Código Postal
    Page<AddressEvent> findByPostalCode(String postalCode, Pageable pageable);

    // Obtener por Evento
    Page<AddressEvent> findByEvent_IdEvent(Integer eventId, Pageable pageable);

    // Obtener por Pueblo (Navegando Address -> Event -> Town)
    Page<AddressEvent> findByEvent_TownId(Integer townId, Pageable pageable);
}
