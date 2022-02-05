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
import ua.com.cyberdone.cloudgateway.documentation.account.RoleApi;
import ua.com.cyberdone.cloudgateway.exception.AlreadyExistException;
import ua.com.cyberdone.cloudgateway.exception.NotFoundException;
import ua.com.cyberdone.cloudgateway.feign.AccountMicroserviceFeignClient;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.role.CreateRoleDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.role.RoleDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.role.RolesDto;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController implements RoleApi {
    private final AccountMicroserviceFeignClient accountFeignClient;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts')")
    public ResponseEntity<RolesDto> readRoles(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size,
                                              @RequestParam(defaultValue = "NONE") String direction,
                                              @RequestParam(defaultValue = "NONE") String sortBy) {
        return accountFeignClient.readRoles(token, page, size, direction, sortBy);
    }

    @GetMapping("/{role-name}")
    @PreAuthorize("hasAnyAuthority('r_all','r_account','r_self')")
    public ResponseEntity<RoleDto> readRole(@RequestHeader(AUTHORIZATION) String token,
                                            @PathVariable(value = "role-name") String roleName)
            throws NotFoundException {
        return accountFeignClient.readRole(token, roleName);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_accounts')")
    public ResponseEntity<String> deleteRoles(@RequestHeader(AUTHORIZATION) String token) {
        return accountFeignClient.deleteRoles(token);
    }

    @DeleteMapping("/{role-name}")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    public ResponseEntity<String> deleteRole(@RequestHeader(AUTHORIZATION) String token,
                                             @PathVariable(value = "role-name") String roleName) {
        return accountFeignClient.deleteRole(token, roleName);
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestBody CreateRoleDto createRoleDto)
            throws AlreadyExistException, NotFoundException {
        return accountFeignClient.createRole(token, createRoleDto);
    }
}
