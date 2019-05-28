package org.zvv.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zvv.demo.annotation.ApiVersion;
import org.zvv.demo.util.RedisUtil;

/**
 * Created by Zvv ON 2019/4/24
 */
@ApiVersion(1)
@RestController("{version}/dd")
public class HelloController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/hello")
    String hello(String k, String v) throws Exception {
//        redisTemplate.opsForValue().set("123","321");
        redisUtil.setString(k, v);
        return k + " == " + v;
    }

    @GetMapping("/getRedis")
    String exception(@RequestParam String key) throws Exception {
//        return (String) redisTemplate.opsForValue().get(key);
        return (String) redisUtil.getString(key);
    }

}
