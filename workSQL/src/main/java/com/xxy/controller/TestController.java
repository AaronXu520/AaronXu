package com.xxy.controller;

import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.xxy.mapper.UrlMapper;
import com.xxy.pojo.JdbcOperation;
import com.xxy.pojo.sharedData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.resource.HttpResource;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Api(value="源地址controller",tags={"用户操作"})
class TestController {

    protected  final String prefix="localhost:8080/get/";
    public static Map<String, String> sourceShortMap = new ConcurrentHashMap<String, String>();
    public static Map<String, String> shortSourceMap = new ConcurrentHashMap<String, String>();

    //@Autowired
    @ApiOperation(value = "获取对应的短地址", notes = "点击 try it out ")
    @RequestMapping(value = "/getOrder",method = RequestMethod.GET)
    @ResponseBody
    public String getOrder(@ApiParam(value = "源地址",required = true) @RequestParam(value = "源地址url") String  source){

            sharedData.sourceUrl=source;
            String s="";
            while(true){
                if(!sourceShortMap.containsKey(source)){
                    String[] shortUrl = pojo.ShortUrlGenerator.shortUrl(sharedData.sourceUrl);

                    for (int i = 0; i < shortUrl.length; i++) {

                        s+=shortUrl[i];

                    }
                    System.out.println(s);
                    sourceShortMap.put(source,s);
                    shortSourceMap.put(s,source);
                    break;
                }
            }
            return prefix+s;
        // urlMapper.GetSourceUrl(source);
    }

    @ApiIgnore
    @RequestMapping("/get/{shorturl}")
    public String RealizeRedirect(@PathVariable String shorturl){
        String redirect = shortSourceMap.get(shorturl);
        if(redirect==null){
            return "error.html";
        }
        else {
            System.out.println(new JdbcOperation().GetSourceUrl(shorturl));
            return "redirect:http://"+redirect;
        }
    }
}

