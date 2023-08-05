package com.example.bipru.presentation.screens.vrp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bipru.R
import com.example.bipru.databinding.FragmentVrpBinding
import com.example.bipru.presentation.base.BaseFragment
import com.example.bipru.utils.extentions.alert
import com.example.bipru.utils.watcher.SimpleTextWatcher
import org.koin.androidx.viewmodel.ext.android.viewModel

class VRPFragment : BaseFragment<VRPViewModel, FragmentVrpBinding>() {

    override val viewModel by viewModel<VRPViewModel>()

    private lateinit var codeWatcher: SimpleTextWatcher

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentVrpBinding.inflate(inflater, container, false)

    override fun initView(view: View, savedInstanceState: Bundle?) {

        codeWatcher = SimpleTextWatcher { viewModel.vrpCode = it }
        //Если завершали ввод, то переходим сразу к результатам
        if (viewModel.isFinishedResults) findNavController().navigate(VRPFragmentDirections.actionVrpToResults(/*viewModel.vrp, viewModel.vrc, viewModel.license*/))
        with(binding) {
            codeEditText.setText(viewModel.vrpCode)
            nextButton.setOnClickListener {
                val action =
                    VRPFragmentDirections.actionMainToVrc(
                        viewModel.vrpCode
                    )
                findNavController().navigate(action)
            }
            skipTextView.setOnClickListener {
                viewModel.skipVRP()
                requireContext().alert(getString(R.string.alert_title), positive = {
                    findNavController().navigate(VRPFragmentDirections.actionVrpToDriversLicense())
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