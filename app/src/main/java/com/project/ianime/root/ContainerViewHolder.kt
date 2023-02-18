package com.project.ianime.root

import androidx.annotation.IdRes
import java.lang.reflect.Constructor

abstract class ContainerViewHolder{
    /**
     * get the UI component id of activity container
     */
    @IdRes
    abstract fun getContainerViewId(): Int

    companion object{
        fun <VH: ContainerViewHolder> createContainerViewHolder(vhClass: Class<VH>): VH{
            val viConstructor: Constructor<VH> = vhClass.getConstructor()
            return viConstructor.newInstance()
        }
    }
}