package org.zvv.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zvv ON 2019/4/26
 */
@Controller
public class PageController {

    @RequestMapping("index")
    String index() throws Exception {
        return "index";
    }
}
