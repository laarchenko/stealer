package com.example.stealer.core.parsing;

import com.example.stealer.core.entity.Site;
import com.example.stealer.core.parsing.model.ParsingResult;
import com.example.stealer.infrastructure.parsing.Parser;
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
