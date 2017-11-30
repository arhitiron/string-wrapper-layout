package com.antyron;

import lombok.extern.log4j.Log4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.*;

@Log4j
public class CharListLayout {

    private static final int SIZE = 10;

    public static void main(String[] args) throws Exception {
        log.info("Class layout");
        log.info(ClassLayout.parseClass(Character.class).toPrintable());

        log.info("Runtime footprint");
        Character val = 'A';
        log.info(GraphLayout.parseInstance(val).toFootprint());

        List<Character> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            char ch = (char) i;
            list.add(ch);
                set.add(ch);
        }

        log.info(GraphLayout.parseInstance(list).toFootprint());
        log.info(GraphLayout.parseInstance(set).toFootprint());
    }

}
