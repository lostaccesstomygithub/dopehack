package me.smokedope2016.dopehack.manager;

import com.google.gson.*;
import me.smokedope2016.dopehack.DopeHack;
import me.smokedope2016.dopehack.features.Feature;
import me.smokedope2016.dopehack.features.settings.Bind;
import me.smokedope2016.dopehack.features.settings.EnumConverter;
import me.smokedope2016.dopehack.features.settings.Setting;
import me.smokedope2016.dopehack.util.traits.Jsonable;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConfigManager {
    private static final Path OYVEY_PATH = FabricLoader.getInstance().getGameDir().resolve("oyvey");
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private final List<Jsonable> jsonables = List.of(DopeHack.friendManager, DopeHack.moduleManager, DopeHack.commandManager);

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void setValueFromJson(Feature feature, Setting setting, JsonElement element) {
        String str;
        switch (setting.getType()) {
            case "Boolean" -> {
                setting.setValue(element.getAsBoolean());
            }
            case "Double" -> {
                setting.setValue(element.getAsDouble());
            }
            case "Float" -> {
                setting.setValue(element.getAsFloat());
            }
            case "Integer" -> {
                setting.setValue(element.getAsInt());
            }
            case "String" -> {
                str = element.getAsString();
                setting.setValue(str.replace("_", " "));
            }
            case "Bind" -> {
                setting.setValue(new Bind(element.getAsInt()));
            }
            case "Enum" -> {
                try {
                    EnumConverter converter = new EnumConverter(((Enum) setting.getValue()).getClass());
                    Enum value = converter.doBackward(element);
                    setting.setValue((value == null) ? setting.getDefaultValue() : value);
                } catch (Exception exception) {
                }
            }
            default -> {
                DopeHack.LOGGER.error("Unknown Setting type for: " + feature.getName() + " : " + setting.getName());
            }
        }
    }

    public void load() {
        if (!OYVEY_PATH.toFile().exists()) OYVEY_PATH.toFile().mkdirs();
        for (Jsonable jsonable : jsonables) {
            try {
                String read = Files.readString(OYVEY_PATH.resolve(jsonable.getFileName()));
                jsonable.fromJson(JsonParser.parseString(read));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        if (!OYVEY_PATH.toFile().exists()) OYVEY_PATH.toFile().mkdirs();
        for (Jsonable jsonable : jsonables) {
            try {
                JsonElement json = jsonable.toJson();
                Files.writeString(OYVEY_PATH.resolve(jsonable.getFileName()), gson.toJson(json));
            } catch (Throwable e) {
            }
        }
    }
}
