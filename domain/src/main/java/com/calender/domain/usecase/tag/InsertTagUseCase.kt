package com.calender.domain.usecase.tag

import com.calender.domain.repository.TagRepository
import javax.inject.Inject

class InsertTagUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {
    operator fun invoke(tag : String,mode : Int){
        return tagRepository.insertTag(tag,mode)
    }
}