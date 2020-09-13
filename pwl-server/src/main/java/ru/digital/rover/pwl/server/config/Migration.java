package ru.digital.rover.pwl.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.digital.rover.pwl.server.domain.model.BypassEntity;
import ru.digital.rover.pwl.server.domain.model.InspectionResultEntity;
import ru.digital.rover.pwl.server.domain.repository.BypassRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@RequiredArgsConstructor
//@Component
public class Migration {

    private final BypassRepository bypassRepository;

    @PostConstruct
    public void init() {
        bypassRepository.save(new BypassEntity(
                "First test bypass, just planed and without results",
                LocalDateTime.now(),
                Collections.emptyList(),
                true
        ));

        bypassRepository.save(new BypassEntity(
                "Alert test bypass!!!",
                LocalDateTime.now(),
                Collections.emptyList(),
                false
        ));

        bypassRepository.save(new BypassEntity(
                "Test bypass with results",
                LocalDateTime.now(),
                Arrays.asList(
                        new InspectionResultEntity(1L, 0.2f, 0.4f, 0.35f),
                        new InspectionResultEntity(2L, 0.12f, 0.43f, 0.6f),
                        new InspectionResultEntity(3L, 0.01f, 0.42f, 0.43f),
                        new InspectionResultEntity(4L, 0.1f, 0.41f, 0.5f)
                ),
                true
        ));

        bypassRepository.save(new BypassEntity(
                "Test bypass with results",
                LocalDateTime.now(),
                Arrays.asList(
                        new InspectionResultEntity(5L, 0.24444f, 0.624565f, 0.35f),
                        new InspectionResultEntity(6L, 0.14234324f, 0.256f, 0.898f),
                        new InspectionResultEntity(7L, 0.5634534f, 0.8979f, 0.43f),
                        new InspectionResultEntity(8L, 0.6256f, 0.8989f, 0.689896f)
                ),
                true
        ));
    }
}
