package dev.microcontrollers.shaketweaks.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.ConfigInstance;
import dev.isxander.yacl3.config.GsonConfigInstance;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ShakeTweaksConfig {
    public static final ConfigInstance<ShakeTweaksConfig> INSTANCE = GsonConfigInstance.createBuilder(ShakeTweaksConfig.class)
            .setPath(FabricLoader.getInstance().getConfigDir().resolve("shaketweaks.json"))
            .build();

    @ConfigEntry
    public boolean disableScreenBobbing = true;

    @ConfigEntry
    public boolean disableHandBobbing = false;

    @ConfigEntry
    public boolean disableMapBobbing = true;

    @ConfigEntry
    public boolean disableHandDamage = false;

    @ConfigEntry
    public boolean disableScreenDamage = false;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, ((defaults, config, builder) -> builder
                .title(Text.literal("Better View Bobbing"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Better View Bobbing"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Screen Bobbing"))
                                .description(OptionDescription.of(Text.of("Disables the screen shake when moving.")))
                                .binding(defaults.disableScreenBobbing, () -> config.disableScreenBobbing, newVal -> config.disableScreenBobbing = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Hand Bobbing"))
                                .description(OptionDescription.of(Text.of("Disables the hand shake when moving.")))
                                .binding(defaults.disableHandBobbing, () -> config.disableHandBobbing, newVal -> config.disableHandBobbing = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Map Bobbing"))
                                .description(OptionDescription.of(Text.of("Disables the hand shake when holding a map. Does nothing if \"Disable Hand Bobbing\" is turned on.")))
                                .binding(defaults.disableMapBobbing, () -> config.disableMapBobbing, newVal -> config.disableMapBobbing = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Screen Damage Tilt"))
                                .description(OptionDescription.of(Text.of("Disables the screen shake when taking damage.")))
                                .binding(defaults.disableScreenDamage, () -> config.disableScreenDamage, newVal -> config.disableScreenDamage = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Hand Damage Tilt"))
                                .description(OptionDescription.of(Text.of("Disables the hand shake when taking damage.")))
                                .binding(defaults.disableHandDamage, () -> config.disableHandDamage, newVal -> config.disableHandDamage = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
        )).generateScreen(parent);
    }

}