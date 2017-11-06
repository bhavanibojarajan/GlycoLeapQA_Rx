package com.holmusk.GlycoLeap.model;

import com.holmusk.HMUITestKit.model.HMInputType;
import com.holmusk.HMUITestKit.model.HMTextType;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.functional.Tuple;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkitcomponents.util.ValueRangeConverterType;

import java.util.Arrays;
import java.util.List;

public enum UserMode implements ErrorProviderType, ValueRangeConverterType<Integer> {
    GENERAL,
   MARSH;

    /**
     * Get the default {@link UserMode}.
     * @return {@link UserMode} instance.
     */
    @NotNull
    public static UserMode defaultUserMode() {
        return GENERAL;
    }
    /**
     * Get the default {@link UserMode} that is .
     * @return {@link UserMode} instance.
     */
    @NotNull
    public static UserMode defaultMARSHUserMode() {
        return MARSH;
    }

    @NotNull
    @Override
    public Converter<Integer> converter() {
        return a -> Double.valueOf(a).intValue();
    }
    /**
     * Check if the current {@link UserMode} is in the parent category.
     * @return {@link Boolean} instance.
     */
    public boolean isParent() {
        return this.equals(GENERAL);
    }


    /**
     * Check if the current {@link UserMode} is in the teen category.
     * @return {@link Boolean} instance.
     */
    public boolean isMARSHUSER() {
        switch (this) {
            case MARSH:

                return true;

            default:
                return false;
        }
    }

    /**
     * Get the default login credentials for the current {@link UserMode}.
     * @return {@link List} of {@link Tuple}.
     */
    @NotNull
    public List<Tuple<HMTextType,String>> loginCredentials() {
        switch (this) {
            case GENERAL:
                return Arrays.asList(
                        Tuple.of(TextInput.EMAIL, "Test50@gmail.com"),
                        Tuple.of(TextInput.PASSWORD, "Test50test")
                );

            case MARSH:

                return Arrays.asList(
                        Tuple.of(TextInput.EMAIL, "Test500@gmail.com"),
                        Tuple.of(TextInput.PASSWORD, "Test500test")
                );

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }
    /**
     * Get the personal information inputs for this {@link UserMode}.
     * @param platform {@link PlatformType} instance.
     * @return {@link List} of {@link HMInputType}.
     * @see #androidPersonalInfo()
     * @see #iOSPersonalInfo()
     */
    @NotNull
    public List<HMTextType> personalInfo(@NotNull PlatformType platform) {
        switch ((Platform)platform) {
            case ANDROID:
                return androidPersonalInfo();

            case IOS:
                return iOSPersonalInfo();

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }
    /**
     * Get personal info {@link HMInputType} for
     * {@link Platform#ANDROID}.
     * @return {@link List} of {@link HMInputType}.
     */
    @NotNull
    private List<HMTextType> androidPersonalInfo() {
        switch (this) {
            case GENERAL:
                return Arrays.asList(
                        TextInput.USER_NAME,
                       TextInput.EMAIL,
                        TextInput.PASSWORD
                );


            case MARSH:
                return Arrays.asList(
                        TextInput.USER_NAME,


                        TextInput.EMAIL,
                        TextInput.PASSWORD
                );

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get personal info {@link HMInputType} for
     * {@link Platform#IOS}.
     * @return {@link List} of {@link HMInputType}.
     */
    private List<HMTextType> iOSPersonalInfo() {
        switch (this) {
            case GENERAL:
                return Arrays.asList(
                        TextInput.USER_NAME,
                       TextInput.EMAIL,
                        TextInput.PASSWORD
                );

            case MARSH:
                return Arrays.asList(
                        TextInput.USER_NAME,
                        TextInput.EMAIL,
                        TextInput.PASSWORD
                );

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }
    /**
     * Get the register butto text for the current {@link UserMode} for

     * @return {@link String} value.
     */
    @NotNull
    public String registerButtonText() {
        switch (this) {
            case GENERAL:
                return "register_title_general";

            case MARSH:

                return "register_title_marsh";

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
            * Get {@link DashboardMode#ACTIVITY} keyword to be searched, because
     * each {@link UserMode} group has a different activity metric.
            * @return {@link String} value.
     */
    @NotNull
    public String dashboardActivityKeyword() {
        switch (this) {
            case GENERAL:
                return "dashboard_activity_title_mins";


            case MARSH:
                return "dashboard_activity_title_mins";

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

}
