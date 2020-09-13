package ru.digital.rover.pwl.server.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.server.domain.model.InspectionResultEntity;
import ru.digital.rover.pwl.server.model.InspectionResultDto;

@Transactional
@Component
public class InspectionResultDtoMapper implements Converter<InspectionResultDto, InspectionResultEntity> {

    @Override
    public InspectionResultEntity convert(MappingContext<InspectionResultDto, InspectionResultEntity> context) {
        InspectionResultDto source = context.getSource();
        return new InspectionResultEntity(
                source.getNodeId(),
                source.getWireBreakPercent(),
                source.getIsolatorLostPercent(),
                source.getGvpShiftedPercent()
        );
    }
}
