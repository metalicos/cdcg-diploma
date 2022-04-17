package ua.com.cyberdone.cloudgateway.controller.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.controller.docs.account.PermissionApi;
import ua.com.cyberdone.cloudgateway.exception.AlreadyExistException;
import ua.com.cyberdone.cloudgateway.exception.NotFoundException;
import ua.com.cyberdone.cloudgateway.feign.AccountMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.CreatePermissionDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.PermissionDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.PermissionsDto;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.cloudgateway.constant.Regex.PERMISSION_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.PERMISSION_NAME_PATTERN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController implements PermissionApi {
    private final AccountMicroserviceFeignClient accountFeignClient;

    @GetMapping
    public ResponseEntity<PermissionsDto> readPermissions(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size,
                                                          @RequestParam(defaultValue = "NONE") String direction,
                                                          @RequestParam(defaultValue = "NONE") String sortBy) {
        return accountFeignClient.readPermissions(token, page, size, direction, sortBy);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PermissionDto> readPermission(@RequestHeader(AUTHORIZATION) String token,
                                                        @PathVariable String name) throws NotFoundException {
        return accountFeignClient.readPermission(token, name);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePermissions(@RequestHeader(AUTHORIZATION) String token) {
        return accountFeignClient.deletePermissions(token);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletePermission(
            @RequestHeader(AUTHORIZATION) String token,
            @Pattern(regexp = PERMISSION_NAME_PATTERN, message = PERMISSION_NAME_FAIL_MESSAGE)
            @PathVariable String name) {
        return accountFeignClient.deletePermission(token, name);
    }

    @PostMapping
    public ResponseEntity<PermissionDto> createPermission(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestBody @Valid CreatePermissionDto dto)
            throws AlreadyExistException {
        return accountFeignClient.createPermission(token, dto);
    }
}
