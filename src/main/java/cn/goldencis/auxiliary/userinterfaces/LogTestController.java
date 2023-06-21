package cn.goldencis.auxiliary.userinterfaces;

import cn.goldencis.auxiliary.application.extract.service.LogExtract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 16:18
 **/
@RestController
@Slf4j
@RequestMapping("/auxiliary")
public class LogTestController {

    @GetMapping("/logtest")
    public String get(String file){
        LogExtract logExtract = new LogExtract();
        logExtract.extract(file);
        return null;
    }
}
