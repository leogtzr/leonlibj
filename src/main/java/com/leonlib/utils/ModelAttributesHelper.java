package com.leonlib.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;

public final class ModelAttributesHelper {

    private static final Logger logger = LoggerFactory.getLogger(ModelAttributesHelper.class);

    private ModelAttributesHelper() {
        // ...
    }

    public static void setLoggedInAttributesInModel(final Model model, final HttpSession session) {
        final Object sub = session.getAttribute("sub");
        if (sub == null) {
            logger.info("debug:x User might not be logged in");
            model.addAttribute("loggedIn", false);
        } else {
            logger.info("debug:x User IS logged in");
            final String subValue = (String) sub;

            logger.info(String.format("debug:x sub=(%s)", subValue));
            model.addAttribute("loggedIn", true);
        }
    }
    
}
