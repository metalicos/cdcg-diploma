package ua.com.cyberdone.cloudgateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.cloudgateway.exception.AccessDeniedException;
import ua.com.cyberdone.cloudgateway.exception.AlreadyExistException;
import ua.com.cyberdone.cloudgateway.exception.AuthenticationException;
import ua.com.cyberdone.cloudgateway.exception.NotFoundException;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.AccountDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.AccountsDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.ChangeEmailDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.ChangeFullNameDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.ChangePasswordDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.LoginDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.LogoutDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.account.RegistrationDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.CreatePermissionDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.PermissionDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.PermissionsDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.role.CreateRoleDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.role.RoleDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.role.RolesDto;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.token.TokenDto;

import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(value = "cyber-done-account-microservice")
public interface AccountMicroserviceFeignClient {

    /*
    ###
    ###          ACCOUNT CONTROLLER CALLS
    ###
     */

    @GetMapping("/accounts")
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts')")
    ResponseEntity<AccountsDto> readAccounts(@RequestHeader(AUTHORIZATION) String token,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int size,
                                             @RequestParam(defaultValue = "NONE") String direction,
                                             @RequestParam(defaultValue = "NONE") String sortBy)
            throws NotFoundException;

    @GetMapping("/accounts/{username}")
    @PreAuthorize("hasAnyAuthority('r_all','r_account','r_self')")
    ResponseEntity<AccountDto> readAccount(@RequestHeader(AUTHORIZATION) String token,
                                           @PathVariable(value = "username") String username)
            throws NotFoundException;

    @DeleteMapping("/accounts")
    @PreAuthorize("hasAnyAuthority('d_all','d_accounts')")
    ResponseEntity<String> deleteAccounts(@RequestHeader(AUTHORIZATION) String token);

    @DeleteMapping("/accounts/{username}/permanent")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    ResponseEntity<String> permanentDeleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                                  @PathVariable String username);

    @DeleteMapping("/accounts/{username}")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    ResponseEntity<String> deleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                         @PathVariable String username) throws NotFoundException;

    @DeleteMapping("/accounts/self")
    @PreAuthorize("hasAnyAuthority('d_self')")
    ResponseEntity<String> deleteSelf(@RequestHeader(AUTHORIZATION) String token) throws NotFoundException;

    @PostMapping("/accounts/registration")
    ResponseEntity<AccountDto> createAccount(@RequestBody RegistrationDto registrationDto)
            throws AlreadyExistException, NotFoundException;

    @PostMapping("/accounts/create")
    ResponseEntity<AccountDto> createAccount(@RequestHeader(AUTHORIZATION) String token,
                                             @RequestBody RegistrationDto registrationDto)
            throws AccessDeniedException, NotFoundException, AlreadyExistException;

    @PutMapping("/accounts/change/password")
    ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto)
            throws NotFoundException;

    @PutMapping("/accounts/change/fullname")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    ResponseEntity<String> changeFullName(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestBody @Valid ChangeFullNameDto changeFullNameDto)
            throws NotFoundException;

    @PutMapping("/accounts/change/username")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    ResponseEntity<String> changeUsername(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestBody @Valid ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException;

    @PutMapping("/accounts/{username}/change/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_images','u_self')")
    ResponseEntity<String> changeImage(@RequestHeader(AUTHORIZATION) String token,
                                       @PathVariable String username, @RequestParam MultipartFile file)
            throws NotFoundException, IOException, AlreadyExistException;

    @PostMapping("/accounts/authentication/login")
    ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) throws AuthenticationException;

    @PostMapping("/accounts/authentication/logout")
    ResponseEntity<TokenDto> logout(@RequestHeader(AUTHORIZATION) String token,
                                    @RequestBody @Valid LogoutDto logoutDto);

    /*
    ###
    ###          PERMISSION CONTROLLER CALLS
    ###
     */

    @GetMapping("/permissions")
    ResponseEntity<PermissionsDto> readPermissions(@RequestHeader(AUTHORIZATION) String token,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "20") int size,
                                                   @RequestParam(defaultValue = "NONE") String direction,
                                                   @RequestParam(defaultValue = "NONE") String sortBy);

    @GetMapping("/permissions/{name}")
    ResponseEntity<PermissionDto> readPermission(@RequestHeader(AUTHORIZATION) String token,
                                                 @PathVariable String name) throws NotFoundException;

    @DeleteMapping("/permissions")
    ResponseEntity<String> deletePermissions(@RequestHeader(AUTHORIZATION) String token);

    @DeleteMapping("/permissions/{name}")
    ResponseEntity<String> deletePermission(@RequestHeader(AUTHORIZATION) String token,
                                            @PathVariable String name);

    @PostMapping("/permissions")
    ResponseEntity<PermissionDto> createPermission(@RequestHeader(AUTHORIZATION) String token,
                                                   @RequestBody CreatePermissionDto dto)
            throws AlreadyExistException;

    /*
    ###
    ###          ROLE CONTROLLER CALLS
    ###
     */

    @GetMapping("/roles")
    ResponseEntity<RolesDto> readRoles(@RequestHeader(AUTHORIZATION) String token,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size,
                                       @RequestParam(defaultValue = "NONE") String direction,
                                       @RequestParam(defaultValue = "NONE") String sortBy);

    @GetMapping("/roles/{role-name}")
    ResponseEntity<RoleDto> readRole(@RequestHeader(AUTHORIZATION) String token,
                                     @PathVariable(value = "role-name") String roleName)
            throws NotFoundException;

    @DeleteMapping("/roles")
    ResponseEntity<String> deleteRoles(@RequestHeader(AUTHORIZATION) String token);

    @DeleteMapping("/roles/{role-name}")
    ResponseEntity<String> deleteRole(@RequestHeader(AUTHORIZATION) String token,
                                      @PathVariable(value = "role-name") String roleName);

    @PostMapping("/roles")
    ResponseEntity<RoleDto> createRole(@RequestHeader(AUTHORIZATION) String token,
                                       @RequestBody CreateRoleDto createRoleDto)
            throws AlreadyExistException, NotFoundException;
}
