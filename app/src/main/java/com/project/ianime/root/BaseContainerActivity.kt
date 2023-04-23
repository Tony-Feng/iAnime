package com.project.ianime.root

import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.R
import com.project.ianime.navigation.AppNavigationImpl
import com.project.ianime.navigation.AppNavigationLifecycle

abstract class BaseContainerActivity: AppCompatActivity(){
    val containerViewHolderId = R.id.container
}