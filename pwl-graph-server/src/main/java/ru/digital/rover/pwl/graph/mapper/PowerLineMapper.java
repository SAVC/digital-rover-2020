package ru.digital.rover.pwl.graph.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.graph.domain.PowerLineNode;
import ru.digital.rover.pwl.graph.model.PowerLineNodeDto;

import java.util.stream.Collectors;

/**
 * Convert domain model to representation
 */
@Transactional
@Component
public class PowerLineMapper implements Converter<PowerLineNode, PowerLineNodeDto> {

    @Override
    public PowerLineNodeDto convert(MappingContext<PowerLineNode, PowerLineNodeDto> context) {
        PowerLineNode source = context.getSource();
        return new PowerLineNodeDto(
                source.getId(),
                source.getName(),
                source.getLatitude(),
                source.getLongitude(),
                source.getAge(),
                source.getNodes().parallelStream().map(PowerLineNode::getId).collect(Collectors.toList()),
                source.getLineIds(),
                source.getVoltage(),
                source.getEnabled()
        );
    }

}
