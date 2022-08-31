package com.example.user_service.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class ChangePasswordInDto {
    @NotBlank(message = "email is empty")
    private String email;

    @NotBlank(message = "old password is empty")
    private String oldPassword;

    @NotBlank(message = "new password is empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%#&*@]).{5,}$", message = "password is not valid")
    @Size(min = 5)
    private String newPassword;
}
