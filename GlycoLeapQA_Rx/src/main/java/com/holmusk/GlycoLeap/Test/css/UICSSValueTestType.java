package com.holmusk.GlycoLeap.Test.css;

import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import com.holmusk.GlycoLeap.model.CSSInput;
import com.holmusk.GlycoLeap.model.UserMode;
import com.holmusk.GlycoLeap.navigation.Screen;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public interface UICSSValueTestType extends UIBaseTestType, CSSValueActionType {
    @NotNull
    @DataProvider
    static Iterator<Object[]> cssDataProvider() {
        List<Object[]> data = new LinkedList<>();

        data.add(new Object[] {
                UserMode.GENERAL,
                Screen.WEIGHT_VALUE,
                Screen.WEIGHT_ENTRY,
                CSSInput.WEIGHT;
        });


    }
