package com.example.bipru.presentation.screens.results

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bipru.databinding.FragmentResultsBinding
import com.example.bipru.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultsFragment : BaseFragment<ResultsViewModel, FragmentResultsBinding>() {

    override val viewModel by viewModel<ResultsViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentResultsBinding.inflate(inflater, container, false)

    @SuppressLint("SetTextI18n")
    override fun initView(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            vrpView.text = vrpView.text.toString() + viewModel.vrp
            vrcView.text = vrcView.text.toString() + viewModel.vrc
            driversLicenseView.text = driversLicenseView.text.toString() + viewModel.driversLicense

            //Если возвращаемся, чтобы сменить данные и перезапускаем приложение, то данные должны вводиться с самого начала
            backLayout.setOnClickListener {
                viewModel.backFromResults()
                findNavController().navigate(ResultsFragmentDirections.actionResultsToDriversLicense())
            }
        }

    }

}