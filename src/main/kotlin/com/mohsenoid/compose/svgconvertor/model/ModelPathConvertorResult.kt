package com.mohsenoid.compose.svgconvertor.model

sealed interface ModelConvertorResult {

    data class Success(val result: String) : ModelConvertorResult

    sealed interface Error : ModelConvertorResult {
        data class InvalidFile(val svgFileError: SvgFileError) : Error
        data class InvalidPath(val error: String) : Error
    }
}