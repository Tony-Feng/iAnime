package com.project.ianime.screen

import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.viewholder.UserFragmentViewHolder

class UserFragment : BaseFragment<UserFragmentViewHolder>(UserFragmentViewHolder::class.java) {
    companion object {
        fun newInstance() = UserFragment()
    }
}