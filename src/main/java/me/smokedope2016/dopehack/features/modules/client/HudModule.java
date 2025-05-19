package me.smokedope2016.dopehack.features.modules.client;

import me.smokedope2016.dopehack.DopeHack;
import me.smokedope2016.dopehack.event.impl.Render2DEvent;
import me.smokedope2016.dopehack.features.modules.Module;

public class HudModule extends Module {
    public HudModule() {
        super("Hud", "hud", Category.CLIENT, true, false, false);
    }

    @Override public void onRender2D(Render2DEvent event) {
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                DopeHack.NAME + " " + DopeHack.VERSION,
                2, 2,
                -1
        );
    }
}
