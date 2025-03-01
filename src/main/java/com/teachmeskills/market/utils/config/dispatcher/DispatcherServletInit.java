package com.teachmeskills.market.utils.config.dispatcher;

import com.teachmeskills.market.annotation.LeadTimed;
import com.teachmeskills.market.exception.CustomException;
import com.teachmeskills.market.utils.config.application.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServletInit.class);

    @LeadTimed("-> Worked out in Dispatcher_Servlet_Init")
    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.debug("Getting root config classes");
        try {
            return new Class[0];
        } catch (Exception e) {
            logger.error("Error getting root config classes", e);
            throw new CustomException("Failed to load root config classes");
        }
    }

    @LeadTimed("-> Worked out in Dispatcher_Servlet_Init")
    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.debug("Getting servlet config classes");
        try {
            return new Class[]{AppConfig.class};
        } catch (Exception e) {
            logger.error("Error getting servlet config classes", e);
            throw new CustomException("Failed to load servlet config classes");
        }
    }

    @LeadTimed("-> Worked out in Dispatcher_Servlet_Init")
    @NonNull
    @Override
    protected String[] getServletMappings() {
        logger.debug("Getting servlet mappings");
        return new String[]{"/"};
    }
}