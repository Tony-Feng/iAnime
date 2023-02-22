package com.project.ianime.root

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.ianime.navigation.NavigationManager
import com.project.ianime.navigation.NavigationManagerImpl
import com.project.ianime.navigation.NavigationManagerLifecycle

abstract class BaseFragment<VH: FragmentViewHolder>(val viewHolder: VH): Fragment() {

    lateinit var navigationManager: NavigationManager
    private lateinit var navigationManagerLifecycle: NavigationManagerLifecycle
    val baseContainerId: Int
        get() {
            val baseContainerActivity = activity as BaseContainerActivity<*>
            return baseContainerActivity.containerViewHolder.getContainerViewId()
        }

    override fun onAttach(context: Context) {
        val navigationManagerImpl = NavigationManagerImpl(
            requireActivity().supportFragmentManager
        )
        navigationManager = navigationManagerImpl
        navigationManagerLifecycle = navigationManagerImpl
        super.onAttach(context)
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewHolder.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        navigationManagerLifecycle.onCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        navigationManagerLifecycle.onDestroy()
        super.onDestroy()
    }

    constructor(vhClass: Class<VH>): this(ViewHolder.createViewHolder(vhClass))
}