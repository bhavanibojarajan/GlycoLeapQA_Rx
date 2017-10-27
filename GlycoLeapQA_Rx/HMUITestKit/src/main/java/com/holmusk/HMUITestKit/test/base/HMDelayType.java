package com.holmusk.HMUITestKit.test.base;

import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;

/**
 * Created by haipham on 12/6/17.
 */
public interface HMDelayType extends ErrorProviderType {
    /**
     * Catch-all delay.
     * @param engine {@link Engine} instance.
     * @return {@link Long} value.
     */
    default long generalDelay(@NotNull Engine<?> engine) {
        if (engine instanceof AndroidEngine) {
            return 1000;
        } else {
            return 2000;
        }
    }

    /**
     * Delay between the time the user submits a CSS log, and that when the
     * next page appears.
     * @param engine {@link Engine} instance.
     * @return {@link Long} value.
     */
    default long cssLogProgressDelay(@NotNull Engine<?> engine) {
        return 1000;
    }
}
