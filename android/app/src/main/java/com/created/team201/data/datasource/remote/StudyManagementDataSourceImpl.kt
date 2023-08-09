package com.created.team201.data.datasource.remote

import com.created.team201.data.remote.api.StudyManagementService
import com.created.team201.data.remote.request.TodoCreateRequestDto
import com.created.team201.data.remote.request.TodoRequestDto
import com.created.team201.data.remote.request.TodoUpdateRequestDto
import com.created.team201.data.remote.response.RoundDetailResponseDto
import com.created.team201.data.remote.response.StudyDetailResponseDto
import retrofit2.Response

class StudyManagementDataSourceImpl(
    private val service: StudyManagementService,
) : StudyManagementDataSource {

    override suspend fun getStudyDetail(studyId: Long): StudyDetailResponseDto {
        return service.getStudyDetail(studyId)
    }

    override suspend fun getRoundDetail(studyId: Long, roundId: Long): RoundDetailResponseDto {
        return service.getRoundDetail(studyId, roundId)
    }

    override suspend fun patchTodo(studyId: Long, todoId: Long, todoRequestDto: TodoRequestDto) {
        service.patchTodo(studyId, todoId, todoRequestDto)
    }

    override suspend fun createTodo(
        studyId: Long,
        todoCreateRequestDto: TodoCreateRequestDto,
    ): Response<Unit> {
        return service.createTodo(studyId, todoCreateRequestDto)
    }

    override suspend fun createNecessaryTodo(
        roundId: Long,
        todoCreateRequestDto: TodoCreateRequestDto,
    ): Response<Unit> {
        return service.createNecessaryTodo(roundId, todoCreateRequestDto)
    }

    override suspend fun createOptionalTodo(
        roundId: Long,
        todoCreateRequestDto: TodoCreateRequestDto,
    ): Response<Unit> {
        return service.createOptionalTodo(roundId, todoCreateRequestDto)
    }

    override suspend fun patchNecessary(roundId: Long, todoUpdateRequestDto: TodoUpdateRequestDto) {
        return service.patchNecessary(roundId, todoUpdateRequestDto)
    }

    override suspend fun patchOptionalTodo(
        roundId: Long,
        todoId: Long,
        todoUpdateRequestDto: TodoUpdateRequestDto,
    ) {
        return service.patchOptionalTodo(roundId, todoId, todoUpdateRequestDto)
    }

    override suspend fun deleteOptionalTodo(roundId: Long, todoId: Long) {
        return service.deleteOptionalTodo(roundId, todoId)
    }
}
