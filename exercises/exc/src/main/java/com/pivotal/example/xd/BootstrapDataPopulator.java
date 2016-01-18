package com.pivotal.example.xd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BootstrapDataPopulator implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(BootstrapDataPopulator.class.getName());


    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        LOG.info("Bootstrapping data...");

        // Create DB table

        LOG.info("...Bootstrapping completed");
    }


}