package com.created.team201.data.mapper

import com.created.domain.model.Profile
import com.created.team201.data.remote.response.MyPageResponseDto

fun MyPageResponseDto.toDomain(): Profile = Profile(
    githubId = githubId,
    id = id,
    introduction = introduction,
    nickname = nickname,
    profileImageUrl = profileImageUrl,
    successRate = successRate,
    successfulRoundCount = successfulRoundCount,
    tier = tier,
    tierProgress = tierProgress,
)
