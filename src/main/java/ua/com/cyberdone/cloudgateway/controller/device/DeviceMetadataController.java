package ua.com.cyberdone.cloudgateway.controller.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.cloudgateway.controller.docs.device.DeviceMetadataApi;
import ua.com.cyberdone.cloudgateway.exception.NotFoundException;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceMetadataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceType;

import java.io.IOException;
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

    @PutMapping
    public ResponseEntity<DeviceMetadataDto> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestParam String uuid,
                                                            @RequestParam String name,
                                                            @RequestParam String description)
            throws IOException, NotFoundException {
        return deviceFeignClient.updateMetadata(token, uuid, name, description);
    }

    @PutMapping("/{uuid}/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata')")
    public ResponseEntity<DeviceMetadataDto> updateDeviceImage(@RequestHeader(AUTHORIZATION) String token,
                                                               @PathVariable String uuid,
                                                               @RequestPart("file") MultipartFile deviceImage)
            throws IOException, NotFoundException {
        return deviceFeignClient.updateDeviceImage(token, uuid, deviceImage);
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
