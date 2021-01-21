package com.ratiug.dev.zno_project;

import android.content.Context;
import android.graphics.ColorFilter;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.textfield.TextInputLayout;

public class CustomTextInputLayout extends TextInputLayout {
    public CustomTextInputLayout(Context context) {
        super(context);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setError(@Nullable CharSequence error) {
        ColorFilter defaultColorFilter = getBackgroundDefaultColorFilter();
        super.setError(error);
        //Reset EditText's background color to default.
        View layout = getChildAt(1);
        if (layout != null) {
            if (error != null && !"".equals(error.toString().trim())) {
                layout.setVisibility(VISIBLE);
            } else {
                layout.setVisibility(GONE);
            }
        }
        updateBackgroundColorFilter(defaultColorFilter);
    }

    @Override
    protected void drawableStateChanged() {
        ColorFilter defaultColorFilter = getBackgroundDefaultColorFilter();
        super.drawableStateChanged();
        //Reset EditText's background color to default.
        updateBackgroundColorFilter(defaultColorFilter);
    }

    private void updateBackgroundColorFilter(ColorFilter colorFilter) {
        if(getEditText() != null && getEditText().getBackground() != null)
            getEditText().getBackground().setColorFilter(colorFilter);
    }

    @Nullable
    private ColorFilter getBackgroundDefaultColorFilter() {
        ColorFilter defaultColorFilter = null;
        if(getEditText() != null && getEditText().getBackground() != null)
            defaultColorFilter = DrawableCompat.getColorFilter(getEditText().getBackground());
        return defaultColorFilter;
    }
}