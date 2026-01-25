package uwu.lopyluna.calamos.event;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.GatherSkippedAttributeTooltipsEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.handler.HookHandler;
import uwu.lopyluna.calamos.client.model.item.IRenderableCurio;
import uwu.lopyluna.calamos.elements.ModDataComponents;
import uwu.lopyluna.calamos.elements.ModModifiers;
import uwu.lopyluna.calamos.elements.items.equipment.armor.CalamosArmorItem;
import uwu.lopyluna.calamos.elements.items.equipment.modifier.Modifier;
import uwu.lopyluna.calamos.utilities.CuriosUtil;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT, modid = CalamosMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ClientForgeEvents {

    @SubscribeEvent
    public static void skipModifierAttributeTooltips(GatherSkippedAttributeTooltipsEvent event) {
        for (var modifier : ModModifiers.MODIFIERS.getEntries()) {
            for (Modifier.Entry entry : modifier.get().attributeModifiers()) {
                event.skipId(entry.modifier().id());
            }
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item.TooltipContext context = event.getContext();
        List<Component> tooltip = event.getToolTip();
        TooltipFlag flag = event.getFlags();
        for (var type : ModDataComponents.COMPONENTS.getEntries()) {
            var comp = stack.get(type);
            if (comp instanceof TooltipProvider tooltipProvider) {
                tooltipProvider.addToTooltip(context, tooltip::add, flag);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        Player player = event.getEntity();
        boolean hideHead = false, hideHat = false, hideBody = false, hideLeftArm = false, hideRightArm = false, hideLeftLeg = false, hideRightLeg = false;
        // Dynamically hide model parts if the curio demands
        var inventoryOp = CuriosApi.getCuriosInventory(player);
        if (inventoryOp.isPresent()) {
            var inventory = inventoryOp.get();
            List<SlotResult> all = CuriosUtil.findAllCurios(inventory, inventory.getWearer(), stack -> true);
            for (SlotResult result : all) {
                if (!result.slotContext().visible()) continue;
                if (result.stack().getItem() instanceof IRenderableCurio renderableCurio) {
                    for (String limb : renderableCurio.hiddenLimbs()) {
                        switch (limb) {
                            case "head": hideHead = true; break;
                            case "hat": hideHat = true; break;
                            case "body": hideBody = true; break;
                            case "left_arm": hideLeftArm = true; break;
                            case "right_arm": hideRightArm = true; break;
                            case "left_leg": hideLeftLeg = true; break;
                            case "right_leg": hideRightLeg = true; break;
                        }
                    }
                }
            }
        }
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.getItem() instanceof CalamosArmorItem armorItem) {
                for (String limb : armorItem.hiddenLimbs().get(armorItem.getType().getSlot())) {
                    switch (limb) {
                        case "head": hideHead = true; break;
                        case "hat": hideHat = true; break;
                        case "body": hideBody = true; break;
                        case "left_arm": hideLeftArm = true; break;
                        case "right_arm": hideRightArm = true; break;
                        case "left_leg": hideLeftLeg = true; break;
                        case "right_leg": hideRightLeg = true; break;
                    }
                }
            }
        }
        var model = event.getRenderer().getModel();
        model.head.visible = !hideHead;
        model.hat.visible = !hideHat;
        model.body.visible = !hideBody;
        model.leftArm.visible = !hideLeftArm;
        model.rightArm.visible = !hideRightArm;
        model.leftLeg.visible = !hideLeftLeg;
        model.rightLeg.visible = !hideRightLeg;
    }

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        HookHandler.handle(camera, localPlayer);
    }
}
