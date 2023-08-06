package com.example.stealer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class ParsingService {


    ScheduledThreadPoolExecutor executor;

    ParsingService() {

    }


}
