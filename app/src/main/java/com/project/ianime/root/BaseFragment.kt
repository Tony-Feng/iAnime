package com.project.ianime.root

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.ianime.navigation.NavigationManager
import com.project.ianime.navigation.NavigationManagerImpl

abstract class BaseFragment<VH: FragmentViewHolder>(val viewHolder: VH): Fragment() {

    lateinit var navigationManager: NavigationManager
    val baseContainerId: Int
        get() {
            val baseContainerActivity = activity as BaseContainerActivity<*>
            return baseContainerActivity.containerViewHolder.getContainerViewId()
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val navigationManagerImpl = NavigationManagerImpl(
            requireActivity().supportFragmentManager,
            viewHolder
        )
        navigationManager = navigationManagerImpl
    }
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewHolder.onCreateView(inflater, container, savedInstanceState)
    }

    constructor(vhClass: Class<VH>): this(ViewHolder.createViewHolder(vhClass))
}