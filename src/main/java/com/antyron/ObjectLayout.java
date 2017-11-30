package com.antyron;

import lombok.extern.log4j.Log4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.*;

@Log4j
public class ObjectLayout {

    private static final int UNIQUE_STRINGS = 1000;
    private static final int DUPLICATES = 10_000;

    public static void main(String[] args) throws Exception {
        log.info("Class layout");
        log.info(ClassLayout.parseClass(String.class).toPrintable());
        log.info(ClassLayout.parseClass(WString.class).toPrintable());

        log.info("Runtime footprint");
        String val = UUID.randomUUID().toString();
        log.info(GraphLayout.parseInstance(val).toFootprint());

        log.info(GraphLayout.parseInstance(new WString(val)).toFootprint());

        List<String> listString = new ArrayList<>();
        List<WString> listWString = new ArrayList<>();
        for (int i = 0; i < UNIQUE_STRINGS; i++) {
            val = UUID.randomUUID().toString();
            for (int j = 0; j < DUPLICATES; j++) {
                listString.add(new String(val));
                listWString.add(new WString(val));
            }
        }

        log.info(GraphLayout.parseInstance(listString).toFootprint());
        log.info(GraphLayout.parseInstance(listWString).toFootprint());
    }

    // String wrapper
    public static class WString {
        private static final long serialVersionUID = 2705552907891794575L;

        private static final Map<String, String> cache = new HashMap<>();

        private String value;

        public WString(String value) {
            cache.putIfAbsent(value, value);
            this.value = cache.get(value);
        }

    }
}
