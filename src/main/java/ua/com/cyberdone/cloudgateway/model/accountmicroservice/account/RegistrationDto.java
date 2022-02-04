package ua.com.cyberdone.cloudgateway.model.accountmicroservice.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.cloudgateway.constant.Regex;

import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"firstName\": \"Bearny\",\n" +
        "    \"lastName\": \"Pritrix\",\n" +
        "    \"patronymic\": \"Adams son\",\n" +
        "    \"username\": \"b.pritrix@gmail.com\",\n" +
        "    \"password\": \"22Rtytr#$\",\n" +
        "    \"roles\": [\n" +
        "        \"ADMIN\",\n" +
        "        \"MANAGER\"\n" +
        "    ]\n" +
        "}")
public class RegistrationDto {
    @Pattern(regexp = Regex.EMAIL_RGX,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;
    @Pattern(regexp = Regex.PASSWORD_RGX,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    private String password;
    @Pattern(regexp = Regex.NAME_RGX,
            message = Regex.FIRST_NAME_FAIL_MESSAGE)
    private String firstName;
    @Pattern(regexp = Regex.NAME_RGX,
            message = Regex.LAST_NAME_FAIL_MESSAGE)
    private String lastName;
    @Pattern(regexp = Regex.NAME_RGX,
            message = Regex.PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
    private Set<String> roles;
}
