package com.holmusk.HMUITestKit.test.circlescrollselector;

import com.holmusk.HMUITestKit.model.HMCSSInputType;
import com.holmusk.HMUITestKit.test.base.HMBaseValidationType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.swiften.javautilities.localizer.LocalizerType;
import org.swiften.javautilities.util.HPLog;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkitcomponents.xpath.XPath;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by haipham on 9/6/17.
 */
public interface HMCircleScrollValidationType extends HMBaseValidationType {
    /**
     * Get the circle scroll selector view.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see HMCSSInputType#CSSXP(InputHelperType)
     */
    @NotNull
    default Flowable<WebElement> rxe_CSSSView(@NotNull Engine<?> engine,
                                              @NotNull HMCSSInputType input) {
        XPath xpath = input.CSSXP(engine);
        return engine.rxe_withXPath(xpath);
    }

    /**
     * Get the CSS value display {@link WebElement}.
     * If we are testing on {@link org.swiften.xtestkit.mobile.Platform#IOS},
     * we can use a common locator query since CSS-related operations share
     * certain common characteristics.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see HMCSSInputType#CSSValueDisplayXP(InputHelperType)
     */
    @NotNull
    default Flowable<WebElement> rxe_CSSValueDisplay(@NotNull Engine<?> engine,
                                                     @NotNull HMCSSInputType input) {
        XPath xpath = input.CSSValueDisplayXP(engine);
        return engine.rxe_withXPath(xpath).firstElement().toFlowable();
    }

    /**
     * Get the value submit {@link WebElement} for circle scroll-related
     * content logging.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see HMCSSInputType#CSSValueSubmitXP(InputHelperType)
     */
    @NotNull
    default Flowable<WebElement> rxe_CSSValueSubmit(@NotNull Engine<?> engine,
                                                    @NotNull HMCSSInputType input) {
        XPath xpath = input.CSSValueSubmitXP(engine);
        return engine.rxe_withXPath(xpath);
    }

    /**
     * Get the detail submit {@link WebElement} for circle scroll-related
     * content logging.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see HMCSSInputType#CSSEntrySubmitXP(InputHelperType)
     */
    @NotNull
    default Flowable<WebElement> rxe_CSSDetailEntrySubmit(@NotNull Engine<?> engine,
                                                          @NotNull HMCSSInputType input) {
        XPath xpath = input.CSSEntrySubmitXP(engine);
        return engine.rxe_withXPath(xpath);
    }

    /**
     * Get the CSS-related log time display {@link WebElement}.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see HMCSSInputType#CSSLogTimeXP(InputHelperType)
     */
    @NotNull
    default Flowable<WebElement> rxe_CSSLogTime(@NotNull Engine<?> engine,
                                                @NotNull HMCSSInputType input) {
        XPath xpath = input.CSSLogTimeXP(engine);
        return engine.rxe_withXPath(xpath).firstElement().toFlowable();
    }

    /**
     * Validate CSS value selection screen.
     * @param engine {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_CSSSView(Engine, HMCSSInputType)
     * @see #rxe_CSSValueSubmit(Engine, HMCSSInputType)
     */
    @NotNull
    @SuppressWarnings("unchecked")
    default Flowable<?> rxv_CSSValue(@NotNull Engine<?> engine,
                                     @NotNull HMCSSInputType input) {
        return Flowable.mergeArray(
            engine.rxe_containsText("css_title_dragTheCircle"),
            rxe_CSSSView(engine, input),
            rxe_CSSValueSubmit(engine, input)
        ).all(HPObjects::nonNull).toFlowable();
    }

    /**
     * Check if the CSS time picker is visible on the screen.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     */
    @NotNull
    default Flowable<Boolean> rxv_CSSTimePickerOpen(@NotNull Engine<?> engine) {
        if (engine instanceof IOSEngine) {
            return engine
                .rxe_containsText("date_title_today")
                .firstElement()
                .toFlowable()
                .map(WebElement::isDisplayed)
                .onErrorReturnItem(false);
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get the currently displayed CSS value.
     * @param ENGINE {@link Engine} instance.
     * @param input {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see Engine#getText(WebElement)
     * @see #rxe_CSSValueDisplay(Engine, HMCSSInputType)
     */
    @NotNull
    default Flowable<String> rxe_selectedCSSValue(@NotNull final Engine<?> ENGINE,
                                                  @NotNull HMCSSInputType input) {
        return rxe_CSSValueDisplay(ENGINE, input).map(ENGINE::getText)
            .doOnNext(a -> HPLog.printft("Selected value: %s", a));
    }

    /**
     * Convert the value emitted by
     * {@link #rxe_selectedCSSValue(Engine, HMCSSInputType)} to a numeric
     * value.
     * @param ENGINE {@link Engine} instance.
     * @param INPUT {@link HMCSSInputType} instance.
     * @return {@link Flowable} instance.
     * @see HMCSSInputType#uom(InputHelperType)
     * @see #rxe_selectedCSSValue(Engine, HMCSSInputType)
     */
    @NotNull
    default Flowable<Double> rxe_selectedCSSNumericValue(
        @NotNull final Engine<?> ENGINE,
        @NotNull final HMCSSInputType INPUT
    ) {
        final HMCircleScrollValidationType THIS = this;
        final LocalizerType LOCALIZER = ENGINE.localizer();
        Collection<String> units = INPUT.uom(ENGINE);

        return Flowable.fromIterable(units)
            .map(LOCALIZER::localize)
            .flatMap(a -> THIS.rxe_selectedCSSValue(ENGINE, INPUT)
                .map(b -> b.replaceAll(a, ""))
                .map(b -> b.replaceAll("\\.", ""))
                .map(Double::valueOf))
            .firstElement()
            .toFlowable();
    }

    /**
     * Check whether a CSS log has a particular {@link Date}.
     * @param ENGINE {@link Engine} instance.
     * @param DATE {@link Date} instance.
     * @return {@link Flowable} instance.
     */
    @NotNull
    default Flowable<?> rxv_hasCSSTime(@NotNull final Engine<?> ENGINE,
                                       @NotNull final Date DATE) {
        return Flowable
            .fromArray(
                "dd/MM/yyyy h:mm a",
                "M/d/yy, h:mm a",
                "M/d/yy",
                "EEE, MMM dd"
            )
            .map(SimpleDateFormat::new)
            .map(a -> a.format(DATE))
            .toList()
            .map(a -> a.toArray(new String[a.size()]))
            .toFlowable()
            .flatMap(ENGINE::rxe_containsText)
            .firstElement()
            .toFlowable();
    }
}
