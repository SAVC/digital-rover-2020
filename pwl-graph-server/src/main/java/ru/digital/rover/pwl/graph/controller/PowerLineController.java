package ru.digital.rover.pwl.graph.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.digital.rover.pwl.graph.model.PowerLineNodeDto;
import ru.digital.rover.pwl.graph.model.PowerLineNodeRequest;
import ru.digital.rover.pwl.graph.service.PowerLineService;

import java.util.Collections;
import java.util.List;

/**
 * OpenAPI for Power Lines
 */
@RequiredArgsConstructor
@RequestMapping("/power-line-node")
@RestController
public class PowerLineController {

    private final PowerLineService powerLineService;

    @GetMapping
    public List<PowerLineNodeDto> getAll() {
        return powerLineService.getAll();
    }

    @GetMapping("/{id}")
    public PowerLineNodeDto byId(@PathVariable Long id) {
        return powerLineService.getById(id);
    }

    @PostMapping
    public PowerLineNodeDto save(@RequestBody PowerLineNodeRequest powerLineNodeRequest) {
        return powerLineService.save(powerLineNodeRequest);
    }

    @PutMapping("/{id}")
    public PowerLineNodeDto update(@RequestBody PowerLineNodeRequest powerLineNodeRequest, @PathVariable Long id) {
        return powerLineService.update(id, powerLineNodeRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        powerLineService.delete(id);
    }

    @PostMapping("/{id}/connect/{destinationId}")
    public PowerLineNodeDto connect(@PathVariable("id") Long id, @PathVariable Long destinationId) {
        return powerLineService.connect(id, destinationId);
    }

    @PostMapping("/{id}/disconnect/{destinationId}")
    public PowerLineNodeDto disconnect(@PathVariable("id") Long id, @PathVariable Long destinationId) {
        return powerLineService.disconnect(id, destinationId);
    }

    @GetMapping("/lineId/{lineId}")
    public List<PowerLineNodeDto> getLineById(@PathVariable String lineId) {
        return powerLineService.getLineById(Collections.singletonList(lineId));
    }
}
