package uwu.lopyluna.calamos.elements;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import uwu.lopyluna.calamos.CalamosMod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public enum CalamosKeys {
    boost("wings_boost", 32)
    
    ;
    
    private KeyMapping keybind;
    private String description;
    private int key;
    private boolean modifiable;
    
    private CalamosKeys(String description, int defaultKey) {
        this.description = CalamosMod.MODID + ".key." + description;
        this.key = defaultKey;
        this.modifiable = !description.isEmpty();
    }
    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        for (CalamosKeys key : values()) {
            key.keybind = new KeyMapping(key.description, key.key, CalamosMod.NAME);
            if (!key.modifiable)
                continue;
            
            event.register(key.keybind);
        }
    }
    
    public KeyMapping getKeybind() {
        return keybind;
    }
    
    public boolean isPressed() {
        if (!modifiable)
            return isKeyDown(key);
        return keybind.isDown();
    }
    
    public String getBoundKey() {
        return keybind.getTranslatedKeyMessage()
                .getString()
                .toUpperCase();
    }
    
    public int getBoundCode() {
        return keybind.getKey()
                .getValue();
    }
    
    public static boolean isKeyDown(int key) {
        return InputConstants.isKeyDown(Minecraft.getInstance()
                .getWindow()
                .getWindow(), key);
    }
    
    public static boolean isMouseButtonDown(int button) {
        return GLFW.glfwGetMouseButton(Minecraft.getInstance()
                .getWindow()
                .getWindow(), button) == 1;
    }
    
    public static boolean ctrlDown() {
        return Screen.hasControlDown();
    }
    
    public static boolean shiftDown() {
        return Screen.hasShiftDown();
    }
    
    public static boolean altDown() {
        return Screen.hasAltDown();
    }
}
