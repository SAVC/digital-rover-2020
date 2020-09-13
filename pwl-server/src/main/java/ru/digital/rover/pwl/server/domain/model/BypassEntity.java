package ru.digital.rover.pwl.server.domain.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "bypass_records")
public class BypassEntity extends BaseEntity<Long> {

    private final float BOUNDARY = 0.6f;

    public BypassEntity(String description, LocalDateTime bypassTime, List<InspectionResultEntity> results, Boolean isScheduled) {
        this.description = description;
        this.bypassTime = bypassTime;
        this.results = results;
        this.isScheduled = isScheduled;
    }

    private String description;

    private LocalDateTime bypassTime;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InspectionResultEntity> results = new ArrayList<>();

    private Boolean isScheduled;

    public Boolean hasDefects() {
        boolean hasDefect = false;
        for (InspectionResultEntity result : results) {
            if (result.getGvpShiftedPercent() > BOUNDARY || result.getWireBreakPercent() > BOUNDARY || result.getIsolatorLostPercent() > BOUNDARY) {
                hasDefect = true;
                break;
            }
        }
        return hasDefect;
    }
}
