package ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.cloudgateway.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"name\": \"Read Notes\",\n" +
        "    \"value\": \"r_notes\"\n" +
        "}")
public class CreatePermissionDto {
    @Pattern(regexp = Regex.PERMISSION_NAME_PATTERN,
            message = Regex.PERMISSION_NAME_FAIL_MESSAGE)
    private String name;
    @Pattern(regexp = Regex.PERMISSION_VALUE_PATTERN,
            message = Regex.PERMISSION_VALUE_FAIL_MESSAGE)
    private String value;
}
