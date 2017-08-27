package com.github.jactorrises.web.controller;

import com.github.jactorrises.client.datatype.UserName;
import com.github.jactorrises.client.domain.User;
import com.github.jactorrises.client.facade.UserFacade;
import com.github.jactorrises.web.dto.UserDto;
import com.github.jactorrises.web.dto.UserNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

import static com.github.jactorrises.web.controller.ControllerValues.ATTRIBUTE_USER;
import static com.github.jactorrises.web.controller.ControllerValues.VIEW_USER;

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
        Optional<User> user = userFacade.findUsing(userName);

        if (!user.isPresent()) {
            return;
        }

        modelMap.put(ATTRIBUTE_USER, new UserDto(user.get()));
    }

    @Autowired
    void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
}
