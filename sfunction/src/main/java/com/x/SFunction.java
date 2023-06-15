package com.x;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author xgh 2023/6/15
 */
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
