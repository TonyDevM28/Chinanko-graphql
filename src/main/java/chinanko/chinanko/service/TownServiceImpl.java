package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.TownRequest;
import chinanko.chinanko.dto.TownResponse;
import chinanko.chinanko.exception.DuplicateEntityException;
import chinanko.chinanko.exception.EntityNotFoundException;
import chinanko.chinanko.mapper.TownMapper;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.repository.TownRepository;
import lombok.RequiredArgsConstructor;

@Service // Marks this class as a Spring Service
@RequiredArgsConstructor // Lombok: Creates constructor for final fields
public class TownServiceImpl implements TownService {

    // Injects the repository for database operations
    private final TownRepository repository;
    private final TownMapper mapper;
    @Override
    public TownResponse create(TownRequest request) {
        Town existingTown = repository.getTownByName(request.getNameTown());
        if (existingTown != null) {
            throw new DuplicateEntityException("Town with name '" + request.getNameTown() + "' already exists");
        }
        
        // 3. Llama al método NO estático del mapper
        Town created = repository.save(mapper.toEntity(request));
        
        // Llama al método estático (o no estático, si lo cambiaste)
        return TownMapper.toResponse(created); 
    }

    @Override
    public List<TownResponse> findAll(int page, int pageSize) {
        // Find all towns, map each to a response DTO, and collect into a list
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Town> towns = repository.findAll(pageReq);  
        return towns.getContent().stream().map(TownMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public TownResponse getById(Integer id) {
        // Find the town by its ID, or throw an exception if not found
        Town town = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Town not found with id: " + id));
        // Map the found entity to a response DTO
        return TownMapper.toResponse(town);
    }

    @Override
    public TownResponse update(Integer id, TownRequest request) {
        Town existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Town not found with id: " + id));

        Town townWithSameName = repository.getTownByName(request.getNameTown());
        
        if (townWithSameName != null && !townWithSameName.getIdTown().equals(id)) {
            throw new DuplicateEntityException("Another town with name '" + request.getNameTown() + "' already exists");
        }

        // 4. Llama al método NO estático del mapper
        mapper.copyToEntity(existing, request);
        
        Town saved = repository.save(existing);
        
        return TownMapper.toResponse(saved);
    }

    @Override
    public TownResponse getTownByName(String name) {
        // Find a town by its exact name
        Town town = repository.getTownByName(name);
        if (town == null) {
            // If not found, throw an exception
            throw new EntityNotFoundException("Town not found with name: " + name);
        }
        // Map the found entity to a response DTO
        return TownMapper.toResponse(town);
    }

    @Override
    public List<TownResponse> getTownsByState(String stateName) {
        // Find all towns associated with a specific state name
        List<Town> towns = repository.getTownsByState(stateName);
        if (towns.isEmpty()) {
            // If no towns are found for that state, throw an exception
            throw new EntityNotFoundException("No towns found for state: " + stateName);
        }
        // Map the list of entities to a list of response DTOs
        return towns.stream().map(TownMapper::toResponse).collect(Collectors.toList());
    }
}