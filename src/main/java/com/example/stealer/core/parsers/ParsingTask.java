package com.example.stealer.core.parsers;

import com.example.stealer.entity.ItemEntity;
import com.example.stealer.model.ItemParsingResult;
import com.example.stealer.entity.SiteEntity;
import com.example.stealer.model.SiteParsingResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class ParsingTask implements RunnableScheduledFuture {

    Integer timeout;

    SiteParsingResult result;

    List<ItemEntity> itemList;

    @Override
    public boolean isPeriodic() {
        return false;
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        return 0;
    }

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Object get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    SiteEntity site;

    @Override
    public void run() {
        SiteParsingResult siteParsingResult = new SiteParsingResult();
        if(isAvailable(site)) {
            var itemsParsed = itemList.stream().map(this::execute).collect(Collectors.toList());
            siteParsingResult.setItemParsingResultList(itemsParsed);

        }
        result = siteParsingResult;
    }

    abstract ItemParsingResult execute(ItemEntity item); //TODO add items to site with item validation

    abstract boolean isAvailable(SiteEntity site);//does this page exists or is item removed, etc
}
