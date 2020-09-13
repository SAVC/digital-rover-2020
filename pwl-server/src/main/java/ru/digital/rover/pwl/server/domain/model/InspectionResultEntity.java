package ru.digital.rover.pwl.server.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "inspection_results")
public class InspectionResultEntity extends BaseEntity<Long> {

    private Long nodeId;

    private Float wireBreakPercent;

    private Float isolatorLostPercent;

    private Float gvpShiftedPercent;


    public Float score() {
        return (wireBreakPercent + isolatorLostPercent + gvpShiftedPercent) / 3;
    }
}
