package nu.hjemme.web.dto;

import nu.hjemme.client.datatype.UserName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;

import static nu.hjemme.web.html.ParameterConstants.CHOOSE_USER;

public class UserNameDto {
    private UserName userName;

    public UserNameDto(WebRequest webRequest) {
        String name = webRequest.getParameter(CHOOSE_USER);

        if (StringUtils.isEmpty(name)) {
            return;
        }

        this.userName = new UserName(name);
    }

    public UserName getUserName() {
        return userName;
    }

    public boolean hasName() {
        return userName != null;
    }
}
