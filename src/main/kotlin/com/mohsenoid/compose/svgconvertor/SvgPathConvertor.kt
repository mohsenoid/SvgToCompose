package com.mohsenoid.compose.svgconvertor

import com.mohsenoid.compose.svgconvertor.model.ModelPath
import com.mohsenoid.compose.svgconvertor.model.ModelPathPartPayload

object SvgPathConvertor {

    fun convert(paths: List<ModelPath>): String =
        paths.mapNotNull { path ->
            val d = path.d ?: return@mapNotNull null

            val fill = path.fill
            if (fill != null && fill.equals(FILL_TYPE_NONE, true)) return@mapNotNull null

            pathToMaterialPath(d)
        }.joinToString("\n")

    private fun pathToMaterialPath(path: String): String =
        path.split(PATH_SPLITTER_REGEX).mapNotNull(::mapPathPartToMaterialPath).joinToString("\n")

    private fun mapPathPartToMaterialPath(pathPart: String): String? {
        if (pathPart.isEmpty()) return null

        val payload = ModelPathPartPayload(pathPart.substring(1))

        return when (val pathType = pathPart[0]) {
            'M' -> payload.extractMoveTo()
            'm' -> payload.extractMoveToRelative()
            'L' -> payload.extractLineTo()
            'l' -> payload.extractLineToRelative()
            'H' -> payload.extractHorizontalLineTo()
            'h' -> payload.extractHorizontalLineToRelative()
            'V' -> payload.extractVerticalLineTo()
            'v' -> payload.extractVerticalLineToRelative()
            'C' -> payload.extractCurveTo()
            'c' -> payload.extractCurveToRelative()
            'S' -> payload.extractReflectiveCurveTo()
            's' -> payload.extractReflectiveCurveToRelative()
            'Q' -> payload.extractQuadTo()
            'q' -> payload.extractQuadToRelative()
            'T' -> payload.extractReflectiveQuadTo()
            't' -> payload.extractReflectiveQuadToRelative()
            'A' -> payload.extractArcTo()
            'a' -> payload.extractArcToRelative()
            'Z', 'z' -> CLOSE
            else -> throw RuntimeException("Path type $pathType is not implemented!")
        }
    }

    private fun ModelPathPartPayload.extractMoveTo(): String {
        val x = nextFloat()
        val y = nextFloat()
        return "moveTo(${x}f, ${y}f)"
    }

    private fun ModelPathPartPayload.extractMoveToRelative(): String {
        val x = nextFloat()
        val y = nextFloat()
        return "moveToRelative(${x}f, ${y}f)"
    }

    private fun ModelPathPartPayload.extractLineTo(): String {
        val x = nextFloat()
        val y = nextFloat()
        return "lineTo(${x}f, ${y}f)"
    }

    private fun ModelPathPartPayload.extractLineToRelative(): String {
        val x = nextFloat()
        val y = nextFloat()
        return "lineToRelative(${x}f, ${y}f)"
    }

    private fun ModelPathPartPayload.extractHorizontalLineTo(): String {
        val x = nextFloat()
        return "horizontalLineTo(${x}f)"
    }

    private fun ModelPathPartPayload.extractHorizontalLineToRelative(): String {
        val x = nextFloat()
        return "horizontalLineToRelative(${x}f)"
    }

    private fun ModelPathPartPayload.extractVerticalLineTo(): String {
        val y = nextFloat()
        return "verticalLineTo(${y}f)"
    }

    private fun ModelPathPartPayload.extractVerticalLineToRelative(): String {
        val y = nextFloat()
        return "verticalLineToRelative(${y}f)"
    }

    private fun ModelPathPartPayload.extractCurveTo(): String {
        val cX1 = nextFloat()
        val cY1 = nextFloat()
        val cX2 = nextFloat()
        val cY2 = nextFloat()
        val eX = nextFloat()
        val eY = nextFloat()
        return "curveTo(${cX1}f, ${cY1}f, ${cX2}f, ${cY2}f, ${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractCurveToRelative(): String {
        val cX1 = nextFloat()
        val cY1 = nextFloat()
        val cX2 = nextFloat()
        val cY2 = nextFloat()
        val eX = nextFloat()
        val eY = nextFloat()
        return "curveToRelative(${cX1}f, ${cY1}f, ${cX2}f, ${cY2}f, ${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractReflectiveCurveTo(): String {
        val cX2 = nextFloat()
        val cY2 = nextFloat()
        val eX = nextFloat()
        val eY = nextFloat()
        return "reflectiveCurveTo(${cX2}f, ${cY2}f, ${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractReflectiveCurveToRelative(): String {
        val result = mutableListOf<String>()
        while (hasMore()) {
            val cX2 = nextFloat()
            val cY2 = nextFloat()
            val eX = nextFloat()
            val eY = nextFloat()
            result += "reflectiveCurveToRelative(${cX2}f, ${cY2}f, ${eX}f, ${eY}f)"
        }
        return result.joinToString("\n")
    }

    private fun ModelPathPartPayload.extractQuadTo(): String {
        val cX = nextFloat()
        val cY = nextFloat()
        val eX = nextFloat()
        val eY = nextFloat()
        return "quadTo(${cX}f, ${cY}f, ${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractQuadToRelative(): String {
        val cX = nextFloat()
        val cY = nextFloat()
        val eX = nextFloat()
        val eY = nextFloat()
        return "quadToRelative(${cX}f, ${cY}f, ${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractReflectiveQuadTo(): String {
        val eX = nextFloat()
        val eY = nextFloat()
        return "reflectiveQuadTo(${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractReflectiveQuadToRelative(): String {
        val eX = nextFloat()
        val eY = nextFloat()
        return "reflectiveQuadToRelative(${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractArcTo(): String {
        val rX = nextFloat()
        val rY = nextFloat()
        val rotation = nextFloat()
        val arc = nextBoolean()
        val sweep = nextBoolean()
        val eX = nextFloat()
        val eY = nextFloat()
        return "arcTo(${rX}f, ${rY}f, ${rotation}f, ${arc}, ${sweep}, ${eX}f, ${eY}f)"
    }

    private fun ModelPathPartPayload.extractArcToRelative(): String {
        val rX = nextFloat()
        val rY = nextFloat()
        val rotation = nextFloat()
        val arc = nextBoolean()
        val sweep = nextBoolean()
        val eX = nextFloat()
        val eY = nextFloat()
        return "arcToRelative(${rX}f, ${rY}f, ${rotation}f, ${arc}, ${sweep}, ${eX}f, ${eY}f)"
    }

    private const val CLOSE: String = "close()"

    private val PATH_SPLITTER_REGEX = Regex("(?=[a-zA-Z])")

    private const val FILL_TYPE_NONE = "none"
}