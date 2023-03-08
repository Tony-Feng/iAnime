package com.project.ianime.screen

import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.stateholder.UserUiState

class UserFragment : BaseFragment<UserUiState>(UserUiState::class.java) {
    companion object {
        fun newInstance() = UserFragment()
    }
}