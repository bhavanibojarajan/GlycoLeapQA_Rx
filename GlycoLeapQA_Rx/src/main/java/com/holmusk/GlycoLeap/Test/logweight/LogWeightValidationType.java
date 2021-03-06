package com.holmusk.GlycoLeap.Test.logweight;

import com.holmusk.GlycoLeap.model.CSSInput;
import com.holmusk.HMUITestKit.model.HMCSSInputType;
import com.holmusk.HMUITestKit.test.circlescrollselector.HMCircleScrollValidationType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.javautilities.protocol.ClassNameProviderType;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.android.AndroidView;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkit.ios.IOSView;

public interface LogWeightValidationType extends HMCircleScrollValidationType{
    /**
     * Get the weight location switch {@link WebElement}.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see Engine#rxe_ofClass(ClassNameProviderType[])
     */
    @NotNull
    default Flowable<WebElement> rxe_weightLocSwitch(@NotNull Engine<?> engine) {
        if (engine instanceof AndroidEngine) {
            return engine
                    .rxe_ofClass(AndroidView.Type.SWITCH)
                    .firstElement()
                    .toFlowable();
        } else if (engine instanceof IOSEngine) {
            return engine
                    .rxe_ofClass(IOSView.Type.UI_SWITCH)
                    .firstElement()
                    .toFlowable();
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }
    /**
     * Validate {@link com.holmusk.GlycoLeap.navigation.Screen#WEIGHT_VALUE}
     * weight entry screen.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_CSSValueDisplay(Engine, HMCSSInputType)
     * @see #rxe_CSSDetailEntrySubmit(Engine, HMCSSInputType)
     * @see #rxe_weightLocSwitch(Engine)
     * @see #rxe_CSSLogTime(Engine, HMCSSInputType)
     */
    @NotNull
    @SuppressWarnings("unchecked")
    default Flowable<?> rxv_weightEntry(@NotNull Engine<?> engine) {
        HMCSSInputType input = CSSInput.WEIGHT;

        return Flowable.mergeArray(
                rxe_CSSValueDisplay(engine, input),
                rxe_CSSLogTime(engine, input),
                rxe_weightLocSwitch(engine),
                rxe_CSSDetailEntrySubmit(engine, input)
        ).all(HPObjects::nonNull).toFlowable();
    }
}
