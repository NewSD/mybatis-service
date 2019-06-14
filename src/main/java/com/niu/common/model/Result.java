//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.common.model;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Result<T, E> {
    private final T value;
    private final E error;

    private Result() {
        this.value = null;
        this.error = null;
    }

    private Result(T value) {
        this.value = Objects.requireNonNull(value);
        this.error = null;
    }

    private Result(T value, E error) {
        this.value = value;
        this.error = error;
    }

    public static <T, E> Result<T, E> of(T value) {
        return new Result(value);
    }

    public static <T, E> Result<T, E> error(E error) {
        return new Result((Object) null, Objects.requireNonNull(error));
    }

    public T get() {
        if (!this.isSuccess()) {
            throw new NoSuchElementException("The result is not success: " + this.error.toString());
        } else {
            return this.value;
        }
    }

    public E error() {
        return this.error;
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return !this.isSuccess() ? Optional.empty() : (predicate.test(this.value) ? Optional.ofNullable(this.value) : Optional.empty());
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return !this.isSuccess() ? Optional.empty() : Optional.ofNullable(mapper.apply(this.value));
    }

    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        return !this.isSuccess() ? Optional.empty() : (Optional) Objects.requireNonNull(mapper.apply(this.value));
    }

    public T orElse(T other) {
        return this.isSuccess() ? this.value : other;
    }

    public T orElseGet(Function<E, ? extends T> other) {
        return this.isSuccess() ? this.value : other.apply(this.error);
    }

    public Result<T, E>.OtherwiseResult<E> onSuccess(Consumer<T> consumer) {
        if (this.isSuccess()) {
            consumer.accept(this.value);
            return new Result.OtherwiseResult();
        } else {
            return new Result.OtherwiseResult(this.error);
        }
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (this.isSuccess()) {
            return this.value;
        } else {
            throw (X) exceptionSupplier.get();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Result)) {
            return false;
        } else {
            Result<?, ?> other = (Result) obj;
            return Objects.equals(this.value, other.value) && Objects.equals(this.error, other.error);
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.value) + Objects.hashCode(this.error);
    }

    public String toString() {
        return this.isSuccess() ? String.format("Result[%s, null]", new Object[]{this.value}) : String.format("Result[null, %s]", new Object[]{this.error});
    }

    public class OtherwiseResult<E> {
        private Optional<E> otherwiseOption;

        private OtherwiseResult(E this$0) {
            this.otherwiseOption = Optional.of((E) error);
        }

        private OtherwiseResult() {
            this.otherwiseOption = Optional.empty();
        }

        public E otherwise(Consumer<E> consumer) {
            if (this.otherwiseOption.isPresent()) {
                E otherwise = this.otherwiseOption.get();
                ((Consumer) Objects.requireNonNull(consumer)).accept(otherwise);
                return otherwise;
            } else {
                return null;
            }
        }
    }
}
