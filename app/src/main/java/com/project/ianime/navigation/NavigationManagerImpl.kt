package com.project.ianime.navigation

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.project.ianime.root.BaseFragment

class NavigationManagerImpl(
    private val fragmentManager: FragmentManager
) : NavigationManager, NavigationManagerLifecycle {

    private val fragmentStack = ArrayList<BaseFragment<*>>()
    private val activeFragmentStack = ArrayList<BaseFragment<*>>()
    private val fragmentLifeCycleListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            if (f is BaseFragment<*>) {
                fragmentStack.add(f)
            }
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            if (f is BaseFragment<*>) {
                fragmentStack.remove(f)
            }
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            if (f is BaseFragment<*>) {
                activeFragmentStack.add(f)
            }
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            if (f is BaseFragment<*>) {
                activeFragmentStack.remove(f)
            }
        }
    }

    private fun getTopActiveFragment(): BaseFragment<*>? {
        val activeCount = activeFragmentStack.size
        if (activeCount == 0) {
            return null
        }
        return activeFragmentStack[activeCount - 1]
    }

    override fun showFragmentReplaceTop(fragment: Fragment, containerId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showFragmentOverTop(fragment: Fragment, containerId: Int) {
        val transaction = fragmentManager.beginTransaction()
        val topFragment = getTopActiveFragment()
        if (topFragment != null) {
            transaction.remove(topFragment)
        }
        transaction.add(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }
    //TODO 2022-02-21: from add screen return back to user screen
    override fun closeTop(): Boolean {
        if (fragmentManager.backStackEntryCount < 1) {
            return false
        }
        fragmentManager.popBackStack()
        return true
    }

    override fun onCreate() {
        fragmentManager.registerFragmentLifecycleCallbacks(fragmentLifeCycleListener, false)
    }

    override fun onDestroy() {
        fragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifeCycleListener)
    }

}