package com.sunlitkid.controller;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunke on 2018/1/25.
 */
@Controller
@RequestMapping("/portal")
public class CenterController {
    @Autowired
    private WxMpService wxService;
    @Autowired
    private WxMpMessageRouter router;
    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {
        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
            return  echostr;
        }
        return "false";
    }
    @ResponseBody
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
                       @RequestParam(name = "signature", required = false) String signature,
                       @RequestParam(name = "timestamp", required = false) String timestamp,
                       @RequestParam(name = "nonce", required = false) String nonce) {
        System.out.println(requestBody);
        WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
        System.out.println(signature+ " "+timestamp+ " "+nonce);
        System.out.println(this.wxService.checkSignature(timestamp, nonce, signature));
        System.out.println(inMessage);
        if(!this.wxService.checkSignature(timestamp, nonce, signature)){
            return  "";
        }
        return  this.router.route(inMessage).toXml();
    }
}
