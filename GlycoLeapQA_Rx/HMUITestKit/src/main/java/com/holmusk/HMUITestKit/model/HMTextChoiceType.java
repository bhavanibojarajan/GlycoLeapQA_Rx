package com.holmusk.HMUITestKit.model;

/**
 * Created by haipham on 23/5/17.
 */

import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.model.ChoiceInputType;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.ios.IOSView;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.javautilities.protocol.ClassNameProviderType;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkitcomponents.xpath.*;

import java.util.List;
import java.util.Optional;

/**
 * This interface provides methods to handle text-based choice inputs.
 */
public interface HMTextChoiceType extends HMChoiceType, ErrorProviderType {
    /**
     * Get all text choice inputs. Usually this interface is implemented by
     * a {@link Enum}, so this method should call {@link Enum} values.
     * @return {@link List} of {@link Item}
     */
    @NotNull List<? extends Item> allTextChoices();

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @param selected The selected {@link String} choice.
     * @return {@link XPath} instance.
     * @see HMChoiceType#androidTargetItemXP(InputHelperType, String)
     * @see Attributes#containsText(String)
     */
    @NotNull
    @Override
    default XPath androidTargetItemXP(@NotNull InputHelperType helper,
                                      @NotNull String selected) {
        Attributes attrs = Attributes.of(Platform.ANDROID);
        Attribute attribute = attrs.containsText(selected);
        return XPath.builder().addAttribute(attribute).build();
    }

    /**
     * Override this method to provide default implementation.
     * @param HELPER {@link InputHelperType} instance.
     * @param VALUE {@link String} value.
     * @return {@link Integer} value.
     * @see HMChoiceType#numericValue(InputHelperType, String)
     * @see #allTextChoices()
     */
    @Override
    default double numericValue(@NotNull final InputHelperType HELPER,
                                @NotNull final String VALUE) {
        List<? extends Item> values = allTextChoices();

        Optional<? extends Item> input = values.stream()
            .filter(a -> a.stringValue(HELPER).equals(VALUE))
            .findFirst();

        if (input.isPresent()) {
            return values.indexOf(input.get());
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @param value {@link Integer} value.
     * @return {@link String} value.
     * @see HMChoiceType#stringValue(InputHelperType, double)
     * @see #allTextChoices()
     */
    @NotNull
    @Override
    default String stringValue(@NotNull InputHelperType helper, double value) {
        return allTextChoices().get((int)value).toString();
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return Return {@link XPath} value.
     * @see ChoiceInputType#choicePickerXP(InputHelperType)
     * @see #androidChoicePickerXP(InputHelperType) )
     * @see #iOSScrollViewPickerXP(InputHelperType)
     */
    @NotNull
    @Override
    default XPath choicePickerXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        switch ((Platform)platform) {
            case ANDROID:
                return androidChoicePickerXP(helper);

            case IOS:
                return iOSScrollViewPickerXP(helper);

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} value.
     * @see ChoiceInputType#choicePickerItemXP(InputHelperType)
     * @see #androidChoicePickerItemXP(InputHelperType)
     */
    @NotNull
    @Override
    default XPath choicePickerItemXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        switch ((Platform)platform) {
            case ANDROID:
                return androidChoicePickerItemXP(helper);

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get the scroll view picker {@link XPath} for {@link Platform#ANDROID}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsID(String)
     */
    @NotNull
    default XPath androidChoicePickerXP(@NotNull InputHelperType helper) {
        Attributes attrs = Attributes.of(Platform.ANDROID);
        Attribute attribute = attrs.containsID("select_dialog_listview");
        return XPath.builder().addAttribute(attribute).build();
    }

    /**
     * Get the scroll view picker {@link XPath} for {@link Platform#IOS}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     */
    @NotNull
    default XPath iOSScrollViewPickerXP(@NotNull InputHelperType helper) {
        ClassNameProviderType param = IOSView.Type.UI_PICKER_WHEEL;
        CompoundAttribute attribute = CompoundAttribute.forClass(param);
        return XPath.builder().addAttribute(attribute).build();
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsID(String)
     */
    @NotNull
    @Override
    default XPath androidChoicePickerItemXP(@NotNull InputHelperType helper) {
        Attributes attrs = Attributes.of(Platform.ANDROID);
        Attribute attribute = attrs.containsID("text1");
        return XPath.builder().addAttribute(attribute).build();
    }

    @FunctionalInterface
    interface Item {
        /**
         * Use this {@link String} to locale a {@link Item} instance. This
         * can be helpful when we are calling
         * {@link HMTextChoiceType#numericValue(InputHelperType, String)}.
         * @param helper {@link InputHelperType} instance.
         * @return {@link String} value.
         */
        @NotNull String stringValue(@NotNull InputHelperType helper);
    }
}
