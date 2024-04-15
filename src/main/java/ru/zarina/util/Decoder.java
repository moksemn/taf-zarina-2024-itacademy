package ru.zarina.util;

import io.restassured.response.Response;
import org.apache.commons.text.StringEscapeUtils;

public class Decoder {
    public static String decodedJsonResponseBody(Response response) {
        return StringEscapeUtils.unescapeJson(response.getBody().asString());
    }
}

