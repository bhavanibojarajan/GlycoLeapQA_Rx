package com.holmusk.HMUITestKit.model;

/**
 * Created by haipham on 6/6/17.
 */

import org.jetbrains.annotations.NotNull;

/**
 * This interface represents {@link Object} that has an associated
 * {@link UnitSystem}.
 */
@FunctionalInterface
public interface HMUnitSystemConvertibleType {
    /**
     * Get the associated {@link UnitSystem}.
     * @return {@link UnitSystem} instance.
     */
    @NotNull UnitSystem unitSystem();
}
