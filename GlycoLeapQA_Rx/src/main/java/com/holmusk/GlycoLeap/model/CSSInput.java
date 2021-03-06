package com.holmusk.GlycoLeap.model;

import com.holmusk.HMUITestKit.model.HMCSSInputType;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.collection.HPIterables;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkitcomponents.xpath.Attribute;
import org.swiften.xtestkitcomponents.xpath.Attributes;
import org.swiften.xtestkitcomponents.xpath.XPath;

import java.util.Collection;

public enum CSSInput implements HMCSSInputType {
    WEIGHT,
    GULCOSE,
    HBA1C;


    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsID(String)
     * @see #androidCSSId()
     */
    @NotNull
    @Override
    public XPath CSSXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        if (platform.equals(Platform.ANDROID)) {
            Attributes attrs = Attributes.of(platform);
            String id = androidCSSId();
            Attribute attr = attrs.containsID(id);
            return XPath.builder().addAttribute(attr).build();
        } else {
            return HMCSSInputType.super.CSSXP(helper);
        }
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see HMCSSInputType#CSSValueDisplayXP(InputHelperType)
     * @see Attributes#containsID(String)
     * @see #androidCSSValueDisplayId()
     */
    @NotNull
    @Override
    public XPath CSSValueDisplayXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        if (platform.equals(Platform.ANDROID)) {
            Attributes attrs = Attributes.of(platform);
            String id = androidCSSValueDisplayId();
            Attribute attr = attrs.containsID(id);
            return XPath.builder().addAttribute(attr).build();
        } else {
            return HMCSSInputType.super.CSSValueDisplayXP(helper);
        }
    }

    /**
     * Get the CSS id for {@link Platform#ANDROID}.
     * @return {@link String} value.
     */
    @NotNull
    private String androidCSSId() {
        switch (this) {
            case GULCOSE:
                return "bis_add_gulcose";

            case WEIGHT:
                return "bis_add_weight";

            case HBA1C:
                return "bis_add_hba1c";
            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get the CSS value display id for {@link Platform#ANDROID}.
     * @return {@link String} value.
     */
    @NotNull
    private String androidCSSValueDisplayId() {
        switch (this) {
            case GULCOSE:
                return "tv_add_activity_tag_glucosevalue";

            case WEIGHT:
                return "tv_add_weight_tag_weightvalue";

            case HBA1C:
                return "tv_add_weight_tag_hba1c";

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link Collection} of {@link String}.
     * @see HMCSSInputType#uom(InputHelperType)
     */
   @NotNull
    @Override
    public Collection<String> uom(@NotNull InputHelperType helper) {
        switch (this) {
           /* case ACTIVITY:
                return HPIterables.asList("activityLog_title_minutes");*/

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }
}
