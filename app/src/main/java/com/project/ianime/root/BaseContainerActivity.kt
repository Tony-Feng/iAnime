package com.project.ianime.root

import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.navigation.AppNavigationImpl
import com.project.ianime.navigation.AppNavigationLifecycle

abstract class BaseContainerActivity<VH: ViewHolder>(containerViewHolder: VH): AppCompatActivity(){
    val containerViewHolder = containerViewHolder
    private val navigationManagerLifecycle: AppNavigationLifecycle
        get() {
            return AppNavigationImpl(supportFragmentManager)
        }
    constructor(vhClass: Class<VH>): this(ViewHolder.createViewHolder(vhClass))

}