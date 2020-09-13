package ru.digital.rover.pwl.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Power line representation
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PowerLineNodeDto {

    /**
     * Node id
     */
    private Long id;

    /**
     * Node documented name
     */
    private String name;

    /**
     * Latitude
     */
    private Double latitude;

    /**
     * Longitude
     */
    private Double longitude;

    /**
     * Power line Node age
     */
    private Integer age;

    /**
     * Connected nodes by ids
     */
    private List<Long> wiredNodeIds;

    /**
     * List of line ids for node
     */
    private List<String> lineIds;

    /**
     * Power line voltage
     */
    private Integer voltage;

    /**
     * Is node enabled
     */
    private Boolean enabled;
}
