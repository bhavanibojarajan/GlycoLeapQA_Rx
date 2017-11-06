package com.holmusk.GlycoLeap.navigation.type;

import com.holmusk.GlycoLeap.model.CardType;
import com.holmusk.GlycoLeap.model.UserMode;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;

import java.util.concurrent.TimeUnit;

public interface ForwardNavigationType extends
        WelcomeActionType,
        LoginActionType,
        ForgotPasswordActionType,
        RegisterModeActionType,
        DOBPickerActionType,
        PersonalInfoActionType,
        DashboardActionType,
        SearchActionType,
        PhotoPickerActionType,
        LogMealActionType,
        MealPageActionType,
        ChatActionType,
        LogWeightActionType,
        LogActivityActionType,
        SettingActionType
{

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#SPLASH}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#WELCOME}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #splashDelay(Engine)
     */
    @NotNull
    default Flowable<?> rxn_splash_welcome(@NotNull Engine<?> engine) {
        return Flowable.timer(splashDelay(engine), TimeUnit.MILLISECONDS);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#WELCOME}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_loginFromWelcome(Engine)
     */
    @NotNull
    default Flowable<?> rxn_welcome_login(@NotNull Engine<?> engine) {
        return rxa_loginFromWelcome(engine);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#FORGOT_PASSWORD}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_forgotPasswordFromLogin(Engine)
     */
    @NotNull
    default Flowable<?> rxn_login_forgotPassword(@NotNull Engine<?> engine) {
        return rxa_forgotPasswordFromLogin(engine);
    }
    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#REGISTER}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_registerFromLogin(Engine)
     */
    @NotNull
    default Flowable<?> rxn_login_register(@NotNull Engine<?> engine) {
        return rxa_registerFromLogin(engine);
    }


    /**
     * Note that a dialog may pop up asking for push notification permission,
     * so we accept by default.
     * {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD_TUTORIAL}
     * @param ENGINE {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_loginWithDefaults(Engine, UserMode)
     */
    @NotNull
    default Flowable<?> rxn_login_tutorial(@NotNull final Engine<?> ENGINE,
                                           @NotNull UserMode mode) {
        return rxa_loginWithDefaults(ENGINE, mode);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#WELCOME}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#REGISTER}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_registerFromWelcome(Engine)
     */
    @NotNull
    default Flowable<?> rxn_welcome_register(@NotNull Engine<?> engine) {
        return rxa_registerFromWelcome(engine);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#REGISTER}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#SHA}
     * @return {@link Flowable} instance.
     * @see #rxa_SHAFromRegister(Engine, UserMode)
     */
    default Flowable<?> rxn_register_sha(@NotNull Engine<?> ENGINE,
                                         @NotNull UserMode mode) {
        return rxa_SHAFromRegister(ENGINE, mode);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#VALID_AGE}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#PERSONAL_INFO}
     * @param engine {@link Engine} instance.
     * @param mode {@link UserMode} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_completeValidAgeInputs(Engine, UserMode)
     */
    @NotNull
    default Flowable<?> rxn_validAge_personalInfo(@NotNull Engine<?> engine,
                                                  @NotNull UserMode mode) {
        return rxa_completeValidAgeInputs(engine, mode);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#PERSONAL_INFO}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#ADDRESS_INFO}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_completePersonalInfo(Engine, UserMode)
     */
    @NotNull
    default Flowable<?> rxn_personalInfo_addressInfo(@NotNull Engine<?> engine,
                                                     @NotNull UserMode mode) {
        return rxa_completePersonalInfo(engine, mode);
    }


    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#USE_APP_NOW}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD_TUTORIAL}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_useAppNow(Engine)
     */
    @NotNull
    default Flowable<?> rxn_useApp_tutorial(@NotNull Engine<?> engine) {
        return rxa_useAppNow(engine);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD_TUTORIAL}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_dismissDashboardTutorial(Engine)
     */
    @NotNull
    default Flowable<?> rxn_tutorial_dashboard(@NotNull Engine<?> engine) {
        return rxa_dismissDashboardTutorial(engine);
    }

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#ADD_CARD}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_openCardAddMenu(Engine)
     */
    @NotNull
    default Flowable<?> rxn_dashboard_addCard(@NotNull Engine<?> engine) {
        return rxa_openCardAddMenu(engine);
    }


    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#SEARCH}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_toggleDashboardSearch(Engine)
     */
    @NotNull
    default Flowable<?> rxn_dashboard_search(@NotNull Engine<?> engine) {
        return rxa_toggleDashboardSearch(engine);
    }

    /**
     * We can access the photo picker by logging a new meal.
     * When the user opens the meal-logging screen, a dialog popup may appear
     * asking for camera permission.
     * {@link com.holmusk.GlycoLeap.navigation.Screen#ADD_CARD}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#PHOTO_PICKER}
     * @param ENGINE {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_addCard(Engine, CardType)
     */
    @NotNull
    default Flowable<?> rxn_addCard_photoPicker(@NotNull final Engine<?> ENGINE) {
        return rxa_addCard(ENGINE, CardType.MEAL);
    }

    /**
     * We skip photo selection by default.
     * {@link com.holmusk.Glyco.navigation.Screen#PHOTO_PICKER}
     * {@link com.holmusk.SuperLeapQA.navigation.Screen#MEAL_ENTRY}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_skipPhoto(Engine)
     */
    @NotNull
    default Flowable<?> rxn_photoPicker_logMeal(@NotNull Engine<?> engine) {
        return rxe_skipPhoto(engine).compose(engine.clickFn());
    }


}
