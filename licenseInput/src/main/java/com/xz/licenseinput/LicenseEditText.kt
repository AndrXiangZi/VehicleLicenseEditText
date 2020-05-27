package com.xz.licenseinput

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * 自定义键盘输入(车牌号),输入框在最底部的时候会有问题,键盘无法把输入框顶上去
 */
class LicenseEditText(context: Context, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatEditText(context, attrs) {

    private val bottomSheetDialog:BottomSheetDialog = BottomSheetDialog(context,R.style.dialog)
    private var licenseKeyboardView:LicenseKeyboardView? = null

    init {
        bottomSheetDialog.setContentView(R.layout.dialog_license_keyboard)
        licenseKeyboardView = bottomSheetDialog.findViewById<LicenseKeyboardView>(R.id.licenseKeyboardView)
        licenseKeyboardView?.bindLicenseEditText(this)
    }

    //1.隐藏系统弹出的键盘
    //什么时候会弹出键盘 1.获取焦点,2输入框点击
    //2.弹出自定义键盘

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                //隐藏软键盘
                hintSoftInput()
                return false
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused) {
            showLicenseKeyboard()
        }else{
            hintLicenseKeyboard()
        }
    }

    /**
     * 隐藏软键盘
     */
    private fun hintSoftInput() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive) {
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        }

        showLicenseKeyboard()
        this.requestFocus()

    }

    private fun showLicenseKeyboard() {
        if (!bottomSheetDialog.isShowing){
            bottomSheetDialog.show()
        }
    }

    private fun hintLicenseKeyboard() {
        bottomSheetDialog.dismiss()
    }


}