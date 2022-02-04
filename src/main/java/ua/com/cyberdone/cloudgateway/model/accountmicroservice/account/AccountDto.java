package ua.com.cyberdone.cloudgateway.model.accountmicroservice.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.cyberdone.cloudgateway.constant.Regex;
import ua.com.cyberdone.cloudgateway.model.accountmicroservice.role.RoleDto;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"id\": 1,\n" +
        "    \"username\": \"ostap.ja@gmail.com\",\n" +
        "    \"firstName\": \"Ostap\",\n" +
        "    \"lastName\": \"Komplikevych\",\n" +
        "    \"patronymic\": \"Yaroslavovych\",\n" +
        "    \"isEnabled\": true,\n" +
        "    \"roles\": [\n" +
        "        {\n" +
        "            \"id\": 1,\n" +
        "            \"role\": \"OWNER\",\n" +
        "            \"permissions\": [\n" +
        "                {\n" +
        "                    \"id\": 3,\n" +
        "                    \"name\": \"Update All\",\n" +
        "                    \"value\": \"u_all\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"id\": 4,\n" +
        "                    \"name\": \"Delete All\",\n" +
        "                    \"value\": \"d_all\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"id\": 1,\n" +
        "                    \"name\": \"Read All\",\n" +
        "                    \"value\": \"r_all\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"id\": 2,\n" +
        "                    \"name\": \"Write All\",\n" +
        "                    \"value\": \"w_all\"\n" +
        "                }\n" +
        "            ]\n" +
        "        }\n" +
        "    ]\n" +
        "}")
public class AccountDto implements Serializable {
    private Long id;
    @Pattern(regexp = Regex.EMAIL_RGX,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;
    @Pattern(regexp = Regex.PASSWORD_RGX,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    @JsonIgnore
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
    private Boolean isEnabled;
    @JsonIgnore
    private Byte[] photo;
    private Set<@Valid RoleDto> roles;
}
