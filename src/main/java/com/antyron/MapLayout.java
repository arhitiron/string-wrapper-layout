package com.antyron;

import lombok.extern.log4j.Log4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.*;

@Log4j
public class MapLayout {


    private static final int VALUE = 42;
    public static final int MAP_SIZE = 20;

    public static void main(String[] args) throws Exception {
        log.info("Class layout");
        log.info(ClassLayout.parseClass(MapLayout.Dummy.class).toPrintable());



        Map<Dummy, Integer> dummyMap = new HashMap<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            dummyMap.put(new Dummy(VALUE), VALUE);
        }

        log.info(GraphLayout.parseInstance(dummyMap).toPrintable());


        Iterator<Map.Entry<Dummy, Integer>> iterator = dummyMap.entrySet().iterator();
        int counter = 0;
        while (MAP_SIZE - counter > 6) {
            iterator.next();
            iterator.remove();
            counter++;
        }

        log.info(GraphLayout.parseInstance(dummyMap).toPrintable());
    }

    static class Dummy {
        int value;

        Dummy(int value) {
            this.value = value;
        }


        @Override
        public int hashCode() {
            return value;
        }
    }
}
