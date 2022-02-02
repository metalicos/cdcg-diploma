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
import org.springframework.web.bind.annotation.RequestMapping;
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
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.token.TokenDto;

import javax.validation.Valid;
import java.io.IOException;

@FeignClient(name = "cdas", url = "${feign.cdas.url}")
@RequestMapping("/accounts")
public interface AccountMicroserviceFeignClient {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts')")
    ResponseEntity<AccountsDto> readAccounts(@RequestHeader("Authorization") String token,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int size,
                                             @RequestParam(defaultValue = "NONE") String direction,
                                             @RequestParam(defaultValue = "NONE") String sortBy)
            throws NotFoundException;

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('r_all','r_account','r_self')")
    ResponseEntity<AccountDto> readAccount(@RequestHeader("Authorization") String token,
                                           @PathVariable(value = "username") String username)
            throws NotFoundException;

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_accounts')")
    ResponseEntity<String> deleteAccounts(@RequestHeader("Authorization") String token);

    @DeleteMapping("/{username}/permanent")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    ResponseEntity<String> permanentDeleteAccount(@RequestHeader("Authorization") String token,
                                                  @PathVariable String username);

    @DeleteMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    ResponseEntity<String> deleteAccount(@RequestHeader("Authorization") String token,
                                         @PathVariable String username) throws NotFoundException;

    @DeleteMapping("/self")
    @PreAuthorize("hasAnyAuthority('d_self')")
    ResponseEntity<String> deleteSelf(@RequestHeader("Authorization") String token) throws NotFoundException;

    @PostMapping("/registration")
    ResponseEntity<AccountDto> createAccount(@RequestBody RegistrationDto registrationDto)
            throws AlreadyExistException, NotFoundException;

    @PostMapping("/create")
    ResponseEntity<AccountDto> createAccount(@RequestHeader("Authorization") String token,
                                             @RequestBody RegistrationDto registrationDto)
            throws AccessDeniedException, NotFoundException, AlreadyExistException;

    @PutMapping("/change/password")
    ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto)
            throws NotFoundException;

    @PutMapping("/change/fullname")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    ResponseEntity<String> changeFullName(@RequestHeader("Authorization") String token,
                                          @RequestBody @Valid ChangeFullNameDto changeFullNameDto)
            throws NotFoundException;

    @PutMapping("/change/username")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    ResponseEntity<String> changeUsername(@RequestHeader("Authorization") String token,
                                          @RequestBody @Valid ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException;

    @PutMapping("/{username}/change/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_images','u_self')")
    ResponseEntity<String> changeImage(@RequestHeader("Authorization") String token,
                                       @PathVariable String username, @RequestParam MultipartFile file)
            throws NotFoundException, IOException, AlreadyExistException;

    @PostMapping("/authentication/login")
    ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) throws AuthenticationException;

    @PostMapping("/authentication/logout")
    ResponseEntity<TokenDto> logout(@RequestHeader("Authorization") String token,
                                    @RequestBody @Valid LogoutDto logoutDto);

}
