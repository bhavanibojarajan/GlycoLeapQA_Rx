package com.holmusk.HMUITestKit.model;

/**
 * Created by haipham on 12/6/17.
 */

import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.localizer.LocalizerType;
import org.swiften.xtestkit.android.AndroidView;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.ios.IOSView;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.javautilities.protocol.ClassNameProviderType;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkitcomponents.xpath.*;

import java.util.Collection;

/**
 * This interface provides methods to handle circle scroll selector-based
 * inputs.
 */
public interface HMCSSInputType extends ErrorProviderType {
    /**
     * Get {@link XPath} to locate the circle choice selector
     * {@link org.openqa.selenium.WebElement}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     */
    @NotNull
    default XPath CSSXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        if (platform.equals(Platform.IOS)) {
            ClassNameProviderType param = IOSView.Type.UI_TABLE_VIEW;
            CompoundAttribute cAttr = CompoundAttribute.forClass(param);
            return XPath.builder().addAttribute(cAttr).build();
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get {@link XPath} to locate the CSS value display.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     */
    @NotNull
    default XPath CSSValueDisplayXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        if (platform.equals(Platform.IOS)) {
            ClassNameProviderType tbv = IOSView.Type.UI_TABLE_VIEW;
            ClassNameProviderType tbc = IOSView.Type.UI_TABLE_VIEW_CELL;
            ClassNameProviderType stc = IOSView.Type.UI_STATIC_TEXT;

            return XPath.builder()
                .addAttribute(CompoundAttribute.forClass(tbv))
                .addAttribute(CompoundAttribute.forClass(tbc).withIndex(1))
                .addAttribute(CompoundAttribute.forClass(stc))
                .build();
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get {@link XPath} to locate the CSS value submit
     * {@link org.openqa.selenium.WebElement}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsText(String)
     * @see Attributes#containsID(String)
     */
    @NotNull
    default XPath CSSValueSubmitXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();
        Attributes attrs = Attributes.of(platform);

        if (platform.equals(Platform.ANDROID)) {
            Attribute attr = attrs.containsID("action_done");
            return XPath.builder().addAttribute(attr).build();
        } else if (platform.equals(Platform.IOS)) {
            LocalizerType localizer = helper.localizer();
            String localized = localizer.localize("css_title_next");
            Attribute attr = attrs.containsText(localized);
            return XPath.builder().addAttribute(attr).build();
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get {@link XPath} to locate the CSS entry submit.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsText(String)
     * @see #CSSValueSubmitXP(InputHelperType)
     */
    @NotNull
    default XPath CSSEntrySubmitXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        if (platform.equals(Platform.ANDROID)) {
            return CSSValueSubmitXP(helper);
        } else if (platform.equals(Platform.IOS)) {
            Attributes attrs = Attributes.of(platform);
            LocalizerType localizer = helper.localizer();
            String localized = localizer.localize("css_title_save");
            Attribute attr = attrs.containsText(localized);
            return XPath.builder().addAttribute(attr).build();
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get the CSS time display {@link org.openqa.selenium.WebElement}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsText(String)
     * @see Axes#followingSibling(AttributeType)
     */
    @NotNull
    default XPath CSSLogTimeXP(@NotNull InputHelperType helper) {
        LocalizerType localizer = helper.localizer();
        PlatformType platform = helper.platform();
        String localized1 = localizer.localize("css_title_dateTime");
        String localized2 = localizer.localize("css_title_selectTime");
        ClassNameProviderType param;

        if (platform.equals(Platform.ANDROID)) {
            param = AndroidView.Type.TEXT_VIEW;
        } else if (platform.equals(Platform.IOS)) {
            param = IOSView.Type.UI_STATIC_TEXT;
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }

        Attributes attrs = Attributes.of(platform);
        CompoundAttribute textCAttr = CompoundAttribute.forClass(param);

        return XPath.builder()
            .addAttribute(AttributeBlock.builder()
                .addAttribute(attrs.containsText(localized1))
                .addAttribute(attrs.containsText(localized2))
                .withJoiner(Joiner.OR)
                .build())
            .addAttribute(Axes.followingSibling(textCAttr))
            .build();
    }

    /**
     * Get the unit of measurement (possibly multiple values that express the
     * same unit but are written differently) for the current
     * {@link HMCSSInputType}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link Collection} of {@link String}.
     */
    @NotNull
    Collection<String> uom(@NotNull InputHelperType helper);
}
