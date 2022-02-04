package ua.com.cyberdone.cloudgateway.controller.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.cloudgateway.documentation.account.PermissionApi;
import ua.com.cyberdone.cloudgateway.exception.AlreadyExistException;
import ua.com.cyberdone.cloudgateway.exception.NotFoundException;
import ua.com.cyberdone.cloudgateway.feign.AccountMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.CreatePermissionDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.PermissionDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.PermissionsDto;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController implements PermissionApi {
    private final AccountMicroserviceFeignClient accountFeignClient;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_permissions')")
    public ResponseEntity<PermissionsDto> readPermissions(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size,
                                                          @RequestParam(defaultValue = "NONE") String direction,
                                                          @RequestParam(defaultValue = "NONE") String sortBy) {
        return accountFeignClient.readPermissions(token, page, size, direction, sortBy);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyAuthority('r_all','r_permission')")
    public ResponseEntity<PermissionDto> readPermission(@RequestHeader(AUTHORIZATION) String token,
                                                        @PathVariable String name) throws NotFoundException {
        return accountFeignClient.readPermission(token, name);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_permissions')")
    public ResponseEntity<String> deletePermissions(@RequestHeader(AUTHORIZATION) String token) {

        return accountFeignClient.deletePermissions(token);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAnyAuthority('d_all','d_permissions')")
    public ResponseEntity<String> deletePermission(@RequestHeader(AUTHORIZATION) String token,
                                                   @PathVariable String name) {
        return accountFeignClient.deletePermission(token, name);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_permissions')")
    public ResponseEntity<PermissionDto> createPermission(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestBody CreatePermissionDto dto)
            throws AlreadyExistException {
        return accountFeignClient.createPermission(token, dto);
    }
}
