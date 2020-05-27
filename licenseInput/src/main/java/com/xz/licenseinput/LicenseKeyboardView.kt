package com.xz.licenseinput

import android.content.Context
import android.graphics.Color
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener

class LicenseKeyboardView : KeyboardView {

    private val TAG = "LicenseKeyboardView"
    private val textKeyboard = Keyboard(context, R.xml.province_short_keyboard)
    private val numKeyboard = Keyboard(context, R.xml.keyboard_car_number_letters)
    private var licenseEditText: LicenseEditText? = null

    //0 text,1 num
    private var inputType = 0

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        initKeyboardView(attributes)
    }

    constructor(context: Context, attributes: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {
        initKeyboardView(attributes)
    }


    private fun initKeyboardView(attributes: AttributeSet?) {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attributes, R.styleable.LicenseKeyboardView)
        inputType = obtainStyledAttributes.getInt(R.styleable.LicenseKeyboardView_inputType, 0)
        obtainStyledAttributes.recycle()
        isPreviewEnabled = false
        keyboard = if (inputType == 0) textKeyboard else numKeyboard
        onKeyboardActionListener = onKeyListener
    }

    fun bindLicenseEditText(licenseEditText: LicenseEditText) {
        this.licenseEditText = licenseEditText
        licenseEditText.addTextChangedListener {
            val text = it?.toString()
            if (text.isNullOrEmpty()){
                setInputType(0)
            }else{
                setInputType(1)
            }
        }
    }

    fun setInputType(inputType: Int) {
        this.inputType = inputType
        keyboard = if (inputType == 0) textKeyboard else numKeyboard
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        clearAnimation()
        if (visibility == View.VISIBLE) {
            animation = AnimationUtils.loadAnimation(context, R.anim.dialog_enter)
            animation.start()
        } else {
            animation = AnimationUtils.loadAnimation(context, R.anim.dialog_exit)
            animation.start()
        }
    }

    private fun deleteLase() {
        val editableText = licenseEditText?.editableText
        if (editableText?.length ?: 0 > 0)
            editableText?.delete(editableText.lastIndex, editableText.lastIndex+1)
    }

    private fun appendText(text: CharSequence?){
        licenseEditText?.append(text)
    }

    private val onKeyListener = object : OnKeyboardActionListener {
        override fun swipeRight() {
        }

        override fun onPress(primaryCode: Int) {
            when (primaryCode) {
                64578 -> {
                    setInputType(1)
                }
                646394 -> {
                    setInputType(0)
                }
                -5 -> {
                    //删除
                    deleteLase()
                }
            }
        }

        override fun onRelease(primaryCode: Int) {
        }

        override fun swipeLeft() {
        }

        override fun swipeUp() {
        }

        override fun swipeDown() {
        }

        override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        }

        override fun onText(text: CharSequence?) {
            appendText(text)
        }
    }

}