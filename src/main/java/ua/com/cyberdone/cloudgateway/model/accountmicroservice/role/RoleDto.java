package ua.com.cyberdone.cloudgateway.model.accountmicroservice.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.permission.PermissionDto;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static ua.com.cyberdone.cloudgateway.constant.Regex.ROLE_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.cloudgateway.constant.Regex.ROLE_NAME_PATTERN;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"id\": 3,\n" +
        "    \"role\": \"ADMIN\",\n" +
        "    \"permissions\": [\n" +
        "        {\n" +
        "            \"id\": 21,\n" +
        "            \"name\": \"Read Self\",\n" +
        "            \"value\": \"r_self\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 24,\n" +
        "            \"name\": \"Delete Self\",\n" +
        "            \"value\": \"d_self\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 22,\n" +
        "            \"name\": \"Write Self\",\n" +
        "            \"value\": \"w_self\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 23,\n" +
        "            \"name\": \"Update Self\",\n" +
        "            \"value\": \"u_self\"\n" +
        "        }\n" +
        "    ]\n" +
        "}")
public class RoleDto {
    private Long id;
    @Pattern(regexp = ROLE_NAME_PATTERN, message = ROLE_NAME_FAIL_MESSAGE)
    private String role;
    private Set<@Valid PermissionDto> permissions;
}
