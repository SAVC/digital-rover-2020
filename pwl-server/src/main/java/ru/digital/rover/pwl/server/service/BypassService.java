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

@Transactional
@RequiredArgsConstructor
@Service
public class BypassService {

    private final BypassRepository bypassRepository;
    private final ModelMapper modelMapper;

    public List<BypassDto> getAll() {
        return bypassRepository.findAll().parallelStream()
                .map(bypassEntity -> modelMapper.map(bypassEntity, BypassDto.class)).collect(Collectors.toList());
    }

    public BypassDto findById(Long id) {
        return modelMapper.map(bypassRepository.findById(id).orElseThrow(), BypassDto.class);
    }

    public BypassDto create(BypassRequest bypassRequest) {
        BypassEntity bypass = modelMapper.map(bypassRequest, BypassEntity.class);
        return modelMapper.map(bypassRepository.saveAndFlush(bypass), BypassDto.class);
    }

    public BypassDto update(Long id, BypassRequest bypassRequest) {
        return modelMapper.map(modelMapper, BypassDto.class);
    }

    public void delete(Long id) {
        bypassRepository.deleteById(id);
    }

    public BypassDto addInspectionResult(Long id, InspectionResultDto inspectionResultDto) {
        BypassEntity bypassEntity = bypassRepository.findById(id).orElseThrow();
        bypassEntity.getResults().add(modelMapper.map(inspectionResultDto, InspectionResultEntity.class));
        return modelMapper.map(bypassRepository.save(bypassEntity), BypassDto.class);
    }

    public BypassDto removeInspectionResult(Long id, Long nodeId) {
        BypassEntity bypassEntity = bypassRepository.findById(id).orElseThrow();
        bypassEntity.getResults().removeIf(inspectionResult -> inspectionResult.getNodeId().equals(nodeId));
        return modelMapper.map(bypassRepository.save(bypassEntity), BypassDto.class);
    }
}
