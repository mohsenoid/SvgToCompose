package com.mohsenoid.compose.svgconvertor

import com.mohsenoid.compose.svgconvertor.model.ModelConvertorResult
import com.mohsenoid.compose.svgconvertor.model.ModelPath

object SvgStringConvertor {

    fun convert(pathString: String): ModelConvertorResult {
        val paths = listOf(ModelPath(pathString))
        val result = SvgPathConvertor.convert(paths)
        return ModelConvertorResult.Success(result)
    }
}