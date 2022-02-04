package ua.com.cyberdone.cloudgateway.documentation.device;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceMetadataDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.DeviceType;

import java.util.List;

@Tag(name = "Device Metadata", description = "Endpoints for managing device metadata")
public interface DeviceMetadataApi {

    @Operation(summary = "Read device metadata by device UUID", description = "Return device metadata. Find metadata by device unique identifier UUID.")
    @ApiResponse(responseCode = "200", description = "Return device metadata. Find metadata by device unique identifier UUID.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DeviceMetadataDto.class)))
    ResponseEntity<DeviceMetadataDto> getMetadataByUuid(String token, String uuid);

    @Operation(summary = "Read list of device metadata by owner user", description = "Return device metadata list. Find metadata by device owner.")
    @ApiResponse(responseCode = "200", description = "Return device metadata list. Find metadata by device owner.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = DeviceMetadataDto.class))))
    ResponseEntity<List<DeviceMetadataDto>> getMetadataByUser(String token, Long userId);


    @Operation(summary = "Update device metadata (name && description)", description = "Update description and name of the device. User customization purpose.")
    @ApiResponse(responseCode = "200", description = "Update description and name of the device. User customization purpose.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateMetadata(String token,
                                          String uuid,
                                          String name,
                                          String description);

    @Operation(summary = "Create device metadata", description = "Creates metadata for device.")
    @ApiResponse(responseCode = "200", description = "Create device metadata, also creates a device, because metadata is the main concept.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DeviceMetadataDto.class)))
    ResponseEntity<DeviceMetadataDto> createMetadata(String token, DeviceMetadataDto metadataDto);

    @Operation(summary = "Delete device metadata", description = "Permanent delete device metadata.")
    @ApiResponse(responseCode = "200", description = "Permanent delete device metadata.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteMetadata(String token, String uuid);

    @Operation(summary = "Read device types", description = "Get all available device types.")
    @ApiResponse(responseCode = "200", description = "Get all available device types.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class)))
    ResponseEntity<DeviceType[]> getDeviceTypesList(String token);

    @Operation(summary = "Unlink device from user", description = "Unlink device with UUID from user with ID")
    @ApiResponse(responseCode = "200", description = "Unlink device with UUID from user with ID",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class)))
    ResponseEntity<String> unlinkMetadataFromUser(String token, String uuid);

    @Operation(summary = "Link device to user", description = "Link device with UUID to user with ID")
    @ApiResponse(responseCode = "200", description = "Link device with UUID to user with ID",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> linkMetadataToUser(String token, String uuid, Long userId);
}
