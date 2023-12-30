package com.example.stealer.service.impl;

import com.example.stealer.mapper.entity.SiteEntityMapper;
import com.example.stealer.model.Site;
import com.example.stealer.repo.SiteRepo;
import com.example.stealer.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteServiceImpl implements SiteService {

    private final SiteRepo siteRepo;
    private final SiteEntityMapper mapper;

    @Override
    public Site getSiteByItemUrl(String itemUrl) {
        return getAllEnabled().stream().filter(
                site -> itemUrl.contains(site.getUrl())).findFirst().orElseThrow();
    }

    @Override
    @Cacheable("sites")
    public List<Site> getAllEnabled() {
        return siteRepo.findByEnabledTrue().stream().map(mapper::toModel).collect(Collectors.toList());
    }


}
