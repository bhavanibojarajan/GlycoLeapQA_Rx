package com.holmusk.HMUITestKit.test.circlescrollselector;

/**
 * Created by haipham on 9/6/17.
 */

import com.holmusk.HMUITestKit.model.HMCSSInputType;
import com.holmusk.HMUITestKit.test.datetime.HMDateTimeActionType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.swiften.javautilities.bool.HPBooleans;
import org.swiften.javautilities.number.HPNumbers;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.javautilities.protocol.RepeatProviderType;
import org.swiften.javautilities.util.HPLog;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.base.param.DirectionParam;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkitcomponents.direction.Direction;
import org.swiften.xtestkitcomponents.direction.DirectionProviderType;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This interface provides methods to handle circle scroll selectors, i.e.
 * views used to select a value displayed within a circle. As the value
 * increases, the circle grows larger in size as well.
 */
public interface HMCircleScrollActionType extends
    HMCircleScrollValidationType,
    HMDateTimeActionType
{
    /**
     * Scroll the circle scroll selector view.
     * @param E {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @param param {@link P} instance.
     * @param <P> Generics parameter.
     * @return {@link Flowable} instance.
     * @see Engine#rxa_swipeGeneric(WebElement, DirectionProviderType)
     * @see #rxe_CSSSView(Engine, HMCSSInputType)
     */
    @NotNull
    default <P extends RepeatProviderType & DirectionProviderType> Flowable<?>
    rxa_swipeCSSView(@NotNull final Engine<?> E,
                     @NotNull HMCSSInputType input,
                     @NotNull P param) {
        final DirectionParam DP = DirectionParam.builder()

            /* On Android, we swipe more since the off-center swipe motions
             * are not as sensitive */
            .withStartRatio(E instanceof AndroidEngine ? 0.2d : 0.5d)
            .withEndRatio(E instanceof AndroidEngine ? 0.8d : 0.6d)
            .withAnchorRatio(0.8d)
            .withDuration(0)
            .withRepeatProvider(param)
            .withDirectionProvider(param)
            .build();

        return rxe_CSSSView(E, input).flatMap(a -> E.rxa_swipeGeneric(a, DP));
    }

    /**
     * Perform random swipe motions on the circle scroll selector view. We
     * need to resort to this tactic because on
     * {@link org.swiften.xtestkit.mobile.Platform#ANDROID}, this view is
     * custom drawn (so all its text values cannot be accessed with
     * {@link org.swiften.xtestkitcomponents.xpath.XPath}). Therefore, this is
     * one area whereby automated tests do not suffice.
     * @param ENGINE {@link Engine} instance.
     * @param INPUT {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see Direction#randomVertical()
     * @see HPNumbers#randomBetween(int, int)
     * @see #rxa_swipeCSSView(Engine, HMCSSInputType, RepeatProviderType)
     */
    @NotNull
    default Flowable<?> rxa_swipeCSSViewRandomly(@NotNull final Engine<?> ENGINE,
                                                 @NotNull final HMCSSInputType INPUT) {
        int times = HPNumbers.randomBetween(3, 5);

        return Flowable.range(0, times)
            .map(a -> Direction.randomVertical())
            .map(a -> DirectionParam.builder().withDirection(a).build())
            .concatMap(a -> rxa_swipeCSSView(ENGINE, INPUT, a))
            .all(HPObjects::nonNull)
            .toFlowable();
    }

    /**
     * Submit a new weight value.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see #cssLogProgressDelay(Engine)
     * @see #rxe_CSSValueSubmit(Engine, HMCSSInputType)
     */
    @NotNull
    default Flowable<?> rxa_submitCSSValue(@NotNull Engine<?> engine,
                                           @NotNull HMCSSInputType input) {
        HPLog.printlnt("Submitting CSS value");

        return rxe_CSSValueSubmit(engine, input)
            .compose(engine.clickFn())
            .delay(cssLogProgressDelay(engine), TimeUnit.MILLISECONDS);
    }

    /**
     * Log a new CSS value, without completing the CSS entry.
     * @param ENGINE {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_swipeCSSViewRandomly(Engine, HMCSSInputType)
     * @see #rxa_submitCSSValue(Engine, HMCSSInputType)
     */
    @NotNull
    default Flowable<?> rxa_completeCSSValue(@NotNull final Engine<?> ENGINE,
                                             @NotNull final HMCSSInputType INPUT) {
        final HMCircleScrollActionType THIS = this;

        return rxa_swipeCSSViewRandomly(ENGINE, INPUT)
            .flatMap(a -> THIS.rxa_submitCSSValue(ENGINE, INPUT));
    }

    /**
     * Submit a new CSS entry. This action must be done after CSS value selection.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see #cssLogProgressDelay(Engine)
     * @see #rxe_CSSDetailEntrySubmit(Engine, HMCSSInputType)
     */
    @NotNull
    default Flowable<?> rxa_submitCSSEntry(@NotNull Engine<?> engine,
                                           @NotNull HMCSSInputType input) {
        HPLog.printlnt("Submitting CSS entry");

        return rxe_CSSDetailEntrySubmit(engine, input)
            .compose(engine.clickFn())
            .delay(cssLogProgressDelay(engine), TimeUnit.MILLISECONDS);
    }

    /**
     * Open the CSS time picker.
     * On {@link org.swiften.xtestkit.mobile.Platform#IOS}, the time picker
     * is open by default, so if we click on the {@link WebElement} emitted by
     * {@link #rxe_CSSLogTime(Engine, HMCSSInputType)}, it will close.
     * Therefore, we need to check if it is visible first.
     * @param engine {@link Engine} instance.
     * @param INPUT {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_CSSLogTime(Engine, HMCSSInputType)
     * @see #rxv_CSSTimePickerOpen(Engine)
     */
    @NotNull
    default Flowable<?> rxa_openCSSTimePicker(@NotNull Engine<?> engine,
                                              @NotNull final HMCSSInputType INPUT) {
        if (engine instanceof AndroidEngine) {
            return rxe_CSSLogTime(engine, INPUT).compose(engine.clickFn());
        } else if (engine instanceof IOSEngine) {
            final HMCircleScrollActionType THIS = this;

            return rxv_CSSTimePickerOpen(engine)
                .filter(HPBooleans::isFalse)
                .flatMap(a -> THIS.rxe_CSSLogTime(engine, INPUT))
                .compose(engine.clickFn())
                .map(HPBooleans::toTrue)
                .defaultIfEmpty(false);
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Select CSS date-time.
     * @param engine {@link Engine} instance.
     * @param date {@link Date} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_selectSpinnerDateTime(Engine, Date)
     */
    @NotNull
    default Flowable<?> rxa_selectCSSTime(@NotNull Engine<?> engine,
                                          @NotNull Date date) {
        return rxa_selectSpinnerDateTime(engine, date);
    }

    /**
     * Confirm CSS time.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #cssLogProgressDelay(Engine)
     * @see #rxe_CSSLogTime(Engine, HMCSSInputType)
     */
    @NotNull
    default Flowable<?> rxa_confirmCSSTime(@NotNull Engine<?> engine,
                                           @NotNull HMCSSInputType input) {
        return rxe_CSSLogTime(engine, input)
            .compose(engine.clickFn())
            .delay(cssLogProgressDelay(engine), TimeUnit.MILLISECONDS);
    }

    /**
     * Complete the CSS detail entry.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #randomSelectableTime()
     * @see #rxa_openCSSTimePicker(Engine, HMCSSInputType)
     * @see #rxa_selectCSSTime(Engine, Date)
     * @see #rxa_confirmCSSTime(Engine, HMCSSInputType)
     * @see #rxa_submitCSSEntry(Engine, HMCSSInputType)
     */
    @NotNull
    @SuppressWarnings("unchecked")
    default Flowable<?> rxa_completeCSSEntry(@NotNull Engine<?> engine,
                                             @NotNull HMCSSInputType input) {
        return Flowable
            .concatArray(rxa_submitCSSEntry(engine, input))
            .all(HPObjects::nonNull)
            .toFlowable();
    }

    /**
     * Log a new CSS entry by completing the CSS value and entry.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_completeCSSValue(Engine, HMCSSInputType)
     * @see #rxa_completeCSSEntry(Engine, HMCSSInputType)
     */
    @NotNull
    default Flowable<?> rxa_logCSSEntry(@NotNull Engine<?> engine,
                                        @NotNull HMCSSInputType input) {
        return Flowable.concatArray(
            rxa_completeCSSValue(engine, input),
            rxa_completeCSSEntry(engine, input)
        ).all(HPObjects::nonNull).toFlowable();
    }
}
