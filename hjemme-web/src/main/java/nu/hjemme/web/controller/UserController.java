package nu.hjemme.web.controller;

import nu.hjemme.client.UserFacade;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.web.dto.UserDto;
import nu.hjemme.web.dto.UserNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import static nu.hjemme.web.controller.ControllerValues.ATTRIBUTE_USER;
import static nu.hjemme.web.controller.ControllerValues.VIEW_USER;

@Controller
public class UserController {

    private UserFacade userFacade;

    @RequestMapping(value = VIEW_USER, method = {RequestMethod.GET, RequestMethod.POST})
    public void doUser(ModelMap modelMap, WebRequest webRequest) {
        resolveUser(modelMap, webRequest);
    }

    private void resolveUser(ModelMap modelMap, WebRequest webRequest) {
        UserNameDto userNameDto = new UserNameDto(webRequest);

        if (!userNameDto.hasName()) {
            return;
        }

        UserName userName = userNameDto.getUserName();
        User user = userFacade.retrieveBy(userName);

        if (user == null) {
            return;
        }

        modelMap.put(ATTRIBUTE_USER, new UserDto(user));
    }

    @Autowired
    void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
}
