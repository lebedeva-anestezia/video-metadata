package org.interview.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Util {

    public static void setIfPresentString(Supplier<String> getter, Consumer<String> setter) {
        if (getter.get() != null) {
            setter.accept(getter.get());
        }
    }

    public static void setIfPresentInteger(Supplier<Integer> getter, Consumer<Integer> setter) {
        if (getter.get() != null) {
            setter.accept(getter.get());
        }
    }
}
