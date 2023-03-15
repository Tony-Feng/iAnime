package com.project.ianime.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Class used to manage UI elements that render the data on the screen
 */
abstract class FragmentUiState: ViewHolder(){

    protected var compositeDisposable = CompositeDisposable()
    abstract fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    abstract fun onDestroyView()
}