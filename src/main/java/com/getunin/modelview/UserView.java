
package com.getunin.modelview;
import com.getunin.entity.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserView {

    private Long id;
    private Parameter identificationType;
    private String identification;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Parameter roleId;
    private Parameter status;

}
