package xyz.aprildown.theme.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.navigation.NavigationView
import xyz.aprildown.theme.R
import xyz.aprildown.theme.Theme.Companion.get
import xyz.aprildown.theme.utils.adjustAlpha

internal class ThemeNavigationView(
    context: Context,
    attrs: AttributeSet? = null
) : NavigationView(context, attrs) {

    init {
        invalidateColors(get().colorPrimary, get().isDark)
    }

    private fun invalidateColors(selectedColor: Int, isDark: Boolean) {
        val baseColor = if (isDark) Color.WHITE else Color.BLACK
        val unselectedIconColor = baseColor.adjustAlpha(.54f)
        val unselectedTextColor = baseColor.adjustAlpha(.87f)

        val selectedItemBgColor = ContextCompat.getColor(
            context,
            if (isDark) R.color.ate_navigation_drawer_selected_dark
            else R.color.ate_navigation_drawer_selected_light
        )

        val iconSl = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(unselectedIconColor, selectedColor)
        )
        val textSl = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(unselectedTextColor, selectedColor)
        )

        itemTextColor = textSl
        itemIconTintList = iconSl

        val bgDrawable = StateListDrawable()
        bgDrawable.addState(
            intArrayOf(android.R.attr.state_checked),
            ColorDrawable(selectedItemBgColor)
        )
        itemBackground = bgDrawable
    }
}
