package com.holmusk.GlycoLeap.Test.base;

import com.holmusk.GlycoLeap.config.config;
import com.holmusk.GlycoLeap.model.UserMode;
import com.holmusk.GlycoLeap.navigation.type.BackwardNavigationType;
import com.holmusk.GlycoLeap.navigation.type.ForwardNavigationType;
import com.holmusk.GlycoLeap.navigation.type.SLScreenManagerType;
import com.holmusk.GlycoLeap.navigation.type.ScreenInitType;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.TestSubscriber;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.protocol.RetryProviderType;
import org.swiften.javautilities.rx.HPReactives;
import org.swiften.javautilities.test.HPTestNGs;
import org.swiften.javautilities.util.HPLog;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.kit.TestKit;
import org.swiften.xtestkit.test.BaseTestType;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public interface UIBaseTestType extends BaseTestType, BackwardNavigationType, ForwardNavigationType, ScreenInitType, SLScreenManagerType {
    /**
     * This {@link DataProvider} provides {@link org.swiften.xtestkit.base.Engine}
     * instances for constructor methods.
     * @return {@link Iterator} instance.
     */
    @NotNull
    @DataProvider(parallel = true)
    static Iterator<Object[]> engineProvider() {
        List<Object[]> data = new LinkedList<>();
        TestKit kit = config.TEST_KIT;

        for (int i = 0, count = config.runCount(); i < count; i++) {
            data.add(new Object[] { kit.engine(i) });
        }

        return data.iterator();
    }

    /**
     * This {@link DataProvider} provides {@link UserMode} instances that
     * do not care whether a guarantor is required.
     * @return {@link Iterator} instance.
     */
    @NotNull
    @DataProvider
    static Iterator<Object[]> generalUserModeProvider() {
        return HPTestNGs.oneFromEach(
//            UserMode.PARENT,
//            UserMode.TEEN_A18,

                UserMode.GENERAL
        ).iterator();
    }



    /**
     * Override this method to provide default implementation.
     * @return {@link TestKit} instance.
     */
    @NotNull
    @Override
    default TestKit testKit() {
        return config.TEST_KIT;
    }

    /**
     * Common method to check correctness of completed operations.
     * @param subscriber The {@link TestSubscriber} instance that received
     *                   all notifications.
     */
    default void assertCorrectness(@NotNull TestSubscriber subscriber) {
        subscriber.assertSubscribed();
        subscriber.assertNoErrors();
        subscriber.assertComplete();
        HPLog.printft("Test results: %s", HPReactives.nextEvents(subscriber));
    }

    @BeforeSuite
    default void beforeSuite() {
        testKit().beforeSuite();
    }

    @AfterSuite
    default void afterSuite() {
        testKit().afterSuite();
    }

    @BeforeClass
    default void beforeClass() {
        engine().beforeClass(RetryProviderType.DEFAULT);
    }

    @AfterClass
    default void afterClass() {
        engine().afterClass(RetryProviderType.DEFAULT);
    }

    @BeforeMethod
    default void beforeMethod() {
        registerScreenHolders();

        Engine<?> engine = engine();
//        CompositeDisposable disposable = masterDisposable();
//        Popup[] popups = Popup.values();
//        Disposable d1 = engine.rxa_pollAndDismissPopup(popups).subscribe();
//        disposable.add(d1);

        engine.beforeMethod(RetryProviderType.DEFAULT);
    }

    @AfterMethod
    default void afterMethod() {
        clearAllNodes();

//        CompositeDisposable disposable = masterDisposable();
//        disposable.dispose();

        engine().afterMethod(RetryProviderType.DEFAULT);
    }
    /**
     * Get the master {@link CompositeDisposable} for everything aside from
     * tests.
     * @return {@link CompositeDisposable} instance.
     */
    @NotNull CompositeDisposable masterDisposable();

}
