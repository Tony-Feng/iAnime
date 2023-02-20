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

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
//        inflater.inflate(R.menu.settings_menu, menu)
//        return super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.setting_chinese_language -> {
//                updateLanguageSetting(requireContext(), HomeFragment.SETTING_CHINESE)
//                true
//            }
//            R.id.setting_english_language -> {
//                updateLanguageSetting(requireContext(), HomeFragment.SETTING_ENGLISH)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//
//    }
}