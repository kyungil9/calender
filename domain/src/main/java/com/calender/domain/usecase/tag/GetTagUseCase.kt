package com.calender.domain.usecase.tag

import com.calender.domain.model.Result
import com.calender.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTagUseCase @Inject constructor(
    private val tagRepository: TagRepository
){
    operator fun invoke() : Flow<Result<List<String>>>{
        return tagRepository.getAllTag()
    }
}