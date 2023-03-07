package com.project.ianime.root

import androidx.annotation.IdRes
import com.project.ianime.R
import java.lang.reflect.Constructor

abstract class ViewHolder{
    /**
     * return the UI reference id of base container
     */
    @IdRes
    open fun getContainerViewId(): Int {
        return R.id.fragment_container
    }

    companion object{
        fun <VH: ViewHolder> createViewHolder(vhClass: Class<VH>): VH{
            val viConstructor: Constructor<VH> = vhClass.getConstructor()
            return viConstructor.newInstance()
        }
    }
}