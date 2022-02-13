package ua.com.cyberdone.cloudgateway.controller.device;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.documentation.device.HydroponicSettingTemplateApi;
import ua.com.cyberdone.cloudgateway.feign.DeviceMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicSettingTemplateDto;
import ua.com.cyberdone.cloudgateway.model.devicemicroservice.hydroponic.HydroponicSettingsDto;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/setting/template")
public class HydroponicSettingTemplateController implements HydroponicSettingTemplateApi {
    private final DeviceMicroserviceFeignClient deviceMicroserviceFeignClient;

    @GetMapping("/last")
    public ResponseEntity<List<HydroponicSettingsDto>> getLastSettingTemplate(@RequestHeader(AUTHORIZATION) String token,
                                                                              @RequestParam Integer page, @RequestParam Integer limit) {
        return deviceMicroserviceFeignClient.getLastSettingTemplate(token, page, limit);
    }

    @PostMapping
    public ResponseEntity<HydroponicSettingTemplateDto> createHydroponicSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token, HydroponicSettingTemplateDto dto) {
        return deviceMicroserviceFeignClient.createHydroponicSettingTemplate(token, dto);
    }

    @PutMapping
    public ResponseEntity<String> updateHydroponicSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token, HydroponicSettingTemplateDto dto) {
        return deviceMicroserviceFeignClient.updateHydroponicSettingTemplate(token, dto);
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<String> deleteHydroponicSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long templateId) {
        return deviceMicroserviceFeignClient.deleteHydroponicSettingTemplate(token, templateId);
    }
}
