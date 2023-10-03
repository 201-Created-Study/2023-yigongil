package com.created.team201.presentation.createStudy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.created.team201.R
import com.created.team201.databinding.FragmentFirstCreateStudyBinding
import com.created.team201.presentation.common.BindingFragment
import com.created.team201.presentation.createStudy.bottomSheet.PeopleCountBottomSheetFragment
import com.created.team201.presentation.createStudy.bottomSheet.StudyDateBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstCreateStudyFragment :
    BindingFragment<FragmentFirstCreateStudyBinding>(R.layout.fragment_first_create_study) {
    private val createStudyViewModel: CreateStudyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        setupCollectEnableNext()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = createStudyViewModel
        binding.onInputClickListener = ::setOnInputClick
    }


    private fun setOnInputClick(tag: String) {
        removeAllFragment()
        createBottomSheetFragment(tag)?.show(childFragmentManager, tag)
    }

    private fun removeAllFragment() {
        childFragmentManager.fragments.forEach {
            childFragmentManager.commit {
                remove(it)
            }
        }
    }

    private fun createBottomSheetFragment(tag: String): BottomSheetDialogFragment? {
        return when (tag) {
            getString(R.string.createStudy_tag_people_count) -> {
                PeopleCountBottomSheetFragment()
            }

            getString(R.string.createStudy_tag_study_date) -> {
                StudyDateBottomSheetFragment()
            }

            else -> {
                null
            }
        }
    }

    private fun setupCollectEnableNext() {
        lifecycleScope.launch {
            createStudyViewModel.isEnableFirstCreateStudyNext.collect { isEnable ->
                binding.tvFirstCreateStudyBtnNext.isEnabled = isEnable
            }
        }
    }
}
