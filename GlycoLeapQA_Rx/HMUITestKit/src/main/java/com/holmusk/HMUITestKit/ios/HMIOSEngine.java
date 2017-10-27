package com.holmusk.HMUITestKit.ios;

import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkit.ios.capability.IOSEngineCapability;

/**
 * Created by haipham on 2/6/17.
 */

/**
 * {@link IOSEngine} for Holmusk.
 */
public class HMIOSEngine extends IOSEngine {
    /**
     * Get {@link Builder} instance.
     * @return {@link Builder} instance.
     */
    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for {@link HMIOSEngine}.
     */
    public static final class Builder extends IOSEngine.Builder {
        Builder() {
            super(new HMIOSEngine(), IOSEngineCapability.builder());
        }
    }
}
