package com.example.stealer.service;

import com.example.stealer.model.Site;

import java.util.List;

public interface SiteService {

    Site getSiteByItemUrl(String itemUrl);

    List<Site> getAllEnabled();
}
