package cn.goldencis.auxiliary.userinterfaces;

import cn.goldencis.auxiliary.application.errorfix.ErrorFixService;
import cn.goldencis.auxiliary.domain.loginfo.ErrorInfoDispatcher;
import cn.goldencis.auxiliary.infrastructure.extract.service.LogExtract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ErrorFixService errorFixService;
    @Autowired
    ErrorInfoDispatcher dispatcher;
    @Autowired
    LogExtract logExtract;

    @GetMapping("/logtest")
    public String get(String file){
        logExtract.extract("", file, null);
        return null;
    }
    @GetMapping("/problem")
    public String prblem(String file) throws InterruptedException {
         dispatcher.takeErrorInfo();
         return null;
    }
}
