package nu.hjemme.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static nu.hjemme.web.controller.ControllerValues.VIEW_ABOUT;

@Controller
/** @author Tor Egil Jacobsen */
public class AboutController {

    @RequestMapping(value = VIEW_ABOUT, method = {RequestMethod.GET, RequestMethod.POST})
    public void doAbout() {
    }
}
