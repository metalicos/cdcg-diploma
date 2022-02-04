package ua.com.cyberdone.cloudgateway.controller.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.documentation.device.DeviceSchedulingApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.RegularScheduleDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.RegularScheduleUpdateDto;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class DeviceScheduleController implements DeviceSchedulingApi {
    private final DeviceMicroserviceFeignClient deviceFeignClient;

    @GetMapping
    public ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(@RequestHeader(AUTHORIZATION) String token,
                                                                      @RequestParam String uuid,
                                                                      @RequestParam String key) {
        return deviceFeignClient.getSchedulesByKey(token, uuid, key);
    }

    @PostMapping
    public ResponseEntity<RegularScheduleDto> createSchedule(@RequestHeader(AUTHORIZATION) String token,
                                                             @Valid @RequestBody RegularScheduleDto schedule) {
        return deviceFeignClient.createSchedule(token, schedule);
    }

    @PatchMapping
    public ResponseEntity<String> updateScheduleMetaInfo(@RequestHeader(AUTHORIZATION) String token,
                                                         @Valid @RequestBody RegularScheduleUpdateDto schedule) {
        return deviceFeignClient.updateScheduleMetaInfo(token, schedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleById(@RequestHeader(AUTHORIZATION) String token,
                                                     @PathVariable("id") Long id) {
        return deviceFeignClient.deleteScheduleById(token, id);
    }
}
