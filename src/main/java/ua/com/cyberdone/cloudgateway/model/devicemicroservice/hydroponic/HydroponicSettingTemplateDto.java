package ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HydroponicSettingTemplateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 370000323L;

    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Double setupPhValue;
    private Long setupTdsValue;
    private Double regulateErrorPh;
    private Double regulateErrorFertilizer;
    private Double mlPerMillisecond;
    private Double phUpDoseMl;
    private Double phDownDoseMl;
    private Double fertilizerDoseMl;
    private Long restartCounter;
    private Long recheckPhDispensersAfterSeconds;
    private Long recheckTdsDispensersAfterSeconds;
    private Boolean calculateStartSolutionAsFertilizer;
    private Boolean calculatePhRegulationAsFertilizer;
    private Boolean dispensersEnable;
    private Boolean sensorsEnable;
    private Boolean autotime;
    private String timeZone;
    private String wifiSsid;
    private String wifiPass;
    private LocalDateTime microcontrollerTime;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}
