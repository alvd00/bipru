package com.example.bipru.presentation.base

import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding: VB

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding(inflater, container)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view, savedInstanceState)
        observeViewModel()
    }

    protected abstract fun initView(view: View, savedInstanceState: Bundle?)

    fun setSupportActionBar(toolbar: Toolbar): ActionBar? {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            return supportActionBar
        }
    }

    private fun getProgressDialog(): ProgressDialog? {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(requireContext())
        }
        return progressDialog
    }

    private fun showProgress() = getProgressDialog()?.show()

    private fun hideProgress() = getProgressDialog()?.dismiss()

    /**
     * Use when need to hide progress and stop fragment
     */
    fun hideProgressDirectly() = getProgressDialog()?.dismiss()

    open fun observeViewModel() {

        viewModel.showProgress.observe(viewLifecycleOwner) {
            if (it == true) showProgress() else hideProgress()
        }
    }

}