package com.mohsenoid.compose.svgconvertor

import com.mohsenoid.compose.svgconvertor.model.ModelConvertorResult
import com.mohsenoid.compose.svgconvertor.model.SvgFileError

object SvgFileConvertor {
    
    fun convert(svgFilePath: String): ModelConvertorResult {
        val svgFileHelper = SvgFileHelper(svgFilePath)
        if (!svgFileHelper.exists) return ModelConvertorResult.Error.InvalidFile(SvgFileError.FILE_NOT_EXISTS)
        if (!svgFileHelper.isSvg) return ModelConvertorResult.Error.InvalidFile(SvgFileError.FILE_NOT_SVG)

        svgFileHelper.altGetSvgPaths()

        val paths = svgFileHelper.getSvgPaths()

        return ModelConvertorResult.Success(SvgPathConvertor.convert(paths))
    }
}