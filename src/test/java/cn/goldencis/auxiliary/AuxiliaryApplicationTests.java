package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import org.apache.commons.exec.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class AuxiliaryApplicationTests {

    @Test
    void contextLoads() {
//        String logContent = "2023-06-19 14:28:56,368 ERROR [http-nio-8790-exec-6] MonitorLatestDataService: \n" + "org.springframework.jdbc.BadSqlGrammarException: \n" + "### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'filed1' in 'where clause'\n" + "### The error may exist in class path resource [mappers/DiagnosisRecordDOMapper.xml]\n" + "### The error may involve defaultParameterMap\n" + "### The error occurred while setting parameters\n" + "### SQL: SELECT * FROM `t_diagnosis_record`       WHERE         create_time IS NOT NULL         AND filed1 IS NOT NULL         AND asset_id = ?       ORDER BY         create_time DESC\n" + "### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'filed1' in 'where clause'\n" + "; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'filed1' in 'where clause'\n" + "\tat org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:231)\n" + "\tat org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:73)\n" + "\tat org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:75)\n" + "\tat org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:447)\n" + "\tat com.sun.proxy.$Proxy130.selectList(Unknown Source)\n" + "\tat org.mybatis.spring.SqlSessionTemplate.selectList(SqlSessionTemplate.java:231)\n" + "\tat org.apache.ibatis.binding.MapperMethod.executeForMany(MapperMethod.java:137)\n" + "\tat org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:75)\n" + "\tat org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)\n" + "\tat com.sun.proxy.$Proxy157.selectDiagnosisRecordDOWithLaLatest(Unknown Source)\n" + "\tat cn.goldencis.vops.monitorhub.service.MonitorLatestDataService.getDiagnosticResults(MonitorLatestDataService.java:1772)\n" + "\tat cn.goldencis.vops.monitorhub.service.MonitorLatestDataService.lambda$getCameraAssetList$19(MonitorLatestDataService.java:864)\n" + "\tat java.util.ArrayList.forEach(ArrayList.java:1257)\n" + "\tat cn.goldencis.vops.monitorhub.service.MonitorLatestDataService.getCameraAssetList(MonitorLatestDataService.java:779)\n" + "\tat cn.goldencis.vops.monitorhub.service.MonitorLatestDataService$$FastClassBySpringCGLIB$$de603f51.invoke(<generated>)\n" + "\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n" + "\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:667)\n" + "\tat cn.goldencis.vops.monitorhub.service.MonitorLatestDataService$$EnhancerBySpringCGLIB$$181efb5a.getCameraAssetList(<generated>)\n" + "\tat cn.goldencis.vops.monitorhub.controller.MonitorCameraAndEquipmentBoxController.getCameraList(MonitorCameraAndEquipmentBoxController.java:143)\n" + "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" + "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\n" + "\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\n" + "\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n" + "\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n" + "\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n" + "\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n" + "\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n" + "\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n" + "\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n" + "\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:861)\n" + "\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:687)\n" + "\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n" + "\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:790)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat com.codahale.metrics.servlet.AbstractInstrumentedFilter.doFilter(AbstractInstrumentedFilter.java:104)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.boot.web.filter.ApplicationContextHeaderFilter.doFilterInternal(ApplicationContextHeaderFilter.java:55)\n" + "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.boot.actuate.trace.WebRequestTraceFilter.doFilterInternal(WebRequestTraceFilter.java:111)\n" + "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:208)\n" + "\tat org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:177)\n" + "\tat org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:347)\n" + "\tat org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:263)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n" + "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n" + "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n" + "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n" + "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.springframework.boot.actuate.autoconfigure.MetricsFilter.doFilterInternal(MetricsFilter.java:106)\n" + "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" + "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" + "\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n" + "\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n" + "\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n" + "\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n" + "\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n" + "\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n" + "\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n" + "\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n" + "\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n" + "\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790)\n" + "\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1468)\n" + "\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n" + "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" + "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" + "\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n" + "\tat java.lang.Thread.run(Thread.java:748)\n" + "Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'filed1' in 'where clause'\n" + "\tat sun.reflect.GeneratedConstructorAccessor140.newInstance(Unknown Source)\n" + "\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n" + "\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n" + "\tat com.mysql.jdbc.Util.handleNewInstance(Util.java:425)\n" + "\tat com.mysql.jdbc.Util.getInstance(Util.java:408)\n" + "\tat com.mysql.jdbc.SQLError.createSQLException(SQLError.java:944)\n" + "\tat com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3976)\n" + "\tat com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3912)\n" + "\tat com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2530)\n" + "\tat com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2683)\n" + "\tat com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2486)\n" + "\tat com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1858)\n" + "\tat com.mysql.jdbc.PreparedStatement.execute(PreparedStatement.java:1197)\n" + "\tat sun.reflect.GeneratedMethodAccessor254.invoke(Unknown Source)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat org.apache.tomcat.jdbc.pool.StatementFacade$StatementProxy.invoke(StatementFacade.java:114)\n" + "\tat com.sun.proxy.$Proxy174.execute(Unknown Source)\n" + "\tat org.apache.ibatis.executor.statement.PreparedStatementHandler.query(PreparedStatementHandler.java:63)\n" + "\tat org.apache.ibatis.executor.statement.RoutingStatementHandler.query(RoutingStatementHandler.java:79)\n" + "\tat org.apache.ibatis.executor.SimpleExecutor.doQuery(SimpleExecutor.java:63)\n" + "\tat org.apache.ibatis.executor.BaseExecutor.queryFromDatabase(BaseExecutor.java:324)\n" + "\tat org.apache.ibatis.executor.BaseExecutor.query(BaseExecutor.java:156)\n" + "\tat org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:109)\n" + "\tat org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:83)\n" + "\tat org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:148)\n" + "\tat org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:141)\n" + "\tat sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:434)\n" + "\t... 90 common frames omitted\n" + "2023-06-19 14:32:50,005 ERROR [pool-1-thread-3] SubscriberStarter: Fail to handle message\n" + "java.lang.reflect.InvocationTargetException: null\n" + "\tat sun.reflect.GeneratedMethodAccessor580.invoke(Unknown Source)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat cn.goldencis.gdcontainer.framework.message.annotation.SubscriberStarter$1.handle(SubscriberStarter.java:130)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.doDispatch(MessageDispatcher.java:53)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.lambda$onMessage$0(MessageDispatcher.java:59)\n" + "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" + "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" + "\tat java.lang.Thread.run(Thread.java:748)\n" + "Caused by: java.lang.RuntimeException: Value for filed1 cannot be null\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.addCriterion(DiagnosisProgressDOCriteria.java:96)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:439)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$Criteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:859)\n" + "\tat cn.goldencis.vops.monitorhub.message.DiagnosisProgressSubscriber.handleDiagnoseProgressData(DiagnosisProgressSubscriber.java:34)\n" + "\t... 9 common frames omitted\n" + "2023-06-19 14:33:00,006 ERROR [pool-1-thread-2] SubscriberStarter: Fail to handle message\n" + "java.lang.reflect.InvocationTargetException: null\n" + "\tat sun.reflect.GeneratedMethodAccessor580.invoke(Unknown Source)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat cn.goldencis.gdcontainer.framework.message.annotation.SubscriberStarter$1.handle(SubscriberStarter.java:130)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.doDispatch(MessageDispatcher.java:53)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.lambda$onMessage$0(MessageDispatcher.java:59)\n" + "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" + "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" + "\tat java.lang.Thread.run(Thread.java:748)\n" + "Caused by: java.lang.RuntimeException: Value for filed1 cannot be null\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.addCriterion(DiagnosisProgressDOCriteria.java:96)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:439)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$Criteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:859)\n" + "\tat cn.goldencis.vops.monitorhub.message.DiagnosisProgressSubscriber.handleDiagnoseProgressData(DiagnosisProgressSubscriber.java:34)\n" + "\t... 9 common frames omitted\n" + "2023-06-19 14:33:10,020 ERROR [pool-1-thread-5] SubscriberStarter: Fail to handle message\n" + "java.lang.reflect.InvocationTargetException: null\n" + "\tat sun.reflect.GeneratedMethodAccessor580.invoke(Unknown Source)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat cn.goldencis.gdcontainer.framework.message.annotation.SubscriberStarter$1.handle(SubscriberStarter.java:130)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.doDispatch(MessageDispatcher.java:53)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.lambda$onMessage$0(MessageDispatcher.java:59)\n" + "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" + "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" + "\tat java.lang.Thread.run(Thread.java:748)\n" + "Caused by: java.lang.RuntimeException: Value for filed1 cannot be null\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.addCriterion(DiagnosisProgressDOCriteria.java:96)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:439)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$Criteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:859)\n" + "\tat cn.goldencis.vops.monitorhub.message.DiagnosisProgressSubscriber.handleDiagnoseProgressData(DiagnosisProgressSubscriber.java:34)\n" + "\t... 9 common frames omitted\n" + "2023-06-19 14:33:20,005 ERROR [pool-1-thread-1] SubscriberStarter: Fail to handle message\n" + "java.lang.reflect.InvocationTargetException: null\n" + "\tat sun.reflect.GeneratedMethodAccessor580.invoke(Unknown Source)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat cn.goldencis.gdcontainer.framework.message.annotation.SubscriberStarter$1.handle(SubscriberStarter.java:130)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.doDispatch(MessageDispatcher.java:53)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.lambda$onMessage$0(MessageDispatcher.java:59)\n" + "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" + "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" + "\tat java.lang.Thread.run(Thread.java:748)\n" + "Caused by: java.lang.RuntimeException: Value for filed1 cannot be null\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.addCriterion(DiagnosisProgressDOCriteria.java:96)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:439)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$Criteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:859)\n" + "\tat cn.goldencis.vops.monitorhub.message.DiagnosisProgressSubscriber.handleDiagnoseProgressData(DiagnosisProgressSubscriber.java:34)\n" + "\t... 9 common frames omitted\n" + "2023-06-19 14:33:30,006 ERROR [pool-1-thread-3] SubscriberStarter: Fail to handle message\n" + "java.lang.reflect.InvocationTargetException: null\n" + "\tat sun.reflect.GeneratedMethodAccessor580.invoke(Unknown Source)\n" + "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" + "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" + "\tat cn.goldencis.gdcontainer.framework.message.annotation.SubscriberStarter$1.handle(SubscriberStarter.java:130)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.doDispatch(MessageDispatcher.java:53)\n" + "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.lambda$onMessage$0(MessageDispatcher.java:59)\n" + "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" + "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" + "\tat java.lang.Thread.run(Thread.java:748)\n" + "Caused by: java.lang.RuntimeException: Value for filed1 cannot be null\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.addCriterion(DiagnosisProgressDOCriteria.java:96)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:439)\n" + "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$Criteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:859)\n" + "\tat cn.goldencis.vops.monitorhub.message.DiagnosisProgressSubscriber.handleDiagnoseProgressData(DiagnosisProgressSubscriber.java:34)\n" + "\t... 9 common frames omitted";
        String logContent = "2023-06-15 02:01:00,007 ERROR [pool-1-thread-3] SubscriberStarter: Fail to handle message\n" +
                "java.lang.reflect.InvocationTargetException: null\n" +
                "\tat sun.reflect.GeneratedMethodAccessor540.invoke(Unknown Source)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "\tat cn.goldencis.gdcontainer.framework.message.annotation.SubscriberStarter$1.handle(SubscriberStarter.java:130)\n" +
                "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.doDispatch(MessageDispatcher.java:53)\n" +
                "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.lambda$onMessage$0(MessageDispatcher.java:59)\n" +
                "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" +
                "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" +
                "\tat java.lang.Thread.run(Thread.java:748)\n" +
                "Caused by: java.lang.RuntimeException: Value for filed1 cannot be null\n" +
                "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.addCriterion(DiagnosisProgressDOCriteria.java:96)\n" +
                "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:439)\n" +
                "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$Criteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:859)\n" +
                "\tat cn.goldencis.vops.monitorhub.message.DiagnosisProgressSubscriber.handleDiagnoseProgressData(DiagnosisProgressSubscriber.java:34)\n" +
                "\t... 9 common frames omitted\n" +
                "2023-06-15 02:01:10,024 ERROR [pool-1-thread-1] SubscriberStarter: Fail to handle message\n" +
                "java.lang.reflect.InvocationTargetException: null\n" +
                "\tat sun.reflect.GeneratedMethodAccessor540.invoke(Unknown Source)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "\tat cn.goldencis.gdcontainer.framework.message.annotation.SubscriberStarter$1.handle(SubscriberStarter.java:130)\n" +
                "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.doDispatch(MessageDispatcher.java:53)\n" +
                "\tat cn.goldencis.gdcontainer.framework.message.mq.MessageDispatcher.lambda$onMessage$0(MessageDispatcher.java:59)\n" +
                "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" +
                "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" +
                "\tat java.lang.Thread.run(Thread.java:748)\n" +
                "Caused by: java.lang.RuntimeException: Value for filed1 cannot be null\n" +
                "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.addCriterion(DiagnosisProgressDOCriteria.java:96)\n" +
                "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$GeneratedCriteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:439)\n" +
                "\tat cn.goldencis.vops.monitorhub.entity.DiagnosisProgressDOCriteria$Criteria.andFiled1EqualTo(DiagnosisProgressDOCriteria.java:859)\n" +
                "\tat cn.goldencis.vops.monitorhub.message.DiagnosisProgressSubscriber.handleDiagnoseProgressData(DiagnosisProgressSubscriber.java:34)\n" +
                "\t... 9 common frames omitted\n";
        List<String> errorList = new ArrayList<>();
        int index = 0;
        String regex = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2},\\d{3}\\sERROR";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            int startIndex = matcher.start();
            System.out.println(logContent.substring(index, startIndex));
            index = startIndex;
        }
        System.out.println(logContent.substring(index, logContent.length()));
    }

    @Test
    public void test2() throws IOException {
        CommandLine cmdLine = CommandLine.parse("mysql");
        cmdLine.addArgument("-uroot");
        cmdLine.addArgument("-pgoldencis");
        cmdLine.addArgument("-e");
        cmdLine.addArgument("DROP");
        cmdLine.addArgument("TABLE");
        cmdLine.addArgument("IF");
        cmdLine.addArgument("EXISTS");
        cmdLine.addArgument("vops_config_hub.t_ntp_config");
        // 创建执行器对象
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
        executor.setWatchdog(watchdog);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);
        try {
            int execute = executor.execute(cmdLine);
            String output = outputStream.toString();
            System.out.printf(output);
        } catch (ExecuteException e) {
            System.err.println("Command execution failed: " + e.getMessage());
            String output = outputStream.toString();
        } catch (IOException e) {
            System.err.println("Command execution failed: " + e.getMessage());
        }
    }

    @Test
    public void test3() {
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"mysql", "-uroot", "-pgoldencis", "-e", "DROP TABLE IF EXISTS vops_config_hub.t_ntp_config"});
            BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(exec.getOutputStream())), true);
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void subString() {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        String text = "mysql -uroot \"ss\" -pgoldencis  -e \"DROP TABLE IF EXISTS vops_config_hub.t_ntp_config\" \"sss\"";
        Matcher matcher = pattern.matcher(text);
        int lastIndex = 0;
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            if (lastIndex < matchStart) {
                // 将两个匹配之间的非匹配文本添加到结果中
                String nonMatchText = text.substring(lastIndex, matchStart);
                result.add(nonMatchText);
            }
            // 将匹配的文本添加到结果中
            String quotedText = text.substring(matchStart, matchEnd);
            result.add(quotedText);
            lastIndex = matchEnd;
        }
        if (lastIndex < text.length()) {
            // 将最后一个匹配后的文本添加到结果中
            String nonMatchText = text.substring(lastIndex);
            result.add(nonMatchText);
        }

    }

    @Test
    public void getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        System.out.println("当前日期：" + formattedDate);
    }

    @Test
    public void command() {
        String logContent = CommandUtil.bashExecute("cat /gdsoft/soft/vops/logs/vops-config-hub-error.2023-07-06.log").getMessage();
        if (logContent == null) {
            System.out.printf("空\n");
        } else {
            System.out.printf(logContent);
        }
    }

    @Test
    public void execute3() {
        String filePath = "/gdsoft/soft/vops/logs/vops-config-hub-error.2023-07-03.log";
        String searchString = "ss";
        try {
            String[] cmd = new String[]{"bash", "-c", "cat /gdsoft/soft/vops/logs/viid.2023-06-19.log"};
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tests() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/gdsoft/soft/vops/logs/vops-config-hub-error.2023-07-06.log"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }



}
