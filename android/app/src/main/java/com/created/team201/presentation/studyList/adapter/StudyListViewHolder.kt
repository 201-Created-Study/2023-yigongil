package com.created.team201.presentation.studyList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.created.team201.R
import com.created.team201.databinding.ItemStudyListBinding
import com.created.team201.presentation.studyList.StudyListClickListener
import com.created.team201.presentation.studyList.model.StudyStatus
import com.created.team201.presentation.studyList.model.StudyStatus.END
import com.created.team201.presentation.studyList.model.StudySummaryUiModel

class StudyListViewHolder(
    private val parent: ViewGroup,
    studyListClickListener: StudyListClickListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_study_list, parent, false),
) {

    private val binding = ItemStudyListBinding.bind(itemView)
    private lateinit var studySummary: StudySummaryUiModel

    init {
        itemView.setOnClickListener {
            studyListClickListener.onClickStudySummary(studySummary)
        }
    }

    fun bind(item: StudySummaryUiModel) {
        studySummary = item
        binding.studySummary = studySummary
        if (StudyStatus.of(studySummary.processingStatus) == END) {
            binding.tvStudyListTitle.setTextColor(
                getColor(parent.context, R.color.grey02_78808B),
            )
        }
    }
}
