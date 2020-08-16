package com.example.gifapisimpleapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.gifapisimpleapplication.livedata.ViewAction
import com.example.gifapisimpleapplication.viewmodels.BaseViewModel

abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewAction.observe(this, Observer {
            handleViewAction(it)
        })
    }

    protected open fun handleViewAction(action: ViewAction) {
        when (action) {
            is ViewAction.Navigate -> navigate(action)
            is ViewAction.NavigateWithDirection -> navigateWithDirection(action.direction)
            is ViewAction.Finish -> finish(action)
            else -> throw RuntimeException("Unable to handle this action: $action")
        }
    }

    private fun navigate(action: ViewAction.Navigate) {
        val intent = action.buildIntent(context!!)
        if (action.requestCode == null) {
            startActivity(intent)
        } else {
            startActivityForResult(intent, action.requestCode)
        }
    }

    private fun navigateWithDirection(direction: NavDirections) {
        view!!.findNavController().navigate(direction)
    }

    private fun finish(action: ViewAction.Finish) {
        action.resultCode?.let { activity?.setResult(it) }
        activity?.finish()
    }
}