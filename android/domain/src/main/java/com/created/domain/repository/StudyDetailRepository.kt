package com.created.domain.repository

import com.created.domain.model.Member
import com.created.domain.model.StudyDetail

interface StudyDetailRepository {

    suspend fun getStudyDetail(studyId: Long): StudyDetail

    suspend fun getStudyApplicants(studyId: Long): List<Member>

    suspend fun participateStudy(studyId: Long)

    suspend fun startStudy(studyId: Long)
}
