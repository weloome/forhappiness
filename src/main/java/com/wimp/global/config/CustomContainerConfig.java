package com.wimp.global.config;


import ch.qos.logback.access.tomcat.LogbackValve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class CustomContainerConfig implements
        WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {

        if (factory instanceof TomcatServletWebServerFactory) {
            TomcatServletWebServerFactory containerFactory =
                    (TomcatServletWebServerFactory) factory;

            LogbackValve logbackValve = new LogbackValve();

            logbackValve.setFilename("logback-access.xml");

            containerFactory.addContextValves(logbackValve);
        }

    }
}
