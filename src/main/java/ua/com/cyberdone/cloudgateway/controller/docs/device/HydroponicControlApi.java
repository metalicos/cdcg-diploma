package ua.com.cyberdone.cloudgateway.controller.docs.device;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.cloudgateway.constant.ControllerConstantUtils;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.DatabasePhCalibrationDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.DatabaseTdsCalibrationDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicTimeDto;

@Tag(name = "Hydroponic Control", description = "Endpoints for control hydroponic microcontrollers")
public interface HydroponicControlApi {

    @Operation(summary = "Update hydroponic time", description = "Send update time command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update time command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateTime(String token, HydroponicTimeDto dto);

    @Operation(summary = "Update hydroponic time zone", description = "Send update time zone command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update time zone command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateZone(String token, String uuid, String value);

    @Operation(summary = "Update hydroponic auto time mode", description = "Send update auto time mode command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update auto time mode command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateAutoTime(String token, String uuid, String value);

    @Operation(summary = "Update hydroponic ph-up pump mode", description = "Send update ph-up pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update ph-up pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhUpPumpStatus(String token, String uuid, String value);

    @Operation(summary = "Update hydroponic ph-down pump mode", description = "Send update ph-down pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update ph-down pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhDownPumpStatus(String token, String uuid, String value);

    @Operation(summary = "Update hydroponic tds pump mode", description = "Send update tds pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update tds pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateTdsPumpStatus(String token, String uuid, String value);

    @Operation(summary = "Update polarity of pump", description = "Update pump`s polarity. For example if pump is rotating left, then after the change it will be rotating in right direction.")
    @ApiResponse(responseCode = "200", description = "Update pump`s polarity. For example if pump is rotating left, then after the change it will be rotating in right direction.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePumpPolarity(String token, String uuid, String pumpNumber);

    @Operation(summary = "Restart hydroponic microcontroller", description = "Restart hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Restart hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> restart(String token, String uuid);

    @Operation(summary = "Update restart counter value", description = "Update restart counter")
    @ApiResponse(responseCode = "200", description = "Update restart counter",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> restartCounter(String token, String uuid, String value);

    @Operation(summary = "Save all settings in hydroponic microcontroller memory", description = "Save all settings in hydroponic microcontroller EEPROM memory")
    @ApiResponse(responseCode = "200", description = "Save settings in hydroponic microcontroller EEPROM memory",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> saveAllSettings(String token, String uuid);

    @Operation(summary = "Read all settings from memory to hydroponic microcontroller", description = "Read all settings from memory to hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Read all settings from memory to hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> readAllSettings(String token, String uuid);

    @Operation(summary = "Calibrate tds sensor", description = "Calibrate tds sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Calibrate tds sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> calibrateTdsSensor(String token, String uuid, String value);

    @Operation(summary = "Clear calibration of tds sensor", description = "Clear calibration of tds sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Clear calibration of tds sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> clrCalibrationTdsSensor(String token, String uuid);

    @Operation(summary = "Calibration of low point of ph sensor", description = "Calibration of low point of ph sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Calibration of low point of ph sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> calibratePhLow(String token, String uuid, String value);

    @Operation(summary = "Calibration of high point of ph sensor", description = "Calibration of high point of ph sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Calibration of high point of ph sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> calibratePhHigh(String token, String uuid, String value);

    @Operation(summary = "Clear calibration of ph sensor", description = "Clear calibration of ph sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Clear calibration of ph sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> clrCalibrationPhSensor(String token, String uuid);

    @Operation(summary = "Change ph value to maintain by controller", description = "Change ph value to maintain by controller")
    @ApiResponse(responseCode = "200", description = "Change ph value to maintain by controller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateSetupPhValue(String token, String uuid, String value);

    @Operation(summary = "Change tds value to maintain by controller", description = "Change tds value to maintain by controller")
    @ApiResponse(responseCode = "200", description = "Change tds value to maintain by controller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateSetupTdsValue(String token, String uuid, String value);

    @Operation(summary = "Change recheck dispensers time", description = "Change time to recheck dispensers after")
    @ApiResponse(responseCode = "200", description = "Change time to recheck dispensers after",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateRecheckDispensersAfterTime(String token, String uuid, String value);

    @Operation(summary = "Change dose for ph up pump one time injection", description = "Change dose for ph up pump one time injection")
    @ApiResponse(responseCode = "200", description = "Change dose for ph up pump one time injection",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhUpDose(String token, String uuid, String value);

    @Operation(summary = "Change dose for ph down pump one time injection", description = "Change dose for ph down pump one time injection")
    @ApiResponse(responseCode = "200", description = "Change dose for ph down pump one time injection",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhDownDose(String token, String uuid, String value);

    @Operation(summary = "Change dose for tds pump one time injection", description = "Change dose for tds pump one time injection")
    @ApiResponse(responseCode = "200", description = "Change dose for tds pump one time injection",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateFertilizerDose(String token, String uuid, String value);

    @Operation(summary = "Change error for ph pump", description = "Change error for ph pump")
    @ApiResponse(responseCode = "200", description = "Change error for ph pump",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateRegulatePhError(String token, String uuid, String value);

    @Operation(summary = "Change error for tds pump", description = "Change error for tds pump")
    @ApiResponse(responseCode = "200", description = "Change error for tds pump",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateRegulateTdsError(String token, String uuid, String value);

    @Operation(summary = "Change pumping speed", description = "Change pumping speed")
    @ApiResponse(responseCode = "200", description = "Change pumping speed",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePumpSpeed(String token, String uuid, String value);

    @Operation(summary = "Change wifi SSID", description = "Change SSID of wifi access point setting in hydroponic controller")
    @ApiResponse(responseCode = "200", description = "Change SSID of wifi access point setting in hydroponic controller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateWifiSsid(String token, String uuid, String value);

    @Operation(summary = "Change wifi PASS", description = "Change password of your wifi access point setting in hydroponic controller")
    @ApiResponse(responseCode = "200", description = "Change password of your wifi access point setting in hydroponic controller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateWifiPassword(String token, String uuid, String value);

    @Operation(summary = "Update hydroponic sensors availability", description = "Update hydroponic sensors availability (ENABLED=1 | DISABLED=0)")
    @ApiResponse(responseCode = "200", description = "Update hydroponic sensors availability (ENABLED=1 | DISABLED=0)",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateSensorsEnable(String token, String uuid, String value);

    @Operation(summary = "Update hydroponic dispensers availability", description = "Update hydroponic dispensers availability (ENABLED=1 | DISABLED=0)")
    @ApiResponse(responseCode = "200", description = "Update hydroponic dispensers availability (ENABLED=1 | DISABLED=0)",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateDispensersEnable(String token, String uuid, String value);

    @Operation(summary = "Update PH calibration using data earlier saved in the DB", description = "Update PH calibration using data earlier saved in the DB")
    @ApiResponse(responseCode = "200", description = "Update PH calibration using data earlier saved in the DB",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhFromDatabaseData(String token, DatabasePhCalibrationDto dto);

    @Operation(summary = "Update TDS calibration using data earlier saved in the DB", description = "Update TDS calibration using data earlier saved in the DB")
    @ApiResponse(responseCode = "200", description = "Update TDS calibration using data earlier saved in the DB",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateTdsFromDatabaseData(String token, DatabaseTdsCalibrationDto dto);
}
