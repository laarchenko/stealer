package com.example.stealer.service.impl;

import com.example.stealer.model.Site;
import com.example.stealer.service.SiteService;
import org.springframework.stereotype.Component;

@Component
public class SiteServiceImpl implements SiteService {

    @Override
    public Site resolveSiteByUrl(String itemUrl) {
        //TODO Implement
        return Site.builder().build();
    }
}
