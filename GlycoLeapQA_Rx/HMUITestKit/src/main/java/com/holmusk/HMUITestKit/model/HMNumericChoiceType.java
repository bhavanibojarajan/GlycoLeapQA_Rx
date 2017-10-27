package com.holmusk.HMUITestKit.model;

import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.android.model.AndroidNumericPickerInputType;
import org.swiften.xtestkit.base.model.ChoiceInputType;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkitcomponents.util.ValueRangeConverterType;
import org.swiften.xtestkitcomponents.xpath.XPath;

/**
 * Created by haipham on 6/4/17.
 */

/**
 * This interface provides methods to handle numeric choice selection.
 */
public interface HMNumericChoiceType extends
    AndroidNumericPickerInputType,
    HMChoiceType,
    ValueRangeConverterType<Integer>
{
    @NotNull
    @Override
    default Converter<Integer> converter() {
        return a -> (int)a;
    }

    //region Choice Picker View
    /**
     * Get the index of the choice picker, depending on the {@link PlatformType}
     * being tested.
     * @param helper {@link InputHelperType} instance.
     * @return {@link Integer} value.
     * @see ChoiceInputType#scrollablePickerIndex(InputHelperType)
     * @see #androidScrollablePickerIndex(InputHelperType)
     * @see #iOSScrollablePickerIndex(InputHelperType)
     */
    @Override
    default int scrollablePickerIndex(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        switch ((Platform)platform) {
            case ANDROID:
                return androidScrollablePickerIndex(helper);

            case IOS:
                return iOSScrollablePickerIndex(helper);

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return Return {@link XPath} value.
     * @see ChoiceInputType#choicePickerXP(InputHelperType)
     * @see #androidChoicePickerXP(InputHelperType)
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
}
