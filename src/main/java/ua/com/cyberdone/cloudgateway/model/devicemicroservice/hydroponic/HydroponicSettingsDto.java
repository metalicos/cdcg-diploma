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
public class HydroponicSettingsDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 37209843L;

    private String uuid;
    private Double mlPerMillisecond;
    private Double regulateErrorPh;
    private Double regulateErrorFertilizer;
    private Double phUpDoseMl;
    private Double phDownDoseMl;
    private Double fertilizerDoseMl;
    private Double setupPhValue;
    private Long setupTdsValue;
    private Long restartCounter;
    private String wifiSSID;
    private String wifiPASS;
    private Long recheckPhDispensersAfterSeconds;
    private Long recheckTdsDispensersAfterSeconds;
    private Boolean calculateStartSolutionAsFertilizer;
    private Boolean calculatePhRegulationAsFertilizer;
    private Boolean dispensersEnable;
    private Boolean sensorsEnable;
    private Boolean autotime;
    private String timeZone;
    private LocalDateTime microcontrollerTime;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}
