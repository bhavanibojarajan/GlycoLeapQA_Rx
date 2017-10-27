package com.holmusk.HMUITestKit.test.datetime;

import com.holmusk.HMUITestKit.android.element.date.EEEMMMddInput;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.bool.HPBooleans;
import org.swiften.javautilities.number.HPNumbers;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.android.element.date.AndroidDatePickerType;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.base.element.choice.ChoiceType;
import org.swiften.xtestkit.base.element.date.CalendarUnit;
import org.swiften.xtestkit.base.element.date.DateParam;
import org.swiften.xtestkit.base.element.date.DateProviderType;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkit.ios.element.date.IOSDatePickerType;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by haipham on 9/6/17.
 */
public interface HMDateTimeActionType extends ErrorProviderType {
    /**
     * Get a random {@link Date}.
     * @return {@link Date} instance.
     * @see HPNumbers#randomBetween(int, int)
     */
    @NotNull
    default Date randomSelectableTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.add(Calendar.HOUR_OF_DAY, HPNumbers.randomBetween(1, 3));
        calendar.add(Calendar.MINUTE, HPNumbers.randomBetween(10, 20));

        /* We set seconds to 30 to avoid under/overshot scrolls */
        calendar.set(Calendar.SECOND, 30);
        return calendar.getTime();
    }

    /**
     * Select date-time with a spinner/picker-wheel widget, using a {@link Date}
     * instance.
     * @param E {@link Engine} instance.
     * @param date {@link Date} instance.
     * @return {@link Flowable} instance.
     * @see Engine#rxa_selectChoice(ChoiceType)
     * @see Engine#rxa_selectDate(DateProviderType)
     * @see AndroidDatePickerType#TIME_NUMBER_PICKER_HH_mm
     * @see IOSDatePickerType#PICKER_WHEEL_MMMd_h_mm_a
     */
    @NotNull
    default Flowable<?> rxa_selectSpinnerDateTime(@NotNull final Engine<?> E,
                                                  @NotNull Date date) {
        if (E instanceof AndroidEngine) {
            DateProviderType dp = DateParam.builder().withDate(date).build();
            ChoiceType dateChoice = EEEMMMddInput.choiceParam(dp);

            DateProviderType tp = DateParam.builder()
                .withDate(date)
                .withCalendarUnits(CalendarUnit.HOUR_24, CalendarUnit.MINUTE)
                .withPicker(AndroidDatePickerType.TIME_NUMBER_PICKER_HH_mm)
                .build();

            return Flowable
                .concat(E.rxa_selectChoice(dateChoice), E.rxa_selectDate(tp))
                .all(HPBooleans::isTrue)
                .toFlowable();
        } else if (E instanceof IOSEngine) {
            DateProviderType dtParam = DateParam.builder()
                .withDate(date)
                .withCalendarUnits(
                    CalendarUnit.DAY,
                    CalendarUnit.MONTH,
                    CalendarUnit.HOUR_12,
                    CalendarUnit.MINUTE,
                    CalendarUnit.PERIOD
                )
                .withPicker(IOSDatePickerType.PICKER_WHEEL_MMMd_h_mm_a)
                .build();

            return E.rxa_selectDate(dtParam);
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }
}
