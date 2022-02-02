package ua.com.cyberdone.cloudgateway.model.devicemicroservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularScheduleDto {
    private Long id;
    private String uuid;
    private String name;
    private String description;
    private String key;
    private String topic;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;
    private LocalTime time;
    private String value;
    private ValueType valueType;
}
