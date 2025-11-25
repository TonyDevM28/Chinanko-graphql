package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;
import chinanko.chinanko.dto.TownRequest;
import chinanko.chinanko.dto.TownResponse;
import chinanko.chinanko.model.State;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.repository.StateRepository; // Asumo que tienes este repo
import chinanko.chinanko.exception.EntityNotFoundException; // Necesitarás esto
import lombok.RequiredArgsConstructor; // Añade esto

@Component
@RequiredArgsConstructor // Añade esto
public class TownMapper {

    // 1. Inyecta el repositorio de State
    private final StateRepository stateRepository;

    // 2. Quita 'static'
    public Town toEntity(TownRequest r) {
        if (r == null)
            return null;
        
        // 3. Busca la entidad 'State' completa
        State state = stateRepository.findById(r.getStateId())
                .orElseThrow(() -> new EntityNotFoundException("State not found with id: " + r.getStateId()));

        return Town.builder()
                .nameTown(r.getNameTown())
                .latitude(r.getLatitude())
                .longitude(r.getLongitude())
                .state(state) // 4. Asigna el objeto 'State' completo
                .build();
    }

    // 5. Quita 'static' de toResponse (no necesita cambios internos)
    public static TownResponse toResponse(Town t) {
        if (t == null)
            return null;

        return TownResponse.builder()
                .idTown(t.getIdTown())
                .nameTown(t.getNameTown())
                .latitude(t.getLatitude())
                .longitude(t.getLongitude())
                // Esto ahora funciona porque t.getState() es la entidad completa
                .stateName(t.getState().getNameState()) 
                .build();
    }

    // 6. Quita 'static' y arregla copyToEntity
    public void copyToEntity(Town t, TownRequest r) {
        if (r == null || t == null) return;

        // 7. Busca la entidad 'State' completa
        State state = stateRepository.findById(r.getStateId())
                .orElseThrow(() -> new EntityNotFoundException("State not found with id: " + r.getStateId()));

        t.setNameTown(r.getNameTown());
        t.setLatitude(r.getLatitude());
        t.setLongitude(r.getLongitude());
        t.setState(state); // 8. Asigna el objeto 'State' completo
    }
}