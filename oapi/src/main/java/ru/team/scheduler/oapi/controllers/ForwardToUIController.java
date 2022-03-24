package ru.team.scheduler.oapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardToUIController {

    @RequestMapping(value = {"/{:^(?!.*?api).*$}/{[path:[^.]*}"
            , "login"
            , "users"
            , "lessons"
            , "exercises"
            , "disciplines"
            , "profile"
            , "registration"
            , "scheduler"
            , "tables"})
    public String goToUI() {
        // Forward to home page so that route is preserved.
        return "forward:/";
    }
}
