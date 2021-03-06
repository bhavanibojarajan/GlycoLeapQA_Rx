package com.holmusk.GlycoLeap.Test.login;

import com.holmusk.GlycoLeap.Test.base.BaseValidationType;
import com.holmusk.GlycoLeap.model.TextInput;
import com.holmusk.HMUITestKit.model.HMInputType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.swiften.javautilities.localizer.LCFormat;
import org.swiften.javautilities.localizer.LocalizerType;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.javautilities.protocol.ClassNameProviderType;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.android.AndroidView;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkit.ios.IOSView;
import org.swiften.xtestkitcomponents.xpath.Attribute;
import org.swiften.xtestkitcomponents.xpath.Attributes;
import org.swiften.xtestkitcomponents.xpath.CompoundAttribute;
import org.swiften.xtestkitcomponents.xpath.XPath;

public interface LoginValidationType extends BaseValidationType {
    /**
     * Get the submit button {@link WebElement}.
     * @param ENGINE {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see Attributes#containsText(String)
     * @see Engine#rxe_withXPath(XPath...)
     */
    @NotNull
    default Flowable<WebElement> rxe_submit(@NotNull final Engine<?> ENGINE) {
        LocalizerType localizer = ENGINE.localizer();
        Attributes attrs = Attributes.of(ENGINE);
        ClassNameProviderType clsName;
        String title;

        if (ENGINE instanceof AndroidEngine) {
            clsName = AndroidView.Type.BUTTON;
            title = "login_title_signIn";
        } else if (ENGINE instanceof IOSEngine) {
            clsName = IOSView.Type.UI_BUTTON;
            title = "login_title_submit";
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }

        Attribute attribute = attrs.containsText(localizer.localize(title));

        CompoundAttribute cAttr = CompoundAttribute
                .single(attribute)
                .withClass(clsName);

        XPath xpath = XPath.builder().addAttribute(cAttr).build();
        return ENGINE.rxe_withXPath(xpath).firstElement().toFlowable();
    }

    /**
     * Get the forgot password button {@link WebElement}.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see Engine#rxe_containsText(String...)
     */
    @NotNull
    default Flowable<WebElement> rxe_forgotPassword(@NotNull Engine<?> engine) {
        return engine
                .rxe_containsText("login_title_forgotPassword")
                .firstElement()
                .toFlowable();
    }

    /**
     * Get the register button {@link WebElement}.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see Attributes#containsText(String)
     * @see Engine#rxe_containsText(LCFormat...)
     * @see Engine#rxe_withXPath(XPath...)
     */
    @NotNull
    default Flowable<WebElement> rxe_loginRegister(@NotNull Engine<?> engine) {
        if (engine instanceof AndroidEngine) {
            return engine
                    .rxe_containsText("login_title_register")
                    .firstElement()
                    .toFlowable();
        } else if (engine instanceof IOSEngine) {
            LocalizerType localizer = engine.localizer();
            String localized = localizer.localize("login_title_register");
            Attributes attrs = Attributes.of(engine);

            Attribute attribute = attrs.containsText(localized);

            CompoundAttribute cAttr = CompoundAttribute.builder()
                    .addAttribute(attribute)
                    .withClass(IOSView.Type.UI_LINK)
                    .build();

            XPath xpath = XPath.builder().addAttribute(cAttr).build();
            return engine.rxe_withXPath(xpath).firstElement().toFlowable();
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Validate {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_editField(Engine, HMInputType)
     * @see #rxe_loginRegister(Engine)
     * @see #rxe_submit(Engine)
     * @see #rxe_forgotPassword(Engine)
     */
    @NotNull
    @SuppressWarnings("unchecked")
    default Flowable<?> rxv_loginScreen(@NotNull Engine<?> engine) {
        return Flowable
                .mergeArray(
                        rxe_editField(engine, TextInput.EMAIL),
                        rxe_editField(engine, TextInput.PASSWORD),
                        rxe_loginRegister(engine),
                        rxe_submit(engine),
                        rxe_forgotPassword(engine)
                )
                .all(HPObjects::nonNull)
                .toFlowable();
    }
}
