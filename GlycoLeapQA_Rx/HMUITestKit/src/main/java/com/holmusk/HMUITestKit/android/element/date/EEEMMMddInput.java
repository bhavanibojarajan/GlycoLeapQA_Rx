package com.holmusk.HMUITestKit.android.element.date;

/**
 * Created by haipham on 6/3/17.
 */

import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.localizer.LocalizerType;
import org.swiften.javautilities.util.HPLog;
import org.swiften.xtestkit.android.AndroidView;
import org.swiften.xtestkit.android.model.AndroidNumericPickerInputType;
import org.swiften.xtestkit.base.element.choice.ChoiceParam;
import org.swiften.xtestkit.base.element.choice.ChoiceType;
import org.swiften.xtestkit.base.element.date.DateProviderType;
import org.swiften.xtestkit.base.model.ChoiceInputType;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.javautilities.protocol.ClassNameProviderType;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkitcomponents.xpath.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class provides date selection methods for custom
 * {@link AndroidView.Type#NUMBER_PICKER}
 * date selection involving weekday/month/day.
 */
public class EEEMMMddInput implements AndroidNumericPickerInputType, ErrorProviderType {
    /**
     * Get {@link ChoiceType} parameter object for
     * {@link org.swiften.xtestkit.android.element.choice.AndroidChoiceSelectorType#rxa_selectChoice(ChoiceType)}.
     * @param param {@link DateProviderType} instance.
     * @return {@link ChoiceType} instance.
     * @see ChoiceParam.Builder#withGeneralMode()
     * @see ChoiceParam.Builder#withInput(ChoiceInputType)
     * @see ChoiceParam.Builder#withSelectedChoice(String)
     * @see DateProviderType#date()
     * @see EEEMMMddInput#dateFormat()
     */
    @NotNull
    public static ChoiceType choiceParam(@NotNull DateProviderType param) {
        EEEMMMddInput input = new EEEMMMddInput();
        Date date = param.date();
        String format = input.dateFormat();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String selected = formatter.format(date);
        HPLog.printft("Selecting %s for date", selected);

        return ChoiceParam.builder()
            .withGeneralMode()
            .withInput(input)
            .withSelectedChoice(selected)
            .build();
    }

    public EEEMMMddInput() {}

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see #androidChoicePickerXP(InputHelperType)
     */
    @NotNull
    @Override
    public XPath choicePickerXP(@NotNull InputHelperType helper) {
        return androidChoicePickerXP(helper);
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see #androidChoicePickerItemXP(InputHelperType)
     */
    @NotNull
    @Override
    public XPath choicePickerItemXP(@NotNull InputHelperType helper) {
        return androidChoicePickerItemXP(helper);
    }

    /**
     * Override this method to provide default implementation.
     * We use {@link Axes#precedingSibling(AttributeType)} because this
     * {@link AndroidView.Type#NUMBER_PICKER} is usually used in front of a
     * {@link AndroidView.Type#TIME_PICKER}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see AndroidNumericPickerInputType#androidChoicePickerXP(InputHelperType)
     * @see Attribute#not()
     * @see Attributes#of(PlatformType)
     * @see Attributes#ofClass(ClassNameProviderType)
     * @see Axes#precedingSibling(AttributeType)
     * @see CompoundAttribute#single(AttributeType)
     * @see XPath.Builder#addAttribute(CompoundAttribute)
     * @see AndroidView.Type#NUMBER_PICKER
     * @see AndroidView.Type#TIME_PICKER
     * @see Platform#ANDROID
     */
    @NotNull
    @Override
    public XPath androidChoicePickerXP(@NotNull InputHelperType helper) {
        ClassNameProviderType npClass = AndroidView.Type.NUMBER_PICKER;
        ClassNameProviderType tpClass = AndroidView.Type.TIME_PICKER;
        Attributes attributes = Attributes.of(Platform.ANDROID);

        return XPath.builder()
            .addAttribute(attributes.ofClass(tpClass))
            .addAttribute(Axes.precedingSibling(attributes.ofClass(npClass)))
            .build();
    }

    /**
     * Override this method to provide default implementation.
     * The {@link String} value should be in the format "EEE, MMM dd". We
     * need to use {@link java.text.SimpleDateFormat} to parse it into a
     * {@link java.util.Date}, upon which it will be converted to a numeric
     * value using {@link Calendar#getTimeInMillis()}.
     * @param helper {@link InputHelperType} instance.
     * @param value {@link String} value.
     * @return {@link Double} value.
     * @see Calendar#getTimeInMillis()
     * @see ChoiceInputType#numericValue(InputHelperType, String)
     * @see InputHelperType#localizer()
     * @see LocalizerType#localize(String)
     * @see #dateFormat()
     * @see #NOT_AVAILABLE
     */
    @Override
    public double numericValue(@NotNull InputHelperType helper,
                               @NotNull String value) {
        LocalizerType localizer = helper.localizer();
        String format = dateFormat();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String today = localizer.localize("date_title_today");
        Date date;

        /* If the date is today, the text will be something like "Today XX" */
        if (value.contains(today)) {
            date = new Date();
        } else {
            try {
                date = formatter.parse(value);
            } catch (ParseException e) {
                HPLog.printft("Error parsing %s", value);
                throw new RuntimeException(NOT_AVAILABLE);
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * Override this method to provide default implementation.
     * Since the numeric value should be a long millis, we need to create
     * a {@link Date} instance, then convert it into {@link String} using
     * a specified date format.
     * @param helper {@link InputHelperType} instance.
     * @param value {@link Double} value.
     * @return {@link String} value.
     * @see #dateFormat()
     */
    @NotNull
    @Override
    public String stringValue(@NotNull InputHelperType helper, double value) {
        String format = dateFormat();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date((long)value);
        return formatter.format(date);
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link Double} value.
     * @see AndroidNumericPickerInputType#numericValueStep(InputHelperType)
     */
    @Override
    public double numericValueStep(@NotNull InputHelperType helper) {
        return 24 * 3600 * 1000;
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link Double} value.
     * @see ChoiceInputType#swipeRatio(InputHelperType)
     */
    @Override
    public double swipeRatio(@NotNull InputHelperType helper) {
        return 1d;
    }

    /**
     * Get the date format with which to parse a {@link Date}, or create
     * {@link Date} from {@link String}.
     * @return {@link String} value.
     */
    @NotNull
    public String dateFormat() {
        return "EEE, MMM dd";
    }
}
