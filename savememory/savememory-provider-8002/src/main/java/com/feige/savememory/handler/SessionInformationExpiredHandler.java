package com.feige.savememory.handler;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.swing.text.AbstractDocument;
import java.io.IOException;

@Slf4j
@Component
public class SessionInformationExpiredHandler implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {

        AbstractDocument ServletUtils;
        ServletUtil.render(sessionInformationExpiredEvent.getRequest(),
                sessionInformationExpiredEvent.getResponse(), Result.fail(400,"多用户同时登陆"));
    }
}
