package ru.digital.rover.pwl.server.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.server.domain.model.BypassEntity;
import ru.digital.rover.pwl.server.model.BypassRequest;

@Transactional
@Component
public class BypassDtoMapper implements Converter<BypassRequest, BypassEntity> {

    @Override
    public BypassEntity convert(MappingContext<BypassRequest, BypassEntity> context) {
        BypassRequest source = context.getSource();
        BypassEntity destination = context.getDestination() == null ? new BypassEntity() : context.getDestination();
        destination.setBypassTime(source.getBypassTime());
        destination.setDescription(source.getDescription());
        destination.setIsScheduled(source.getIsScheduled());
        return destination;
    }
}
