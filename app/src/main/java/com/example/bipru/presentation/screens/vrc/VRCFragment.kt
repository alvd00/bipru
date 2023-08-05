package com.example.bipru.presentation.screens.vrc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bipru.R
import com.example.bipru.databinding.FragmentVrcBinding
import com.example.bipru.presentation.base.BaseFragment
import com.example.bipru.utils.extentions.alert
import com.example.bipru.utils.watcher.SimpleTextWatcher
import org.koin.androidx.viewmodel.ext.android.viewModel

class VRCFragment : BaseFragment<VRCViewModel, FragmentVrcBinding>() {

    override val viewModel by viewModel<VRCViewModel>()

    private lateinit var codeWatcher: SimpleTextWatcher
    private val args: VRCFragmentArgs by navArgs()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentVrcBinding.inflate(inflater, container, false)

    override fun initView(view: View, savedInstanceState: Bundle?) {

        codeWatcher = SimpleTextWatcher { viewModel.vrcCode = it }

        with(binding) {
            codeEditText.setText(viewModel.vrcCode)

            nextButton.setOnClickListener {
                viewModel.saveData(args.vrp)
                val action =
                    VRCFragmentDirections.actionVrcToDriversLicense()
                findNavController().navigate(action)
            }
            backLayout.setOnClickListener {
                findNavController().navigate(VRCFragmentDirections.actionVrcToVrp())
            }
            skipTextView.setOnClickListener {
                requireContext().alert(getString(R.string.alert_title), positive = {
                    findNavController().navigate(VRCFragmentDirections.actionVrcToDriversLicense())
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