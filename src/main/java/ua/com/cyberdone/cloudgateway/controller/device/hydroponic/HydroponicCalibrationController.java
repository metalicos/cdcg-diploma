package ua.com.cyberdone.cloudgateway.controller.device.hydroponic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.controller.docs.device.HydroponicCalibrationApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicCalibrationDataDto;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/calibration-data")
public class HydroponicCalibrationController implements HydroponicCalibrationApi {
    private final DeviceMicroserviceFeignClient deviceFeignClient;

    @GetMapping("/last")
    public ResponseEntity<List<HydroponicCalibrationDataDto>> getLastCalibrationDataInDeviceWithUuid(
            @RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid,
            @RequestParam Integer page, @RequestParam Integer limit) {
        return deviceFeignClient.getLastCalibrationDataInDeviceWithUuid(token, uuid, page, limit);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCalibrationDataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestParam String uuid) {
        return deviceFeignClient.deleteCalibrationDataByUuid(token, uuid);
    }
}
