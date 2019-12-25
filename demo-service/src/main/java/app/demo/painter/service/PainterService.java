package app.demo.painter.service;

import core.framework.inject.Inject;
import core.framework.log.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kimi
 */
public class PainterService {
    private final Logger logger = LoggerFactory.getLogger(PainterService.class);
    @Inject
    BrushService brushService;

    public void draw() {
        logger.warn(Markers.errorCode("PAINTER_DRAW"), "painter drawing....");
        brushService.print();
    }
}
