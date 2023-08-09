package com.created.team201.data.datasource.remote

import com.created.team201.data.remote.request.TodoCreateRequestDto
import com.created.team201.data.remote.request.TodoRequestDto
import com.created.team201.data.remote.request.TodoUpdateRequestDto
import com.created.team201.data.remote.response.RoundDetailResponseDto
import com.created.team201.data.remote.response.StudyDetailResponseDto
import retrofit2.Response

interface StudyManagementDataSource {

    suspend fun getStudyDetail(studyId: Long): StudyDetailResponseDto

    suspend fun getRoundDetail(studyId: Long, roundId: Long): RoundDetailResponseDto

    suspend fun patchTodo(studyId: Long, todoId: Long, todoRequestDto: TodoRequestDto)

    suspend fun createTodo(
        studyId: Long,
        todoCreateRequestDto: TodoCreateRequestDto,
    ): Response<Unit>

    suspend fun createNecessaryTodo(
        roundId: Long,
        todoCreateRequestDto: TodoCreateRequestDto,
    ): Response<Unit>

    suspend fun createOptionalTodo(
        roundId: Long,
        todoCreateRequestDto: TodoCreateRequestDto,
    ): Response<Unit>

    suspend fun patchNecessary(roundId: Long, todoUpdateRequestDto: TodoUpdateRequestDto)

    suspend fun patchOptionalTodo(
        roundId: Long,
        todoId: Long,
        todoUpdateRequestDto: TodoUpdateRequestDto,
    )

    suspend fun deleteOptionalTodo(roundId: Long, todoId: Long)
}
