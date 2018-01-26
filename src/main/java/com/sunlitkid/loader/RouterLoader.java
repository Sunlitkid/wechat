package com.sunlitkid.loader;

import com.sunlitkid.configuration.WechatConfiguration;
import com.sunlitkid.handler.ImageHandler;
import com.sunlitkid.handler.LogHandler;
import com.sunlitkid.handler.MsgHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sunke on 2018/1/26.
 */
@Configuration
@ConditionalOnClass(WxMpMessageRouter.class)
public class RouterLoader {
    @Autowired
    LogHandler logHandler;
    @Autowired
    MsgHandler msgHandler;
    @Autowired
    ImageHandler imageHandler;
    @Bean
    public WxMpMessageRouter router(WxMpService wxMpService) {
        final WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        router.rule().handler(this.logHandler).next();
        // 默认
        router.rule().async(false).msgType("image").handler(imageHandler).end();

        router.rule().async(false).handler(this.msgHandler).end();

        return router;
    }
}
