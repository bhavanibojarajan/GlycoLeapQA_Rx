package com.holmusk.GlycoLeap.navigation.type;

import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;

public interface BackwardNavigationType extends
        BaseActionType{

    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#WELCOME}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_clickBackButton(Engine)
     */
    @NotNull
    default Flowable<?> rxn_login_welcome(@NotNull Engine<?> engine) {
        return rxa_clickBackButton(engine);
    }


    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#REGISTER}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#WELCOME}
     * @param ENGINE {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_clickBackButton(Engine)
     */
    @NotNull
    default Flowable<?> rxn_register_welcome(@NotNull final Engine<?> ENGINE) {
        return rxa_clickBackButton(ENGINE);
    }



    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#PERSONAL_INFO}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#VALID_AGE}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_clickBackButton(Engine)
     */
    @NotNull
    default Flowable<?> rxn_personalInfo_validAge(@NotNull Engine<?> engine) {
        return rxa_clickBackButton(engine);
    }


    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#SEARCH}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#DASHBOARD}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_cancelSearch(Engine)
     */
    @NotNull
    default Flowable<?> rxn_search_dashboard(@NotNull Engine<?> engine) {
        return rxa_cancelSearch(engine);
    }



    /**
     * {@link com.holmusk.GlycoLeap.navigation.Screen#CHAT}
     * {@link com.holmusk.GlycoLeap.navigation.Screen#MEAL_PAGE}
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxa_dismissChatWindow(Engine)
     */
    @NotNull
    default Flowable<?> rxn_chat_mealPage(@NotNull Engine<?> engine) {
        return rxa_dismissChatWindow(engine);
    }
}
