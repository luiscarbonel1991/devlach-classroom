package com.devlach.classroom.entity;

import com.devlach.classroom.api.exception.BadRequestException;

import java.util.Arrays;
import java.util.List;

public enum FileExtensionType {
    WEBP,
    JPG,
    JPEG,
    PNG,
    PDF,
    DOC,
    DOCX,
    XLS,
    XLSX,
    PPT,
    PPTX,
    MP4,
    MP3,
    AVI,
    MOV,
    WMV,
    FLV,
    TXT,
    ZIP,
    RAR,
    OTHER;

    public static List<FileExtensionType> imageTypes() {
        return Arrays.asList(WEBP, JPG, JPEG, PNG);
    }

    public static FileExtensionType of(String extension) {
        return Arrays.stream(values())
                .filter(ft -> ft.name().equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> BadRequestException.invalidImageByExtension(extension, Arrays.stream(values()).toList()));
    }
}
