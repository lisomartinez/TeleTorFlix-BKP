package com.teletorflix.app.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFiles {
    private static Path path = Paths.get("src", "test", "resources", "model");

    private JsonFiles() {}

    public static File getExternalsExpected() {
       return path.resolve("ExternalsJson_Expected.json").toFile();
    }

    public static File getShowDeserialize() {
        return path.resolve("ShowJson_Deserialize.json").toFile();
    }

    public static File getShowSerielizeExpected() {
        return path.resolve("ShowJson_Serialize_Expected.json").toFile();
    }

    public static File getImageExpected() {
        return path.resolve("ImageJson_Expected.json").toFile();
    }

    public static File getImageDeserialize() {
        return path.resolve("ImageJson_Expected.json").toFile();
    }
}
