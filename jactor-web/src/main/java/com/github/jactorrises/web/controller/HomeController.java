package com.github.jactorrises.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.github.jactorrises.web.controller.ControllerValues.VIEW_HOME;

@Controller
public class HomeController {

    @RequestMapping(value = VIEW_HOME, method = {RequestMethod.GET, RequestMethod.POST}) @SuppressWarnings("unused") // used by spring mvc
    public void doHome() { }
}