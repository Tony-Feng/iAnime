package com.project.ianime.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.rxjava3.core.Observable


class AnimeEditText : TextInputLayout {

    private val text: String
        get() = editText?.text.toString()

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int): super(
        context,
        attributeSet,
        defStyleAttr
    )

    fun setTextChangeListener(): Observable<Boolean>{
        return Observable.create { emitter ->
            editText?.addTextChangedListener {
                emitter.onNext(isValid())
            }
        }
    }

    private fun isValid(optional: Boolean = false): Boolean{
        // Add optional choice for edit text
        if (optional && text == ""){
            return true
        }
        //TODO: Handle required cases
        return true
    }
}
