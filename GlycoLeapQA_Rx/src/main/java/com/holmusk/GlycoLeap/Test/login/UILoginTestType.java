package com.holmusk.GlycoLeap.Test.login;

import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import com.holmusk.GlycoLeap.model.UserMode;
import com.holmusk.GlycoLeap.navigation.Screen;
import com.holmusk.GlycoLeap.navigation.type.ScreenInitType;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.javautilities.rx.CustomTestSubscriber;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.Test;

import java.util.List;

public interface UILoginTestType extends UIBaseTestType, ScreenInitType, LoginActionType {

    /**
     * Login with predefined credentials and verify that it works correctly.
     * @see #assertCorrectness(TestSubscriber)
     * @see #engine()
     * @see #rxa_login(Engine, List)
     * @see #rxa_loginWithDefaults(Engine, UserMode)
     * @see #rxn_dashboardTutorialInitialized(Engine)
     */
    @Test
    @SuppressWarnings("unchecked")
    default void test_loginInputs_shouldWork() {
        // Setup
        Engine<?> engine = engine();
        UserMode mode = UserMode.defaultUserMode();
        TestSubscriber subscriber = CustomTestSubscriber.create();

        // When
        Flowable.concatArray(
                rxa_navigate(mode, Screen.SPLASH, Screen.LOGIN),
                rxa_loginWithDefaults(engine, mode),

            /* Call this method to make sure all alerts are dismissed */
                rxn_dashboardTutorialInitialized(engine)
        ).all(HPObjects::nonNull).toFlowable().subscribe(subscriber);

        subscriber.awaitTerminalEvent();

        // Then
        assertCorrectness(subscriber);
    }

}
