package ua.com.cyberdone.cloudgateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceMetadataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceType;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.RegularScheduleDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.RegularScheduleUpdateDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.DelegatedDeviceControlDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.DelegationStatus;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.PageableDelegatedDeviceControlDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.DatabasePhCalibrationDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.DatabaseTdsCalibrationDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicCalibrationDataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicDataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicSettingTemplateDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicTimeDto;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DELEGATION_STATUS_PARAMETER;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DEVICE_UUID_PARAMETER;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.USERNAME_PARAMETER;

@FeignClient(value = "cyber-done-device-microservice")
public interface DeviceMicroserviceFeignClient {

    /* METADATA CONTROLLER CALLS */
    @GetMapping("/device/metadata")
    ResponseEntity<DeviceMetadataDto> getMetadataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                        @RequestParam String uuid);

    @GetMapping("/device/metadata/list")
    ResponseEntity<List<DeviceMetadataDto>> getMetadataByUser(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam Long userId);

    @PutMapping("/device/metadata")
    ResponseEntity<DeviceMetadataDto> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @RequestParam String name,
                                                     @RequestParam String description);

    @PutMapping(value = "/device/metadata/{uuid}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DeviceMetadataDto> updateDeviceImage(@RequestHeader(AUTHORIZATION) String token,
                                                        @PathVariable String uuid,
                                                        @RequestPart("file") MultipartFile deviceImage);

    @PostMapping("/device/metadata")
    ResponseEntity<DeviceMetadataDto> createMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestBody DeviceMetadataDto metadataDto);

    @DeleteMapping("/device/metadata")
    ResponseEntity<String> deleteMetadata(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid);

    @GetMapping("/device/metadata/device-types")
    ResponseEntity<DeviceType[]> getDeviceTypesList(@RequestHeader(AUTHORIZATION) String token);

    @PutMapping("/device/metadata/unlink")
    ResponseEntity<String> unlinkMetadataFromUser(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid);

    @PutMapping("/device/metadata/link")
    ResponseEntity<String> linkMetadataToUser(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam String uuid, @RequestParam Long userId);

    /* SCHEDULE CONTROLLER CALLS */

    @GetMapping("/device/regular/schedules")
    ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(@RequestHeader(AUTHORIZATION) String token,
                                                               @RequestParam String uuid,
                                                               @RequestParam String key);

    @PostMapping("/device/regular/schedules")
    ResponseEntity<RegularScheduleDto> createSchedule(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestBody RegularScheduleDto schedule);

    @PutMapping("/device/regular/schedules")
    ResponseEntity<String> updateScheduleMetaInfo(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestBody RegularScheduleUpdateDto schedule);

    @DeleteMapping("/device/regular/schedules/{id}")
    ResponseEntity<String> deleteScheduleById(@RequestHeader(AUTHORIZATION) String token,
                                              @PathVariable("id") Long id);

    /* HYDROPONIC DATA CALLS */

    @GetMapping("/hydroponic/data/last")
    ResponseEntity<List<HydroponicDataDto>> getLastDataInDeviceWithUUID(@RequestHeader(AUTHORIZATION) String token,
                                                                        @RequestParam String uuid,
                                                                        @RequestParam(required = false) Integer page,
                                                                        @RequestParam(required = false) Integer limit);

    @GetMapping("/hydroponic/data/range")
    ResponseEntity<List<HydroponicDataDto>> getLastDataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam String uuid,
                                                              @RequestParam String fromDate,
                                                              @RequestParam String toDate,
                                                              @RequestParam Integer dataStep);

    @DeleteMapping("/hydroponic/data")
    ResponseEntity<Void> deleteAllDataInDeviceWithUUID(@RequestHeader(AUTHORIZATION) String token,
                                                       @RequestParam Long id);

    /* HYDROPONIC CALIBRATION CALLS */


    @GetMapping("/hydroponic/calibration-data/last")
    ResponseEntity<List<HydroponicCalibrationDataDto>> getLastCalibrationDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                                              @RequestParam String uuid,
                                                                                              @RequestParam(required = false) Integer page,
                                                                                              @RequestParam(required = false) Integer limit);

    @DeleteMapping("/hydroponic/calibration-data")
    ResponseEntity<Void> deleteCalibrationDataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid);

    /* HYDROPONIC SETTINGS CALLS */

    @GetMapping("/hydroponic/settings/last")
    ResponseEntity<List<HydroponicSettingsDto>> getLastSettingsInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                                @RequestParam String uuid,
                                                                                @RequestParam(required = false) Integer page,
                                                                                @RequestParam(required = false) Integer limit);

    /* HYDROPONIC SETTING TEMPLATE CALLS */

    @GetMapping("/hydroponic/setting/template/last")
    ResponseEntity<List<HydroponicSettingsDto>> getLastSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token, @RequestParam Integer page, @RequestParam Integer limit);

    @PostMapping("/hydroponic/setting/template")
    ResponseEntity<HydroponicSettingTemplateDto> createHydroponicSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token, HydroponicSettingTemplateDto dto);

    @PutMapping("/hydroponic/setting/template")
    ResponseEntity<String> updateHydroponicSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token, @Valid HydroponicSettingTemplateDto dto);

    @DeleteMapping("/hydroponic/setting/template/{templateId}")
    ResponseEntity<String> deleteHydroponicSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long templateId);

    /* CONTROL HYDROPONICS CALLS */

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

    @PutMapping("/hydroponic/control/update/restart-counter")
    ResponseEntity<String> updateRestartCounter(@RequestHeader(AUTHORIZATION) String token,
                                                @RequestParam String uuid, @RequestParam String value);

    @PutMapping("/hydroponic/control/save/settings")
    ResponseEntity<String> saveAllSettings(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid);

    @PutMapping("/hydroponic/control/read/settings")
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

    @PutMapping("/hydroponic/control/update/dispensers/tds/recheck-time")
    ResponseEntity<String> updateRecheckTdsDispensersAfterTime(@RequestHeader(AUTHORIZATION) String token,
                                                               @RequestParam String uuid,
                                                               @RequestParam String value);

    @PutMapping("/hydroponic/control/update/dispensers/ph/recheck-time")
    ResponseEntity<String> updateRecheckPhDispensersAfterTime(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam String uuid,
                                                              @RequestParam String value);

    @PutMapping("/hydroponic/control/update/clear-impurity-calculation")
    ResponseEntity<String> updateClearImpurityCalculation(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestParam String uuid);

    @PutMapping("/hydroponic/control/update/calculate/start-solution-as-fertilizer")
    ResponseEntity<String> updateCalculateStartSolutionAsFertilizer(@RequestHeader(AUTHORIZATION) String token,
                                                                    @RequestParam String uuid,
                                                                    @RequestParam String value);

    @PutMapping("/hydroponic/control/update/calculate/ph-regulation-as-fertilizer")
    ResponseEntity<String> updateCalculatePhRegulationAsFertilizer(@RequestHeader(AUTHORIZATION) String token,
                                                                   @RequestParam String uuid,
                                                                   @RequestParam String value);

    @PutMapping("/hydroponic/control/update/manual-data-refresh")
    ResponseEntity<String> requestFreshData(@RequestHeader(AUTHORIZATION) String token,
                                            @RequestParam String uuid);

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

    @PutMapping("/hydroponic/control/calibrate/ph/database")
    ResponseEntity<String> updatePhFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                    @RequestBody DatabasePhCalibrationDto dto);

    @PutMapping("/hydroponic/control/calibrate/tds/database")
    ResponseEntity<String> updateTdsFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestBody DatabaseTdsCalibrationDto dto);

    @PutMapping("/hydroponic/control/update/pumps/{pumpNumber}/polarity")
    ResponseEntity<String> updatePumpPolarity(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam String uuid,
                                              @PathVariable String pumpNumber);

    /* DEVICE CONTROL DELEGATION */

    @GetMapping("/delegated-device-controls/self")
    ResponseEntity<PageableDelegatedDeviceControlDto> getDelegatedDeviceControlForUserByToken(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String sortBy);

    @GetMapping("/delegated-device-controls/list")
    ResponseEntity<PageableDelegatedDeviceControlDto> getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid,
            @RequestParam(DELEGATION_STATUS_PARAMETER) DelegationStatus delegationStatus,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String sortBy);

    @GetMapping("/delegated-device-controls")
    ResponseEntity<DelegatedDeviceControlDto> getDelegatedDeviceControlForUser(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(USERNAME_PARAMETER) String username,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid);

    @PutMapping("/delegated-device-controls")
    ResponseEntity<String> updateDelegationStatus(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid,
            @RequestParam(USERNAME_PARAMETER) String username,
            @RequestParam(DELEGATION_STATUS_PARAMETER) DelegationStatus delegationStatus);

    @PostMapping("/delegated-device-controls")
    ResponseEntity<DelegatedDeviceControlDto> createDelegatedDeviceControl(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(required = false) String comment,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid);
}
