//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.common.utils;

import com.niu.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;

public class Exceptions {
    private static final Logger log = LoggerFactory.getLogger(Exceptions.class);

    public Exceptions() {
    }

    public static <T> T uncheck(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception var2) {
            log.error("", var2);
            return null;
        }
    }

    public static <T> Optional<T> uncheckOptional(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception var2) {
            log.error("", var2);
            return Optional.empty();
        }
    }

    public static <T> Result<T, Exception> uncheckResult(Supplier<T> supplier) {
        try {
            return Result.of(supplier.get());
        } catch (Exception var2) {
            log.error("", var2);
            return Result.error(var2);
        }
    }
}
