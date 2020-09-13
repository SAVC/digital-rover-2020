package ru.digital.rover.pwl.server.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.server.domain.model.BypassEntity;
import ru.digital.rover.pwl.server.domain.model.InspectionResultEntity;
import ru.digital.rover.pwl.server.domain.repository.BypassRepository;
import ru.digital.rover.pwl.server.model.BypassDto;
import ru.digital.rover.pwl.server.model.BypassRequest;
import ru.digital.rover.pwl.server.model.InspectionResultDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bypass manipulation service
 */
@Transactional
@RequiredArgsConstructor
@Service
public class BypassService {

    private final BypassRepository bypassRepository;
    private final ModelMapper modelMapper;

    /**
     * Get all bypasses
     *
     * @return list of bypasses
     */
    public List<BypassDto> getAll() {
        return bypassRepository.findAll().parallelStream()
                .map(bypassEntity -> modelMapper.map(bypassEntity, BypassDto.class)).collect(Collectors.toList());
    }

    /**
     * Find bypass by id
     *
     * @param id - id
     * @return bypass
     */
    public BypassDto findById(Long id) {
        return modelMapper.map(bypassRepository.findById(id).orElseThrow(), BypassDto.class);
    }

    /**
     * Create bypass
     *
     * @param bypassRequest - request for bypass creation
     * @return saved bypass
     */
    public BypassDto create(BypassRequest bypassRequest) {
        BypassEntity bypass = modelMapper.map(bypassRequest, BypassEntity.class);
        return modelMapper.map(bypassRepository.saveAndFlush(bypass), BypassDto.class);
    }

    /**
     * Update existing bypass
     *
     * @param bypassRequest - request for bypass creation
     * @return saved bypass
     */
    public BypassDto update(Long id, BypassRequest bypassRequest) {
        return modelMapper.map(modelMapper, BypassDto.class);
    }

    /**
     * Delete bypass by id
     *
     * @param id - id
     */
    public void delete(Long id) {
        bypassRepository.deleteById(id);
    }

    /**
     * Add inspection result for bypass
     *
     * @param id                  - bypass id
     * @param inspectionResultDto - inspection result
     * @return saved bypass
     */
    public BypassDto addInspectionResult(Long id, InspectionResultDto inspectionResultDto) {
        BypassEntity bypassEntity = bypassRepository.findById(id).orElseThrow();
        bypassEntity.getResults().add(modelMapper.map(inspectionResultDto, InspectionResultEntity.class));
        return modelMapper.map(bypassRepository.save(bypassEntity), BypassDto.class);
    }

    /**
     * Remove inspection result for bypass
     *
     * @param id     - bypass id
     * @param nodeId - inspection node id
     * @return saved bypass
     */
    public BypassDto removeInspectionResult(Long id, Long nodeId) {
        BypassEntity bypassEntity = bypassRepository.findById(id).orElseThrow();
        bypassEntity.getResults().removeIf(inspectionResult -> inspectionResult.getNodeId().equals(nodeId));
        return modelMapper.map(bypassRepository.save(bypassEntity), BypassDto.class);
    }
}
