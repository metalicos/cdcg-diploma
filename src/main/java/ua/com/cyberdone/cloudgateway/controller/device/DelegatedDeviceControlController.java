package ua.com.cyberdone.cloudgateway.controller.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.controller.docs.DelegatedDeviceControlApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.DelegatedDeviceControlDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.DelegationStatus;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.PageableDelegatedDeviceControlDto;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DEFAULT_COMMENT_FOR_DEVICE_CONTROL_DELEGATION;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DEFAULT_DIRECTION;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DEFAULT_ELEMENTS_AMOUNT;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DEFAULT_PAGE;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DEFAULT_SEARCH;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DELEGATION_STATUS_PARAMETER;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.DEVICE_UUID_PARAMETER;
import static ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils.USERNAME_PARAMETER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delegated-device-controls")
public class DelegatedDeviceControlController implements DelegatedDeviceControlApi {
    private final DeviceMicroserviceFeignClient deviceMicroserviceFeignClient;

    @GetMapping("/self")
    public ResponseEntity<PageableDelegatedDeviceControlDto> getDelegatedDeviceControlForUserByToken(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String sortBy) {
        return deviceMicroserviceFeignClient.getDelegatedDeviceControlForUserByToken(token, page, size, direction, sortBy);
    }

    @GetMapping("/list")
    public ResponseEntity<PageableDelegatedDeviceControlDto> getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid,
            @RequestParam(DELEGATION_STATUS_PARAMETER) DelegationStatus delegationStatus,
            @RequestParam( required = false) Integer page,
            @RequestParam( required = false) Integer size,
            @RequestParam( required = false) String direction,
            @RequestParam(required = false) String sortBy) {
        return deviceMicroserviceFeignClient.getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(
                token, deviceUuid, delegationStatus, page, size, direction, sortBy);
    }

    @GetMapping
    public ResponseEntity<DelegatedDeviceControlDto> getDelegatedDeviceControlForUser(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(USERNAME_PARAMETER) String username,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid) {
        return deviceMicroserviceFeignClient.getDelegatedDeviceControlForUser(token, username, deviceUuid);
    }

    @PatchMapping
    public ResponseEntity<String> updateDelegationStatus(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid,
            @RequestParam(USERNAME_PARAMETER) String username,
            @RequestParam(DELEGATION_STATUS_PARAMETER) DelegationStatus delegationStatus) {
        return deviceMicroserviceFeignClient.updateDelegationStatus(token, deviceUuid, username, delegationStatus);
    }

    @PostMapping
    public ResponseEntity<DelegatedDeviceControlDto> createDelegatedDeviceControl(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(required = false) String comment,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid) {
        return deviceMicroserviceFeignClient.createDelegatedDeviceControl(token, comment, deviceUuid);
    }
}
