package dev.microcontrollers.shaketweaks.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.GsonConfigInstance;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class ShakeTweaksConfig {
    public static GsonConfigInstance<dev.microcontrollers.shaketweaks.config.ShakeTweaksConfig> configInstance = new GsonConfigInstance<>(dev.microcontrollers.shaketweaks.config.ShakeTweaksConfig.class, Path.of(FabricLoader.getInstance().getConfigDir().toString(), "shaketweaks.json"));

    @ConfigEntry
    public static boolean disableScreenBobbing;

    @ConfigEntry
    public static boolean disableHandBobbing;

    @ConfigEntry
    public static boolean disableMapBobbing;

    @ConfigEntry
    public static boolean disableHandDamage;

    @ConfigEntry
    public static boolean disableScreenDamage;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(configInstance, ((defaults, config, builder) -> builder
                .title(Text.literal("Better View Bobbing"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Better View Bobbing"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Screen Bobbing"))
                                .description(OptionDescription.of(Text.of("Disables the screen shake when moving.")))
                                .binding(false, () -> disableScreenBobbing, newVal -> disableScreenBobbing = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Hand Bobbing"))
                                .description(OptionDescription.of(Text.of("Disables the hand shake when moving.")))
                                .binding(false, () -> disableHandBobbing, newVal -> disableHandBobbing = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Map Bobbing"))
                                .description(OptionDescription.of(Text.of("Disables the hand shake when holding a map.")))
                                .binding(false, () -> disableMapBobbing, newVal -> disableMapBobbing = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Screen Damage Tilt"))
                                .description(OptionDescription.of(Text.of("Disables the screen shake when taking damage.")))
                                .binding(false, () -> disableScreenDamage, newVal -> disableScreenDamage = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Hand Damage Tilt"))
                                .description(OptionDescription.of(Text.of("Disables the hand shake when taking damage.")))
                                .binding(false, () -> disableHandDamage, newVal -> disableHandDamage = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
        )).generateScreen(parent);
    }

}
