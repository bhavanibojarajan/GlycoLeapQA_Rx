package com.holmusk.GlycoLeap.Test.css;

import com.holmusk.HMUITestKit.model.HMCSSInputType;
import com.holmusk.GlycoLeap.model.CSSInput;
import com.holmusk.GlycoLeap.model.UserMode;
import com.holmusk.GlycoLeap.navigation.Screen;
import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.javautilities.rx.CustomTestSubscriber;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public interface UICSSValueTestType extends UIBaseTestType, CSSValueActionType {
    @NotNull
    @DataProvider
    static Iterator<Object[]> cssDataProvider() {
        List<Object[]> data = new LinkedList<>();

        data.add(new Object[]{
                UserMode.GENERAL,
                Screen.WEIGHT_VALUE,
                Screen.WEIGHT_ENTRY,
                CSSInput.WEIGHT
        });

        return data.iterator();
    }



    }
