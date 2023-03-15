package com.project.ianime.widget

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.rxjava3.core.Observable

fun TextInputLayout.setTextChangeListener(): Observable<Boolean> {
    return Observable.create { emitter ->
        editText?.addTextChangedListener {
            //TODO: Add validation in onNext
            emitter.onNext(true)
        }
    }

}