package ru.digital.rover.pwl.graph.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.graph.domain.PowerLineNode;
import ru.digital.rover.pwl.graph.model.PowerLineNodeRequest;

/**
 * Converter Power line request to domain model
 */
@Transactional
@Component
public class PowerLineDtoMapper implements Converter<PowerLineNodeRequest, PowerLineNode> {

    @Override
    public PowerLineNode convert(MappingContext<PowerLineNodeRequest, PowerLineNode> context) {
        PowerLineNodeRequest source = context.getSource();
        PowerLineNode destination = context.getDestination() == null ? new PowerLineNode() : context.getDestination();
        destination.setAge(source.getAge());
        destination.setEnabled(source.getEnabled());
        destination.setLatitude(source.getLatitude());
        destination.setLongitude(source.getLongitude());
        destination.setLineIds(source.getLineIds());
        destination.setName(source.getName());
        destination.setVoltage(source.getVoltage());
        return destination;
    }
}
