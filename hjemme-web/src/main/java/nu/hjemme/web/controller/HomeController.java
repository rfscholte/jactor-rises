package nu.hjemme.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static nu.hjemme.web.controller.ControllerValues.VIEW_HOME;

@Controller
public class HomeController {

    @RequestMapping(value = VIEW_HOME, method = {RequestMethod.GET, RequestMethod.POST})
    public void doHome() {
    }
}
