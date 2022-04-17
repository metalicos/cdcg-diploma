package ua.com.cyberdone.cloudgateway.model.accountmicroservice.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Set;

import static ua.com.cyberdone.cloudgateway.constant.Regex.PERMISSION_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.PERMISSION_NAME_PATTERN;
import static ua.com.cyberdone.cloudgateway.constant.Regex.ROLE_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.ROLE_NAME_PATTERN;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"role\": \"DRIVER\",\n" +
        "    \"permissionNames\": [\n" +
        "        \"Read Directions\"\n" +
        "    ]\n" +
        "}")
public class CreateRoleDto {
    @Pattern(regexp = ROLE_NAME_PATTERN, message = ROLE_NAME_FAIL_MESSAGE)
    private String role;
    private Set<@Pattern(regexp = PERMISSION_NAME_PATTERN, message = PERMISSION_NAME_FAIL_MESSAGE) String> permissionNames;
}
