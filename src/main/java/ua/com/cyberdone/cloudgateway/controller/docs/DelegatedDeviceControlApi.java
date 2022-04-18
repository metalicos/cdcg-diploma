package ua.com.cyberdone.cloudgateway.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.DelegatedDeviceControlDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.DelegationStatus;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.delegation.PageableDelegatedDeviceControlDto;

@Tag(name = "Delegated Device Control", description = "Endpoints base for delegation device control between users")
public interface DelegatedDeviceControlApi {

    @Operation(summary = "Get Delegated Device Control For User By His Token", description = "Get Delegated Device Control For User By His Token")
    @ApiResponse(responseCode = "200", description = "Get Delegated Device Control For User By His Token",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageableDelegatedDeviceControlDto.class)))
    ResponseEntity<PageableDelegatedDeviceControlDto> getDelegatedDeviceControlForUserByToken(
            String token, Integer page, Integer size, String direction, String sortBy);

    @Operation(summary = "Get Delegated Device Control For Owner By Device UUID", description = "Get Delegated Device Control For Owner By Device UUID")
    @ApiResponse(responseCode = "200", description = "Get Delegated Device Control For Owner By Device UUID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageableDelegatedDeviceControlDto.class)))
    ResponseEntity<PageableDelegatedDeviceControlDto> getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(
            String token, String deviceUuid, DelegationStatus delegationStatus, Integer page, Integer size, String direction, String sortBy);

    @Operation(summary = "Get Delegated Device Control For User By Device UUID", description = "Get Delegated Device Control For User By Device UUID")
    @ApiResponse(responseCode = "200", description = "Get Delegated Device Control For User By Device UUID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DelegatedDeviceControlDto.class)))
    ResponseEntity<DelegatedDeviceControlDto> getDelegatedDeviceControlForUser(
            String token, String username, String deviceUuid);

    @Operation(summary = "Update Delegated Device Control For Owner",
            description = "Update Delegated Device Control For Owner By Device UUID And Delegated User Username")
    @ApiResponse(responseCode = "200", description = "Update Delegated Device Control For Owner By Device UUID And Delegated User Username",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateDelegationStatus(
            String token, String deviceUuid, String username,
            DelegationStatus delegationStatus);

    @Operation(summary = "Create Delegated Device Control Request",
            description = "Create Delegated Device Control Request")
    @ApiResponse(responseCode = "200", description = "Create Delegated Device Control Request",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<DelegatedDeviceControlDto> createDelegatedDeviceControl(
            String token, String comment, String deviceUuid);
}
