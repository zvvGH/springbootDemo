package org.zvv.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zvv.demo.annotation.ApiVersion;

/**
 * Created by Zvv ON 2019/4/24
 */
@ApiVersion(1)
@RestController("{version}/dd")
public class HelloController {

    @RequestMapping("/hello")
    String hello() throws Exception {
        return "Hello!";
    }

    @RequestMapping("exception")
    void exception() throws Exception {
        throw new Exception("手动异常测试");
    }

}
