package com.project.ianime.screen

import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.viewholder.UserViewHolder

class UserFragment : BaseFragment<UserViewHolder>(UserViewHolder::class.java) {
    companion object {
        fun newInstance() = UserFragment()
    }
}