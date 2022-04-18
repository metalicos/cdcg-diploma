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
import ua.com.cyberdone.cloudgateway.controller.docs.account.RoleApi;
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
    public ResponseEntity<RolesDto> readRoles(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) Integer size,
                                              @RequestParam(required = false) String direction,
                                              @RequestParam(required = false) String sortBy) {
        return accountFeignClient.readRoles(token, page, size, direction, sortBy);
    }

    @GetMapping("/{role-name}")
    public ResponseEntity<RoleDto> readRole(@RequestHeader(AUTHORIZATION) String token,
                                            @PathVariable(value = "role-name") String roleName)
            throws NotFoundException {
        return accountFeignClient.readRole(token, roleName);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRoles(@RequestHeader(AUTHORIZATION) String token) {
        return accountFeignClient.deleteRoles(token);
    }

    @DeleteMapping("/{role-name}")
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
