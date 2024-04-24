package ru.zarina.util;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.text.StringEscapeUtils;

public class Decoder {
    public static String decodedJsonResponseBody(ValidatableResponse response) {
        return StringEscapeUtils.unescapeJson(response.extract().asString());
    }
}

