package com.example.user_service.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(description = "dto to update password")
@Data
@Builder
@AllArgsConstructor
public class ChangePasswordInDto {

    @Schema (description = "old password")
    @NotBlank(message = "old password is empty")
    private String oldPassword;

    @Schema (description = "new password")
    @NotBlank(message = "new password is empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%#&*@]).{5,}$", message = "password is not valid")
    @Size(min = 5)
    private String newPassword;
}
