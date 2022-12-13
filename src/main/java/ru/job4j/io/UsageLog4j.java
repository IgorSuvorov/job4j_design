package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int a = 33;
        short b = 2;
        long c = 222;
        double d = 4.5;
        float e = 4.44f;
        boolean f = true;
        byte g = 1;
        char h = '1';
        LOG.debug("a: {}, b: {}, c: {}, d: {}, e: {}, f: {}, g: {}, h: {}", a, b, c, d, e, f, g, h);
    }
}