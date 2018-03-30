package com.digitaltoken.vncorenlpserver

import com.google.gson.*
import vn.pipeline.Sentence
import java.lang.reflect.Type

class SentenceSerializer : JsonSerializer<Sentence> {
    override fun serialize(p0: Sentence, p1: Type?, p2: JsonSerializationContext): JsonElement {
        return JsonObject().apply {
            add("rawSentence", JsonPrimitive(p0.rawSentence))
            add("words", Gson().toJsonTree(p0.words))
            add("tokens", Gson().toJsonTree(p0.tokens))
            add("language", JsonPrimitive(p0.detectLanguage()))
            add("wordSegmentedSentence", JsonPrimitive(p0.wordSegmentedSentence))
        }
    }
}