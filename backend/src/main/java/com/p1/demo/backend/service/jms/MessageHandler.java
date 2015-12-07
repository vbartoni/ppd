package com.p1.demo.backend.service.jms;

import javax.jms.Message;

/**
 * Created by bartpved on 2015-12-06.
 */
interface MessageHandler
{
    void handle(Message msg);
}
