package ua.com.cyberdone.cloudgateway.model.accountmicroservice.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.cloudgateway.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"oldEmail\": \"old2022@gmail.com\",\n" +
        "    \"newEmail\": \"newone2022@gmail.com\"\n" +
        "}")
public class ChangeEmailDto {
    @Pattern(regexp = Regex.EMAIL_RGX,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String oldEmail;

    @Pattern(regexp = Regex.EMAIL_RGX,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String newEmail;
}
