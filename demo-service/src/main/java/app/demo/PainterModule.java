package app.demo;

import app.demo.painter.service.BrushService;
import app.demo.painter.service.PainterService;
import core.framework.module.Module;

/**
 * @author kimi
 */
public class PainterModule extends Module {
    @Override
    protected void initialize() {
        bind(BrushService.class);
        bind(PainterService.class);

        PainterService painterService = bean(PainterService.class);
        painterService.draw();
    }
}
