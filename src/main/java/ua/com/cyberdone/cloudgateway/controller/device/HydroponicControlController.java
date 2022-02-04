package ua.com.cyberdone.cloudgateway.controller.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.documentation.device.HydroponicControlApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
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
        return deviceFeignClient.updatePhUpPumpStatus(token, uuid, value);
    }

    @PutMapping("/update/pumps/tds")
    public ResponseEntity<String> updateTdsPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateTdsPumpStatus(token, uuid, value);
    }

    @PutMapping("/update/restart")
    public ResponseEntity<String> restart(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid) {
        return deviceFeignClient.restart(token, uuid);
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

    @PutMapping("/update/dispensers/recheck-time")
    public ResponseEntity<String> updateRecheckDispensersAfterTime(@RequestHeader(AUTHORIZATION) String token,
                                                                   @RequestParam String uuid, @RequestParam String value) {
        return deviceFeignClient.updateRecheckDispensersAfterTime(token, uuid, value);
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
}
