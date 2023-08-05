package com.example.bipru.presentation.screens.drivers_license

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bipru.R
import com.example.bipru.databinding.FragmentDriversLicenseBinding
import com.example.bipru.presentation.base.BaseFragment
import com.example.bipru.presentation.screens.vrc.VRCFragmentDirections
import com.example.bipru.utils.extentions.alert
import com.example.bipru.utils.watcher.SimpleTextWatcher
import org.koin.androidx.viewmodel.ext.android.viewModel

class DriversLicenseFragment :
    BaseFragment<DriversLicenseViewModel, FragmentDriversLicenseBinding>() {

    override val viewModel by viewModel<DriversLicenseViewModel>()
    private lateinit var codeWatcher: SimpleTextWatcher

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDriversLicenseBinding.inflate(inflater, container, false)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        codeWatcher = SimpleTextWatcher { viewModel.driversLicenseCode = it }
        with(binding) {
            codeEditText.setText(viewModel.driversLicenseCode)
            nextButton.setOnClickListener {
                viewModel.saveDriversLicense(viewModel.driversLicenseCode)
                val action =
                    DriversLicenseFragmentDirections.actionDriversLicenseToResults()
                findNavController().navigate(action)
            }

            backLayout.setOnClickListener {
                findNavController().navigate(
                    DriversLicenseFragmentDirections.actionDriversLicenseToVrc(
                        ""//viewModel.vrp
                    )
                )
            }
            skipTextView.setOnClickListener {
                viewModel.saveDriversLicense("")
                requireContext().alert(getString(R.string.alert_title), positive = {
                    findNavController().navigate(DriversLicenseFragmentDirections.actionDriversLicenseToResults())
                })
            }

        }

    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.isButtonEnabled.observe(viewLifecycleOwner) {
            binding.nextButton.isEnabled = it
        }
    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            codeEditText.addTextChangedListener(codeWatcher)
        }
    }

    override fun onPause() {
        super.onPause()
        with(binding) {
            codeEditText.removeTextChangedListener(codeWatcher)
        }
    }

}