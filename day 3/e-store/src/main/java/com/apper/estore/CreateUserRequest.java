package com.apper.estore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "email is required")
    // @Email mag-eerror kasi hindi valid ang jdoeapper.com kasi walang @
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "'password' is required")
    @Size(min = 8, message = "'password must be at least 8 characters")
    private String password;

    // LAGAY NG JSONPROPERTY kasi hindi sila same ng KEY.
    // POSTMAN
    // POST localhost:9090/user
    // {
    //    "email": "jdoeapper.com",
    //    "password": "123qw121e",
    //    "first_name": "John",
    //    "middle_name": "Felix",
    //    "last_name": "Doe",
    //    "birth_date": "2000-10-10"
    //}
    //@NotBlank -- para i-require yung field
    //@NotNull -- combination ng NotBlank and NotEmpty

    @JsonProperty("first_name")
    @NotBlank(message = "'first_name' is required")
    private String firstName;

    @JsonProperty("middle_name")
    @NotBlank
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "'last_name' is required")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "'Birth date' is required")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "'birth_date' must be in the format YYYY-MM-DD")
    private String birthDate;
}