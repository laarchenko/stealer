package com.example.stealer.service;

import com.example.stealer.model.Site;

public interface SiteService {

    Site resolveSiteByUrl(String itemUrl);
}
