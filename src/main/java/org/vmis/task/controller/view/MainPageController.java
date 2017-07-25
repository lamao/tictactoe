package org.vmis.task.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Controller
@RequestMapping("/")
public class MainPageController {

    private static final String VIEW_NAME_INDEX = "/index.html";

    @GetMapping
    public ModelAndView getMainPage() {
        return new ModelAndView(VIEW_NAME_INDEX);
    }
}
