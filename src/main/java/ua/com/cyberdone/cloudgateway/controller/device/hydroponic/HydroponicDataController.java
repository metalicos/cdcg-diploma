package ua.com.cyberdone.cloudgateway.controller.device.hydroponic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.controller.docs.device.HydroponicDataApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicDataDto;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/data")
public class HydroponicDataController implements HydroponicDataApi {
    private final DeviceMicroserviceFeignClient deviceFeignClient;

    @GetMapping("/last")
    public ResponseEntity<List<HydroponicDataDto>> getLastDataInDeviceWithUUID(@RequestHeader(AUTHORIZATION) String token,
                                                                               @RequestParam String uuid,
                                                                               @RequestParam Integer page,
                                                                               @RequestParam Integer limit) {
        return deviceFeignClient.getLastDataInDeviceWithUUID(token, uuid, page, limit);
    }

    @GetMapping("/range")
    public ResponseEntity<List<HydroponicDataDto>> getLastDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                               @RequestParam String uuid,
                                                                               @RequestParam String fromDate,
                                                                               @RequestParam String toDate,
                                                                               @RequestParam int dataStep) {
        return deviceFeignClient.getLastDataByUuid(token, uuid, fromDate, toDate, dataStep);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllDataInDeviceWithUUID(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam Long id) {
        return deviceFeignClient.deleteAllDataInDeviceWithUUID(token, id);
    }
}
