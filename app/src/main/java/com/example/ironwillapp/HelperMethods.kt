package com.example.ironwillapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ironwillapp.ui.activities.MainActivity

object HelperMethods {
    const val FRAGMENT_TAG = "fragments_tag"
    fun changeFragment(activity: MainActivity, fragment: Fragment?) {
        val fragmentTransaction: FragmentTransaction =
            activity.supportFragmentManager.beginTransaction()
        //        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.myNavHostFragment, fragment!!, FRAGMENT_TAG)
        fragmentTransaction.commit()
    }
}