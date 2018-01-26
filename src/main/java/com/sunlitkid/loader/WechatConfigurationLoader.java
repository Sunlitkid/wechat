package com.sunlitkid.loader;

import com.sunlitkid.configuration.WechatConfiguration;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sunke on 2018/1/26.
 */
@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WechatConfiguration.class)
public class WechatConfigurationLoader {
    @Autowired
    WechatConfiguration wechatConfiguration;
    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService() {
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.okhttp.WxMpServiceImpl();
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.jodd.WxMpServiceImpl();
//        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.apache.WxMpServiceImpl();
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(this.wechatConfiguration.getAppId());
        configStorage.setSecret(this.wechatConfiguration.getSecret());
        configStorage.setToken(this.wechatConfiguration.getToken());
        configStorage.setAesKey(this.wechatConfiguration.getAesKey());
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }
}
