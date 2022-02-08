package ua.com.cyberdone.cloudgateway.controller.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.documentation.device.DeviceMetadataApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceMetadataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceType;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device/metadata")
public class DeviceMetadataController implements DeviceMetadataApi {
    private final DeviceMicroserviceFeignClient deviceFeignClient;

    @GetMapping
    public ResponseEntity<DeviceMetadataDto> getMetadataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                               @RequestParam String uuid) {
        return deviceFeignClient.getMetadataByUuid(token, uuid);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DeviceMetadataDto>> getMetadataByUser(@RequestHeader(AUTHORIZATION) String token,
                                                                     @RequestParam Long userId) {
        return deviceFeignClient.getMetadataByUser(token, userId);
    }

    @PatchMapping
    public ResponseEntity<String> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String name,
                                                 @RequestParam String description) {
        return deviceFeignClient.updateMetadata(token, uuid, name, description);
    }

    @PostMapping
    public ResponseEntity<DeviceMetadataDto> createMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestBody DeviceMetadataDto metadataDto) {
        return deviceFeignClient.createMetadata(token, metadataDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid) {
        return deviceFeignClient.deleteMetadata(token, uuid);
    }

    @GetMapping("/device-types")
    public ResponseEntity<DeviceType[]> getDeviceTypesList(@RequestHeader(AUTHORIZATION) String token) {
        return deviceFeignClient.getDeviceTypesList(token);
    }

    @PutMapping("/unlink")
    public ResponseEntity<String> unlinkMetadataFromUser(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid) {
        return deviceFeignClient.unlinkMetadataFromUser(token, uuid);
    }

    @PutMapping("/link")
    public ResponseEntity<String> linkMetadataToUser(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid, @RequestParam Long userId) {
        return deviceFeignClient.linkMetadataToUser(token, uuid, userId);
    }
}