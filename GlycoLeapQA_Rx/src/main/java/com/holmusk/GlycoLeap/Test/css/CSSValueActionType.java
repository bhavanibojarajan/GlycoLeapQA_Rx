package com.holmusk.GlycoLeap.Test.css;

import com.holmusk.GlycoLeap.Test.base.BaseActionType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkit.navigation.ScreenType;

import java.util.concurrent.TimeUnit;

public interface CSSValueActionType  extends BaseActionType{

/**
 * {@link com.holmusk.GlycoLeap.navigation.Screen#WEIGHT_ENTRY}
 * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD}. We do not
 * use this with {@link com.holmusk.GlycoLeap.navigation.ScreenHolder}
 * because the flows for both platforms are different in such a way that
 * it causes {@link StackOverflowError} when we use
 * {@link org.swiften.xtestkit.navigation.ScreenManagerType#multiNodes(ScreenType...)}
 * @param engine {@link Engine} instance.
 * @return {@link Flowable} instance.
 * @see #generalDelay(Engine)
 * @see #rxa_clickBackButton(Engine)
 */
@NotNull
default Flowable<?> rxa_dashboardFromCSSEntry(@NotNull Engine<?> engine) {
    if (engine instanceof AndroidEngine) {
        return rxa_clickBackButton(engine);
    } else if (engine instanceof IOSEngine) {
        final CSSValueActionType THIS = this;

        return Flowable.range(0, 2)
                .concatMap(a -> THIS.rxa_clickBackButton(engine))
                .delay(generalDelay(engine), TimeUnit.MILLISECONDS);
    } else {
        throw new RuntimeException(NOT_AVAILABLE);
    }
}
}
