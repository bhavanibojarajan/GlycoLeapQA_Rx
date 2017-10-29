package com.holmusk.GlycoLeap.model;

import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.xtestkitcomponents.direction.Direction;

public enum DashboardMode implements ErrorProviderType {
    WEIGHT,
    ACTIVITY,
    HBA1C;

    /**
     * Get the swipe {@link Direction} with which we can perform a swipe
     * to reveal the current {@link DashboardMode}.
     * @return {@link Direction} instance.
     */
    @NotNull
    public Direction swipeDirection() {
        switch (this) {
            case WEIGHT:
                return Direction.LEFT_RIGHT;

            case ACTIVITY:
                return Direction.RIGHT_LEFT;

            case HBA1C:
                return Direction.DOUBLE_RIGHT_LEFT;
            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }
}
