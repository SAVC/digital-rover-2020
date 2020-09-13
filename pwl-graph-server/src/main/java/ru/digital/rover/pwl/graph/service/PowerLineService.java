package ru.digital.rover.pwl.graph.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.graph.domain.PowerLineNode;
import ru.digital.rover.pwl.graph.model.PowerLineNodeDto;
import ru.digital.rover.pwl.graph.model.PowerLineNodeRequest;
import ru.digital.rover.pwl.graph.repositories.PowerLineRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Graph manipulation service
 */
@Transactional
@RequiredArgsConstructor
@Service
public class PowerLineService {

    private final PowerLineRepository powerLineRepository;
    private final ModelMapper modelMapper;

    /**
     * Get all nodes
     *
     * @return list of {@link PowerLineNode}
     */
    public List<PowerLineNodeDto> getAll() {
        return powerLineRepository.findAll().parallelStream()
                .map(powerLineNode -> modelMapper.map(powerLineNode, PowerLineNodeDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Power line node by id
     *
     * @param id - node id
     * @return power line node
     */
    public PowerLineNodeDto getById(Long id) {
        return modelMapper.map(powerLineRepository.findById(id).orElseThrow(), PowerLineNodeDto.class);
    }

    /**
     * Save new power line node
     *
     * @param powerLineNodeRequest - request for node creation
     * @return saved power line node
     */
    public PowerLineNodeDto save(PowerLineNodeRequest powerLineNodeRequest) {
        PowerLineNode node = modelMapper.map(powerLineNodeRequest, PowerLineNode.class);
        return modelMapper.map(powerLineRepository.save(node), PowerLineNodeDto.class);
    }

    /**
     * Update existing power line node
     *
     * @param id                   - node id
     * @param powerLineNodeRequest - request for node update
     * @return saved power line node
     */
    public PowerLineNodeDto update(Long id, PowerLineNodeRequest powerLineNodeRequest) {
        PowerLineNode powerLineNode = powerLineRepository.findById(id).orElseThrow();
        modelMapper.map(powerLineNodeRequest, powerLineNode);
        return modelMapper.map(powerLineRepository.save(powerLineNode), PowerLineNodeDto.class);
    }

    /**
     * Delete power line node by id
     *
     * @param id - node id
     */
    public void delete(Long id) {
        powerLineRepository.deleteById(id);
    }

    /**
     * Connect two nodes by ids. Connection is undirected by business logic
     *
     * @param id            - source node id
     * @param destinationId - dest node id
     * @return saved source node
     */
    public PowerLineNodeDto connect(Long id, Long destinationId) {
        PowerLineNode node = powerLineRepository.findById(id).orElseThrow();
        PowerLineNode destination = powerLineRepository.findById(destinationId).orElseThrow();
        node.getNodes().add(destination);
        return modelMapper.map(powerLineRepository.save(node), PowerLineNodeDto.class);
    }

    /**
     * Disconnect two nodes by ids. Connection is undirected by business logic
     *
     * @param id            - source node id
     * @param destinationId - dest node id
     * @return saved source node
     */
    public PowerLineNodeDto disconnect(Long id, Long destinationId) {
        PowerLineNode node = powerLineRepository.findById(id).orElseThrow();
        node.getNodes().removeIf(n -> n.getId().equals(destinationId));
        return modelMapper.map(powerLineRepository.save(node), PowerLineNodeDto.class);
    }

    /**
     * Get all nodes by Power Line path ids
     *
     * @param lineIds - line id
     * @return list of {@link PowerLineNode}
     */
    public List<PowerLineNodeDto> getLineById(List<String> lineIds) {
        return powerLineRepository.findAllByLineIdsContains(lineIds).parallelStream()
                .map(powerLineNode -> modelMapper.map(powerLineNode, PowerLineNodeDto.class))
                .collect(Collectors.toList());
    }

}
