package ua.com.cyberdone.cloudgateway.model.accountmicroservice.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.cloudgateway.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"alex@gmail.com\",\n" +
        "    \"firstName\": \"Alex\",\n" +
        "    \"lastName\": \"Breyks\",\n" +
        "    \"patronymic\": \"D'Amanti\"\n" +
        "}")
public class ChangeFullNameDto {
    @Pattern(regexp = Regex.EMAIL_RGX,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = Regex.NAME_RGX,
            message = Regex.FIRST_NAME_FAIL_MESSAGE)
    private String firstName;

    @Pattern(regexp = Regex.NAME_RGX,
            message = Regex.LAST_NAME_FAIL_MESSAGE)
    private String lastName;

    @Pattern(regexp = Regex.NAME_RGX,
            message = Regex.PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
}
