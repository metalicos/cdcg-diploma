package ua.com.cyberdone.cloudgateway.controller.account;

import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.cloudgateway.constant.Regex;
import ua.com.cyberdone.cloudgateway.documentation.account.AccountApi;
import ua.com.cyberdone.cloudgateway.exception.AccessDeniedException;
import ua.com.cyberdone.cloudgateway.exception.AlreadyExistException;
import ua.com.cyberdone.cloudgateway.exception.AuthenticationException;
import ua.com.cyberdone.cloudgateway.exception.NotFoundException;
import ua.com.cyberdone.cloudgateway.feign.AccountMicroserviceFeignClient;
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
import javax.validation.constraints.Pattern;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController implements AccountApi {
    private final AccountMicroserviceFeignClient accountFeignClient;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts')")
    public ResponseEntity<AccountsDto> readAccounts(@RequestHeader(AUTHORIZATION) String token,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int size,
                                                    @RequestParam(defaultValue = "NONE") String direction,
                                                    @RequestParam(defaultValue = "NONE") String sortBy)
            throws NotFoundException {
        return accountFeignClient.readAccounts(token, page, size, direction, sortBy);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('r_all','r_account','r_self')")
    public ResponseEntity<AccountDto> readAccount(@RequestHeader(AUTHORIZATION) String token,
                                                  @PathVariable(value = "username") String username)
            throws NotFoundException {
        return accountFeignClient.readAccount(token, username);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_accounts')")
    public ResponseEntity<String> deleteAccounts(@RequestHeader(AUTHORIZATION) String token) {
        return accountFeignClient.deleteAccounts(token);
    }

    @DeleteMapping("/{username}/permanent")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    public ResponseEntity<String> permanentDeleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                                         @PathVariable String username) {
        return accountFeignClient.permanentDeleteAccount(token, username);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    public ResponseEntity<String> deleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                                @PathVariable String username) throws NotFoundException {
        return accountFeignClient.deleteAccount(token, username);
    }

    @DeleteMapping("/self")
    @PreAuthorize("hasAnyAuthority('d_self')")
    public ResponseEntity<String> deleteSelf(@RequestHeader(AUTHORIZATION) String token) throws NotFoundException {
        return accountFeignClient.deleteSelf(token);
    }

    @PostMapping("/registration")
    public ResponseEntity<AccountDto> createAccount(@RequestBody RegistrationDto registrationDto)
            throws AlreadyExistException, NotFoundException {
        return accountFeignClient.createAccount(registrationDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestHeader(AUTHORIZATION) String token,
                                                    @RequestBody RegistrationDto registrationDto)
            throws AccessDeniedException, NotFoundException, AlreadyExistException {
        return accountFeignClient.createAccount(token, registrationDto);
    }

    @PutMapping("/change/password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto)
            throws NotFoundException {
        return accountFeignClient.changePassword(changePasswordDto);
    }

    @PutMapping("/change/fullname")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    public ResponseEntity<String> changeFullName(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestBody @Valid ChangeFullNameDto changeFullNameDto)
            throws NotFoundException {
        return accountFeignClient.changeFullName(token, changeFullNameDto);
    }

    @PutMapping("/change/username")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    public ResponseEntity<String> changeUsername(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestBody @Valid ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException {
        return accountFeignClient.changeUsername(token, changeEmailDto);
    }

    @PutMapping("/{username}/change/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_images','u_self')")
    public ResponseEntity<String> changeImage(@RequestHeader(AUTHORIZATION) String token,
                                              @Pattern(regexp = Regex.EMAIL, message = Regex.EMAIL_FAIL_MESSAGE)
                                              @PathVariable String username, @RequestParam MultipartFile file)
            throws NotFoundException, IOException, AlreadyExistException {
        return accountFeignClient.changeImage(token, username, file);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) throws AuthenticationException {
        return accountFeignClient.login(loginDto);
    }

    @PostMapping("/authentication/logout")
    public ResponseEntity<TokenDto> logout(@RequestHeader(AUTHORIZATION) String token,
                                           @RequestBody @Valid LogoutDto logoutDto) {
        return accountFeignClient.logout(token, logoutDto);
    }
}
