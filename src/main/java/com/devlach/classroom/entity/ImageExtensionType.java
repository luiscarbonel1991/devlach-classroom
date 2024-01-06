package com.devlach.classroom.entity;

import lombok.Getter;

@Getter
public enum ImageExtensionType {
    WEBP("webp"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif"),
    SVG("svg");

    private final String extension;

    ImageExtensionType(String extension) {
        this.extension = extension;
    }
}
