package org.zvv.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Zvv ON 2019/4/25
 */
@Slf4j
@Aspect
@Component
public class WebExceptionAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void webPointcut() {
    }

    @AfterThrowing(pointcut = "webPointcut()", throwing = "e")
    private void afterThrowing(Exception e) throws Throwable {
        log.info("Exception 来了");
        if (!StringUtils.isEmpty(e.getMessage())) {
            writeContent(e.getMessage());
        } else {
            writeContent("出异常啦");
        }
    }

    private void writeContent(String str) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(StringUtils.isEmpty(str) ? "" : str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
