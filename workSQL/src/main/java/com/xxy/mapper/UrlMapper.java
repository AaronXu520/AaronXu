package com.xxy.mapper;


import com.xxy.pojo.SourceShortUrl;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface UrlMapper {

    //存入短地址
    public String SaveShortUrl(String SourceUrl,String ShortUrl);

    //取出源地址
    public List<Map<String, Object>> GetSourceUrl(String ShortUrl);


}
