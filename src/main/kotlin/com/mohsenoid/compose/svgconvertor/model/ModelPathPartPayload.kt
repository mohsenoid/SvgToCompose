package com.mohsenoid.compose.svgconvertor.model

data class ModelPathPartPayload(val payload: String) {

    private var index: Int = -1

    private val payloadParts = payload.split(SPLITTER_REGEX).filter { it.isNotEmpty() }

    fun hasMore(): Boolean = payloadParts.size - 1 > index

    fun nextFloat(): Float = nextPart().toFloat()

    fun nextBoolean(): Boolean = nextPart().toBoolean()

    private fun nextPart(): String {
        index++
        return payloadParts[index].trim()
    }

    companion object {
        private val SPLITTER_REGEX = Regex("(?=[\\s-])|,")
    }
}
