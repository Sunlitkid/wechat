package com.sunlitkid.handler;

import com.sunlitkid.builder.ImageBuilder;
import com.sunlitkid.builder.TextBuilder;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by sunke on 2018/1/26.
 */
@Component
public class ImageHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        return new ImageBuilder().build( "mYEYWvtFU8G8sdsMPvnC0OVQPBqAePQEB2hXmHkSxF4ozkEkt6IGTGHMEGGCDVyv", wxMessage, wxMpService);
    }
}
