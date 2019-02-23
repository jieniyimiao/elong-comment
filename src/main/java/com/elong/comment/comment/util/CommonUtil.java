package com.elong.comment.comment.util;

import java.util.UUID;

public class CommonUtil {

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
