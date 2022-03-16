package com.mohsenoid.compose.svgconvertor

import com.mohsenoid.compose.svgconvertor.model.ModelConvertorResult

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Missing path!")
        return
    }

    val svgPath = args[0]

    when (val convertorResult = SvgStringConvertor.convert(svgPath)) {
        is ModelConvertorResult.Error.InvalidFile -> throw IllegalStateException()
        is ModelConvertorResult.Error.InvalidPath -> println(convertorResult.error)
        is ModelConvertorResult.Success -> println(convertorResult.result)
    }
}


