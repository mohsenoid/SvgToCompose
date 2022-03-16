package com.mohsenoid.compose.svgconvertor

import com.mohsenoid.compose.svgconvertor.model.ModelConvertorResult
import com.mohsenoid.compose.svgconvertor.model.SvgFileError

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Missing SVG file!")
        return
    }

    val svgFilePath = args[0]

    when (val convertorResult = SvgFileConvertor.convert(svgFilePath)) {
        is ModelConvertorResult.Error.InvalidFile -> {
            when (convertorResult.svgFileError) {
                SvgFileError.FILE_NOT_EXISTS -> println("SVG file does not exists!")
                SvgFileError.FILE_NOT_SVG -> println("Not a SVG file!")
            }
        }
        is ModelConvertorResult.Error.InvalidPath -> println(convertorResult.error)
        is ModelConvertorResult.Success -> println(convertorResult.result)
    }
}


