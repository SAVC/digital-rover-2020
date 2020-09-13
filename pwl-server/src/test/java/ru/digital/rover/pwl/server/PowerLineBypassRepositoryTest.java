package ru.digital.rover.pwl.server;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.digital.rover.pwl.server.domain.model.BypassEntity;
import ru.digital.rover.pwl.server.domain.repository.BypassRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author pdtyreus
 */

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PowerLineBypassRepositoryTest {

    @Autowired
    private BypassRepository bypassRepository;

    @Test
    void dbTest() {
        bypassRepository.save(new BypassEntity("123", LocalDateTime.now(), Collections.emptyList(), true));
        List<BypassEntity> all = bypassRepository.findAll();
        Assert.assertEquals(1, all.size());
    }

}
