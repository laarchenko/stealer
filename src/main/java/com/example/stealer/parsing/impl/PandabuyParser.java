package com.example.stealer.parsing.impl;

import com.example.stealer.entity.Site;
import com.example.stealer.parsing.Parser;
import com.example.stealer.parsing.ParsingResult;
import org.springframework.stereotype.Component;

@Component

public class PandabuyParser extends Parser {

    @Override
    public void setTimeout(Integer timeout) {
        super.setTimeout(20);
    }

    @Override
    protected ParsingResult execute(Site site) {
        return null;
    }
}
