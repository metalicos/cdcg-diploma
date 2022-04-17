package ua.com.cyberdone.cloudgateway.model.accountmicroservice.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.cloudgateway.constant.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.EMAIL_PATTERN;
import static ua.com.cyberdone.cloudgateway.constant.Regex.FIRST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.LAST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.NAME_PATTERN;
import static ua.com.cyberdone.cloudgateway.constant.Regex.PATRONYMIC_FAIL_MESSAGE;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"alex@gmail.com\",\n" +
        "    \"firstName\": \"Alex\",\n" +
        "    \"lastName\": \"Breyks\",\n" +
        "    \"patronymic\": \"D'Amanti\"\n" +
        "}")
public class ChangeFullNameDto {
    @Pattern(regexp = EMAIL_PATTERN,
            message = EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = NAME_PATTERN,
            message = FIRST_NAME_FAIL_MESSAGE)
    private String firstName;

    @Pattern(regexp = NAME_PATTERN,
            message = LAST_NAME_FAIL_MESSAGE)
    private String lastName;

    @Pattern(regexp = NAME_PATTERN,
            message = PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
}
