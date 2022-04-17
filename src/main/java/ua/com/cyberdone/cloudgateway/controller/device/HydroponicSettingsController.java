package ua.com.cyberdone.cloudgateway.controller.device;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.controller.docs.device.HydroponicSettingsApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicSettingsDto;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/settings")
public class HydroponicSettingsController implements HydroponicSettingsApi {
    private final DeviceMicroserviceFeignClient deviceFeignClient;

    @GetMapping("/last")
    public ResponseEntity<List<HydroponicSettingsDto>> getLastSettingsInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                                       @RequestParam String uuid,
                                                                                       @RequestParam(defaultValue = "0") Integer page,
                                                                                       @RequestParam(defaultValue = "15") Integer limit) {
        return deviceFeignClient.getLastSettingsInDeviceWithUuid(token, uuid, page, limit);
    }
}
