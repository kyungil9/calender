package com.calender.domain.usecase.tag

import com.calender.domain.repository.TagRepository
import javax.inject.Inject

class GetOneTagUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {
    operator fun invoke() : String{
        return tagRepository.getOneTag()
    }
}