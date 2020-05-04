package com.xxy.pojo;


import com.xxy.mapper.UrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class JdbcOperation implements UrlMapper{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String SaveShortUrl(String SourceUrl, String ShortUrl) {
        jdbcTemplate.update("insert into Url(shortUrl,sourceUrl) values('"+ ShortUrl+"', '"+SourceUrl+"' );");
        return "ok!";
    }

    @Override
    public List<Map<String, Object>> GetSourceUrl(String ShortUrl){
        try{
            List<Map<String, Object>> map=jdbcTemplate.queryForList("select * from Url where shortUrl = ' "+ ShortUrl +" ';");
            return map;
        }catch (Exception e){
            return null;
        }

    }

}
