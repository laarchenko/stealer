package com.example.stealer.exception;

import com.example.stealer.enums.SiteName;
import org.springframework.http.HttpStatus;

public class NoParserForSiteException extends ApplicationException{

    public NoParserForSiteException(SiteName siteName) {
        super(HttpStatus.BAD_REQUEST, String.format("No parser for site %s found !", siteName.getValue()));
    }
}
