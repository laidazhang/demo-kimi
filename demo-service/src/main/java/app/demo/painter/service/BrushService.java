package app.demo.painter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kimi
 */
public class BrushService {
    private final Logger logger = LoggerFactory.getLogger(BrushService.class);

    public void print() {
        logger.info("hello world");
    }
}
