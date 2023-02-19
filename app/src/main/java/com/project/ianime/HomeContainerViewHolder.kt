package com.project.ianime

import com.project.ianime.root.ContainerViewHolder

class HomeContainerViewHolder: ContainerViewHolder() {
    override fun getContainerViewId(): Int {
        return R.id.container
    }
    fun getActionBarViewId(): Int{
        return R.id.tool_bar
    }
    fun getBottomBarViewId(): Int{
        return R.id.bottom_navigation_view
    }

}