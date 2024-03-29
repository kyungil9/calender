package com.calender.domain.usecase.tag

import com.calender.domain.repository.TagRepository
import javax.inject.Inject

class DeleteTagUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {
    operator fun invoke(tag : String,mode:Int){
        tagRepository.deleteTag(tag,mode)
    }
}