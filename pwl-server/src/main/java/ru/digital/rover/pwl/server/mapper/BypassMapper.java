package ru.digital.rover.pwl.server.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.server.domain.model.BypassEntity;
import ru.digital.rover.pwl.server.model.BypassDto;
import ru.digital.rover.pwl.server.model.InspectionResultDto;

import java.util.stream.Collectors;

@Transactional
@Component
public class BypassMapper implements Converter<BypassEntity, BypassDto> {

    @Override
    public BypassDto convert(MappingContext<BypassEntity, BypassDto> context) {
        BypassEntity source = context.getSource();
        return new BypassDto(
                source.getId(),
                source.getDescription(),
                source.getBypassTime(),
                source.getCreateDate(),
                source.getUpdateDate(),
                source.getResults().parallelStream().map(inspectionResult -> new InspectionResultDto(
                        inspectionResult.getNodeId(),
                        inspectionResult.getWireBreakPercent(),
                        inspectionResult.getIsolatorLostPercent(),
                        inspectionResult.getGvpShiftedPercent(),
                        inspectionResult.score()
                )).collect(Collectors.toList()),
                source.hasDefects(),
                source.getIsScheduled()
        );
    }

}
