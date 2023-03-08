package com.project.ianime.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Class used to manage UI elements that render the data on the screen
 */
abstract class FragmentUiState: ViewHolder(){
    abstract fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    abstract fun onDestroyView()
}