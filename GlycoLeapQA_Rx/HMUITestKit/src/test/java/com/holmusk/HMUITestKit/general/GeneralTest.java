package com.holmusk.HMUITestKit.general;

import com.holmusk.HMUITestKit.android.element.date.EEEMMMddInput;
import com.holmusk.HMUITestKit.model.HMCSSInputType;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.collection.HPIterables;
import org.swiften.javautilities.localizer.Localizer;
import org.swiften.javautilities.localizer.LocalizerType;
import org.swiften.javautilities.util.HPLog;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkit.util.TestHelper;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by haipham on 6/3/17.
 */
@SuppressWarnings("UndeclaredTests")
public final class GeneralTest {
    @Test(dataProviderClass = TestHelper.class, dataProvider = "platformProvider")
    public void test_EEEMMMddInput_shouldCreateCorrectXPath(@NotNull Platform platform) {
        // Setup
        InputHelperType helper = TestHelper.mockHelper(platform);
        EEEMMMddInput input = new EEEMMMddInput();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.MAY);
        Date date = calendar.getTime();
        String format = input.dateFormat();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String stringValue = formatter.format(date);
        double numericValue = calendar.getTimeInMillis();

        // When & Then
        HPLog.println(input.numericValue(helper, stringValue));
        HPLog.println(input.stringValue(helper, numericValue));
        HPLog.println(input.choicePickerItemXP(helper));
        HPLog.println(input.choicePickerXP(helper));
        HPLog.println(input.androidChoicePickerXP(helper));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test(dataProviderClass = TestHelper.class, dataProvider = "platformProvider")
    public void test_HMCSSInput_shouldCreateCorrectXPath(@NotNull final Platform PLATFORM) {
        // Setup
        HMCSSInputType input = helper -> HPIterables.asList();

        InputHelperType helper = new InputHelperType() {
            @NotNull
            @Override
            public LocalizerType localizer() {
                return Localizer.builder().build();
            }

            @NotNull
            @Override
            public PlatformType platform() {
                return PLATFORM;
            }

            @NotNull
            @Override
            public String platformName() {
                return PLATFORM.value();
            }
        };

        // When & Then
        HPLog.println(input.CSSLogTimeXP(helper));
    }
}
