package com.holmusk.GlycoLeap.Test.TestStart;



import com.holmusk.GlycoLeap.Test.base.UIBaseTest;
import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import com.holmusk.GlycoLeap.Test.login.UILoginTestType;
import com.holmusk.HMUITestKit.model.HMCSSInputType;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.javautilities.rx.CustomTestSubscriber;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.Factory;
import com.holmusk.GlycoLeap.Test.logweight.UILogWeightTestType;
import com.holmusk.GlycoLeap.Test.css.UICSSValueTestType;
import org.testng.annotations.Test;

public class UILogicValidation extends UIBaseTest implements UIBaseTestType,
               UILoginTestType,
        UILogWeightTestType,
        UICSSValueTestType

{
    @Factory(
            dataProviderClass = UIBaseTestType.class,
            dataProvider = "engineProvider"
    )
    public UILogicValidation(@NotNull Engine<?> engine) {
        super(engine);
    }

}
