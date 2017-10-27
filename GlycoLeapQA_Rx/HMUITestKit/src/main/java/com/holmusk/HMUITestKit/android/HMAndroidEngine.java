package com.holmusk.HMUITestKit.android;

import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.android.capability.AndroidEngineCapability;

/**
 * Created by haipham on 2/6/17.
 */

/**
 * {@link AndroidEngine} for Holmusk.
 */
public class HMAndroidEngine extends AndroidEngine {
    /**
     * Get a {@link Builder} instance.
     * @return {@link Builder} instance.
     */
    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for {@link HMAndroidEngine}.
     */
    public static final class Builder extends AndroidEngine.Builder {
        Builder() {
            super(new HMAndroidEngine(), AndroidEngineCapability.builder());
        }
    }
}
