package ua.com.cyberdone.cloudgateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceMetadataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceType;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.RegularScheduleDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.RegularScheduleUpdateDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicCalibrationDataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicDataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicTimeDto;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient("cyber-done-device-microservice")
public interface DeviceMicroserviceFeignClient {

    //          METADATA CONTROLLER CALLS
    @GetMapping("/metadata")
    ResponseEntity<DeviceMetadataDto> getMetadataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                        @RequestParam String uuid);

    @GetMapping("/metadata/list")
    ResponseEntity<List<DeviceMetadataDto>> getMetadataByUser(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam Long userId);

    @PatchMapping("/metadata")
    ResponseEntity<String> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid,
                                          @RequestParam String name,
                                          @RequestParam String description);

    @PostMapping("/metadata")
    ResponseEntity<DeviceMetadataDto> createMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestBody DeviceMetadataDto metadataDto);

    @DeleteMapping("/metadata")
    ResponseEntity<String> deleteMetadata(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid);

    @GetMapping("/metadata/device-types")
    ResponseEntity<DeviceType[]> getDeviceTypesList(@RequestHeader(AUTHORIZATION) String token);

    @PutMapping("/metadata/unlink")
    ResponseEntity<String> unlinkMetadataFromUser(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid);

    @PutMapping("/metadata/link")
    ResponseEntity<String> linkMetadataToUser(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam String uuid, @RequestParam Long userId);

    //          SCHEDULE CONTROLLER CALLS


    @PutMapping("/schedules")
    ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(@RequestHeader(AUTHORIZATION) String token,
                                                               @RequestParam String uuid,
                                                               @RequestParam String key);

    @PutMapping("/schedules")
    ResponseEntity<RegularScheduleDto> createSchedule(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestBody RegularScheduleDto schedule);

    @PatchMapping("/schedules")
    ResponseEntity<String> updateScheduleMetaInfo(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestBody RegularScheduleUpdateDto schedule);

    @DeleteMapping("/schedules/{id}")
    ResponseEntity<String> deleteScheduleById(@RequestHeader(AUTHORIZATION) String token,
                                              @PathVariable("id") Long id);

    //        HYDROPONIC DATA CALLS

    @GetMapping("/hydroponic/data/last")
    ResponseEntity<List<HydroponicDataDto>> getLastDataInDeviceWithUUID(@RequestHeader(AUTHORIZATION) String token,
                                                                        @RequestParam String uuid,
                                                                        @RequestParam Integer page,
                                                                        @RequestParam Integer limit);

    @DeleteMapping("/hydroponic/data")
    ResponseEntity<Void> deleteAllDataInDeviceWithUUID(@RequestHeader(AUTHORIZATION) String token,
                                                       @RequestParam Long id);

    //        HYDROPONIC CALIBRATION CALLS


    @GetMapping("/hydroponic/calibration-data/last")
    ResponseEntity<List<HydroponicCalibrationDataDto>> getLastCalibrationDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                                              @RequestParam String uuid,
                                                                                              @RequestParam Integer page,
                                                                                              @RequestParam Integer limit);

    @DeleteMapping("/hydroponic/calibration-data")
    ResponseEntity<Void> deleteCalibrationDataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid);

    //        HYDROPONIC SETTINGS CALLS

    @GetMapping("/hydroponic/settings/last")
    ResponseEntity<List<HydroponicSettingsDto>> getLastSettingsInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                                @RequestParam String uuid,
                                                                                @RequestParam(defaultValue = "0") Integer page,
                                                                                @RequestParam(defaultValue = "15") Integer limit);

    //        CONTROL HYDROPONICS CALLS

    @PutMapping("/hydroponic/control/update/time")
    ResponseEntity<String> updateTime(@RequestHeader(AUTHORIZATION) String token,
                                      @RequestBody HydroponicTimeDto dto);

    @PutMapping("/hydroponic/control/update/zone")
    ResponseEntity<String> updateZone(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam String uuid,
            @RequestParam String value);

    @PutMapping("/hydroponic/control/update/autotime")
    ResponseEntity<String> updateAutoTime(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/pumps/phUp")
    ResponseEntity<String> updatePhUpPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/pumps/phDown")
    ResponseEntity<String> updatePhDownPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/pumps/tds")
    ResponseEntity<String> updateTdsPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                               @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/restart")
    ResponseEntity<String> restart(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid);

    @PostMapping("/hydroponic/control/save/settings")
    ResponseEntity<String> saveAllSettings(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid);

    @GetMapping("/hydroponic/control/read/settings")
    ResponseEntity<String> readAllSettings(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid);

    @PutMapping("/hydroponic/control/calibrate/tds")
    ResponseEntity<String> calibrateTdsSensor(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/calibrate/tds/clear")
    ResponseEntity<String> clrCalibrationTdsSensor(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid);

    @PutMapping("/hydroponic/control/calibrate/ph/low")
    ResponseEntity<String> calibratePhLow(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/calibrate/ph/high")
    ResponseEntity<String> calibratePhHigh(@RequestHeader(AUTHORIZATION) String token,
                                           @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/calibrate/ph/clear")
    ResponseEntity<String> clrCalibrationPhSensor(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid);

    @PutMapping("/hydroponic/control/setup/ph")
    ResponseEntity<String> updateSetupPhValue(
            @RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/setup/tds")
    ResponseEntity<String> updateSetupTdsValue(@RequestHeader(AUTHORIZATION) String token,
                                               @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/dispensers/recheck-time")
    ResponseEntity<String> updateRecheckDispensersAfterTime(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/dose/ph/up")
    ResponseEntity<String> updatePhUpDose(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/dose/ph/down")
    ResponseEntity<String> updatePhDownDose(@RequestHeader(AUTHORIZATION) String token,
                                            @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/dose/tds")
    ResponseEntity<String> updateFertilizerDose(@RequestHeader(AUTHORIZATION) String token,
                                                @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/regulator/error/ph")
    ResponseEntity<String> updateRegulatePhError(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/regulator/error/tds")
    ResponseEntity<String> updateRegulateTdsError(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/pump/speed")
    ResponseEntity<String> updatePumpSpeed(@RequestHeader(AUTHORIZATION) String token,
                                           @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/wifi/ssid")
    ResponseEntity<String> updateWifiSsid(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/wifi/pass")
    ResponseEntity<String> updateWifiPassword(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/enable/sensors")
    ResponseEntity<String> updateSensorsEnable(@RequestHeader(AUTHORIZATION) String token,
                                               @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/update/enable/dispensers")
    ResponseEntity<String> updateDispensersEnable(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid, @RequestParam String value);
}
