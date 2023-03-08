package com.project.ianime.root

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.ianime.widget.actionbar.ActionBarService
import com.project.ianime.widget.actionbar.ActionBarServiceImpl
import com.project.ianime.navigation.AppNavigation
import com.project.ianime.navigation.AppNavigationImpl
import com.project.ianime.navigation.AppNavigationLifecycle

abstract class BaseFragment<US: FragmentUiState>(val uiState: US): Fragment() {

    lateinit var appNavigation: AppNavigation
    private lateinit var navigationLifeCycle: AppNavigationLifecycle
    val actionBarService: ActionBarService
        get() {
            return ActionBarServiceImpl(
                activity as AppCompatActivity
            )
        }
    val baseContainerId: Int
        get() {
            val baseContainerActivity = activity as BaseContainerActivity<*>
            return baseContainerActivity.containerViewHolder.getContainerViewId()
        }
    override fun onAttach(context: Context) {
        val navigationManagerImpl = AppNavigationImpl(
            requireActivity().supportFragmentManager
        )
        appNavigation = navigationManagerImpl
        navigationLifeCycle = navigationManagerImpl
        super.onAttach(context)
    }
    // Set Fragment Action Bar
    open fun updateActionBar(): Boolean{
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        navigationLifeCycle.onCreate()
        super.onCreate(savedInstanceState)
    }
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return uiState.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateActionBar()
    }
    final override fun onDestroyView() {
        super.onDestroyView()
        uiState.onDestroyView()
    }
    override fun onDestroy() {
        navigationLifeCycle.onDestroy()
        super.onDestroy()
    }
    constructor(vhClass: Class<US>): this(ViewHolder.createViewHolder(vhClass))
}