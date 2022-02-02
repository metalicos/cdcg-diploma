package ua.com.cyberdone.cloudgateway.model.accountmicroservice.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.cloudgateway.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"roger@gmail.com\",\n" +
        "    \"newPassword\": \"!@#333rtrIODone\",\n" +
        "    \"checkNewPassword\": \"!@#333rtrIODone\"\n" +
        "}")
public class ChangePasswordDto {
    @Pattern(regexp = Regex.EMAIL,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = Regex.PASSWORD,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    private String newPassword;

    @Pattern(regexp = Regex.PASSWORD,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    private String checkNewPassword;
}
