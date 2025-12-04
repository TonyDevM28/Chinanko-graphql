package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;
import chinanko.chinanko.mapper.EventMapper;
import chinanko.chinanko.model.Event;
import chinanko.chinanko.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Override
    @Transactional
    public EventResponse create(EventRequest request) {
        Event entity = EventMapper.toEntity(request);
        Event saved = repository.save(entity);
        return EventMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public EventResponse update(Integer id, EventRequest request) {
        Event existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
        
        EventMapper.copyToEntity(existing, request);
        Event saved = repository.save(existing);
        return EventMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getById(Integer id) {
        Event entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
        return EventMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getByTownAndType(Integer townId, Integer typeId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        // Llamada actualizada al repositorio
        Page<Event> result = repository.findByTownIdAndTypeOfEventId(townId, typeId, pageable);
        return result.stream().map(EventMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getByPostalCode(String postalCode) {
        // Llamada actualizada a addressEvents (plural)
        return repository.findDistinctByAddressEvent_PostalCode(postalCode).stream()
                .map(EventMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getByStreet(String street) {
        return repository.findDistinctByAddressEvent_StreetContainingIgnoreCase(street).stream()
                .map(EventMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getByTown(Integer townId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        // Llamada actualizada
        Page<Event> result = repository.findByTownId(townId, pageable);
        return result.stream().map(EventMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getByTownAndState(Integer townId, Integer stateId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        // Llamada actualizada
        Page<Event> result = repository.findByTownIdAndStateOfEvent(townId, stateId, pageable);
        return result.stream().map(EventMapper::toResponse).collect(Collectors.toList());
    }
}