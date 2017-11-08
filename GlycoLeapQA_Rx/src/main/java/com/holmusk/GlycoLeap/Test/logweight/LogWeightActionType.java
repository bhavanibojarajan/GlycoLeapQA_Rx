package com.holmusk.GlycoLeap.Test.logweight;

import com.holmusk.GlycoLeap.Test.base.BaseActionType;
import com.holmusk.HMUITestKit.test.circlescrollselector.HMCircleScrollActionType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;


public interface LogWeightActionType extends BaseActionType, HMCircleScrollActionType, LogWeightValidationType{
    /**
     * Toggle weight location.
     * @param engine {@link Engine} instance.
     * @param on {@link Boolean} value.
     * @return {@link Flowable} instance.
     * @see #rxe_weightLocSwitch(Engine)
     */
    @NotNull
    default Flowable<?> rxa_toggleWeightLoc(@NotNull Engine<?> engine, boolean on) {
        return rxe_weightLocSwitch(engine).compose(engine.toggleSwitchFn(on));
    }
}
