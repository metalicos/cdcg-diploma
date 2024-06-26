package ua.com.cyberdone.cloudgateway.model.accountmicroservice.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.cloudgateway.constant.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.EMAIL_PATTERN;
import static ua.com.cyberdone.cloudgateway.constant.Regex.PASSWORD_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.PASSWORD_PATTERN;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"alex2022@gmail.com\",\n" +
        "    \"password\": \"2022h@tD@g\"\n" +
        "}")
public class LoginDto {
    @Pattern(regexp = EMAIL_PATTERN,
            message = EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = PASSWORD_PATTERN,
            message = PASSWORD_FAIL_MESSAGE)
    private String password;
}
