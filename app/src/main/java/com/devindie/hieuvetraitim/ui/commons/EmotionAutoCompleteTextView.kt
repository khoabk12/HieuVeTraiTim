package com.devindie.hieuvetraitim.ui.commons

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View.OnFocusChangeListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView

class EmotionAutoCompleteTextView : AppCompatAutoCompleteTextView {
    private val _onFocusChangeListener = OnFocusChangeListener { p0, p1 ->
        if (p1) {
            showDropDown()
        }
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setAutoCompleteArray(autocompleteArrays: Array<String>) {
        setAdapter(
            ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                autocompleteArrays
            )
        )
        setOnTouchListener { p0, p1 ->
            showDropDown()
            false
        }
        onFocusChangeListener = _onFocusChangeListener
//        addTextChangedListener(_onShowDropdownTextWatcher)
    }
}