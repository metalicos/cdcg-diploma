package ua.com.cyberdone.cloudgateway.controller.device.hydroponic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.controller.docs.device.HydroponicControlApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.DatabasePhCalibrationDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.DatabaseTdsCalibrationDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicTimeDto;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/control")
public class HydroponicControlController implements HydroponicControlApi {
    private final DeviceMicroserviceFeignClient deviceFeignClient;

    @PutMapping("/update/time")
    public ResponseEntity<String> updateTime(@RequestHeader(AUTHORIZATION) String token,
                                             @RequestBody HydroponicTimeDto dto) {
        return deviceFeignClient.updateTime(token, dto);
    }

    @PutMapping("/update/zone")
    public ResponseEntity<String> updateZone(@RequestHeader(AUTHORIZATION) String token,
                                             @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateZone(token, uuid, value);
    }

    @PutMapping("/update/autotime")
    public ResponseEntity<String> updateAutoTime(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateAutoTime(token, uuid, value);
    }

    @PutMapping("/update/pumps/phUp")
    public ResponseEntity<String> updatePhUpPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                       @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updatePhUpPumpStatus(token, uuid, value);
    }

    @PutMapping("/update/pumps/phDown")
    public ResponseEntity<String> updatePhDownPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updatePhDownPumpStatus(token, uuid, value);
    }

    @PutMapping("/update/pumps/tds")
    public ResponseEntity<String> updateTdsPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateTdsPumpStatus(token, uuid, value);
    }

    @PutMapping("/update/pumps/{pumpNumber}/polarity")
    public ResponseEntity<String> updatePumpPolarity(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @PathVariable String pumpNumber) {
        return deviceFeignClient.updatePumpPolarity(token, uuid, pumpNumber);
    }

    @PutMapping("/update/restart")
    public ResponseEntity<String> restart(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid) {
        return deviceFeignClient.restart(token, uuid);
    }

    @PutMapping("/update/restart-counter")
    public ResponseEntity<String> restartCounter(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String value) {
        return deviceFeignClient.updateRestartCounter(token, uuid, value);
    }

    @PutMapping("/save/settings")
    public ResponseEntity<String> saveAllSettings(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid) {
        return deviceFeignClient.saveAllSettings(token, uuid);
    }

    @PutMapping("/read/settings")
    public ResponseEntity<String> readAllSettings(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid) {
        return deviceFeignClient.readAllSettings(token, uuid);
    }

    @PutMapping("/calibrate/tds")
    public ResponseEntity<String> calibrateTdsSensor(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.calibrateTdsSensor(token, uuid, value);
    }

    @PutMapping("/calibrate/tds/clear")
    public ResponseEntity<String> clrCalibrationTdsSensor(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestParam String uuid) {
        return deviceFeignClient.clrCalibrationTdsSensor(token, uuid);
    }

    @PutMapping("/calibrate/ph/low")
    public ResponseEntity<String> calibratePhLow(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.calibratePhLow(token, uuid, value);
    }

    @PutMapping("/calibrate/ph/high")
    public ResponseEntity<String> calibratePhHigh(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.calibratePhHigh(token, uuid, value);
    }

    @PutMapping("/calibrate/ph/clear")
    public ResponseEntity<String> clrCalibrationPhSensor(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid) {
        return deviceFeignClient.clrCalibrationPhSensor(token, uuid);
    }

    @PutMapping("/setup/ph")
    public ResponseEntity<String> updateSetupPhValue(
            @RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateSetupPhValue(token, uuid, value);
    }

    @PutMapping("/setup/tds")
    public ResponseEntity<String> updateSetupTdsValue(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateSetupTdsValue(token, uuid, value);
    }

    @PutMapping("/update/dispensers/tds/recheck-time")
    public ResponseEntity<String> updateRecheckTdsDispensersAfterTime(@RequestHeader(AUTHORIZATION) String token,
                                                                      @RequestParam String uuid,
                                                                      @RequestParam String value) {
        return deviceFeignClient.updateRecheckTdsDispensersAfterTime(token, uuid, value);
    }

    @PutMapping("/update/dispensers/ph/recheck-time")
    public ResponseEntity<String> updateRecheckPhDispensersAfterTime(@RequestHeader(AUTHORIZATION) String token,
                                                                     @RequestParam String uuid,
                                                                     @RequestParam String value) {
        return deviceFeignClient.updateRecheckPhDispensersAfterTime(token, uuid, value);
    }

    @PutMapping("/update/clear-impurity-calculation")
    public ResponseEntity<String> updateClearImpurityCalculation(@RequestHeader(AUTHORIZATION) String token,
                                                                 @RequestParam String uuid) {
        return deviceFeignClient.updateClearImpurityCalculation(token, uuid);
    }

    @PutMapping("/update/calculate/start-solution-as-fertilizer")
    public ResponseEntity<String> updateCalculateStartSolutionAsFertilizer(@RequestHeader(AUTHORIZATION) String token,
                                                                           @RequestParam String uuid,
                                                                           @RequestParam String value) {
        return deviceFeignClient.updateCalculateStartSolutionAsFertilizer(token, uuid, value);
    }

    @PutMapping("/update/calculate/ph-regulation-as-fertilizer")
    public ResponseEntity<String> updateCalculatePhRegulationAsFertilizer(@RequestHeader(AUTHORIZATION) String token,
                                                                          @RequestParam String uuid,
                                                                          @RequestParam String value) {
        return deviceFeignClient.updateCalculateStartSolutionAsFertilizer(token, uuid, value);
    }

    @PutMapping("/update/manual-data-refresh")
    public ResponseEntity<String> updateRequestFreshData(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid) {
        return deviceFeignClient.requestFreshData(token, uuid);
    }

    @PutMapping("/update/dose/ph/up")
    public ResponseEntity<String> updatePhUpDose(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updatePhUpDose(token, uuid, value);
    }

    @PutMapping("/update/dose/ph/down")
    public ResponseEntity<String> updatePhDownDose(@RequestHeader(AUTHORIZATION) String token,
                                                   @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updatePhDownDose(token, uuid, value);
    }

    @PutMapping("/update/dose/tds")
    public ResponseEntity<String> updateFertilizerDose(@RequestHeader(AUTHORIZATION) String token,
                                                       @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateFertilizerDose(token, uuid, value);
    }

    @PutMapping("/update/regulator/error/ph")
    public ResponseEntity<String> updateRegulatePhError(@RequestHeader(AUTHORIZATION) String token,
                                                        @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateRegulatePhError(token, uuid, value);
    }

    @PutMapping("/update/regulator/error/tds")
    public ResponseEntity<String> updateRegulateTdsError(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateRegulateTdsError(token, uuid, value);
    }

    @PutMapping("/update/pump/speed")
    public ResponseEntity<String> updatePumpSpeed(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updatePumpSpeed(token, uuid, value);
    }

    @PutMapping("/update/wifi/ssid")
    public ResponseEntity<String> updateWifiSsid(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateWifiSsid(token, uuid, value);
    }

    @PutMapping("/update/wifi/pass")
    public ResponseEntity<String> updateWifiPassword(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateWifiPassword(token, uuid, value);
    }

    @PutMapping("/update/enable/sensors")
    public ResponseEntity<String> updateSensorsEnable(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateSensorsEnable(token, uuid, value);
    }

    @PutMapping("/update/enable/dispensers")
    public ResponseEntity<String> updateDispensersEnable(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateDispensersEnable(token, uuid, value);
    }

    @PutMapping("/calibrate/ph/database")
    public ResponseEntity<String> updatePhFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                           @RequestBody DatabasePhCalibrationDto dto) {
        return deviceFeignClient.updatePhFromDatabaseData(token, dto);
    }

    @PutMapping("/calibrate/tds/database")
    public ResponseEntity<String> updateTdsFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestBody DatabaseTdsCalibrationDto dto) {
        return deviceFeignClient.updateTdsFromDatabaseData(token, dto);
    }
}
