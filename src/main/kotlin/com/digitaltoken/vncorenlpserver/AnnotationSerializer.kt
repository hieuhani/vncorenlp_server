package com.digitaltoken.vncorenlpserver

import com.google.gson.*
import vn.pipeline.Annotation
import java.lang.reflect.Type

class AnnotationSerializer : JsonSerializer<Annotation> {
    override fun serialize(p0: Annotation, p1: Type?, p2: JsonSerializationContext): JsonElement {
        return JsonObject().apply {
            add("rawText", JsonPrimitive(p0.rawText))
            add("language", JsonPrimitive(p0.detectLanguage()))
            add("wordCount", Gson().toJsonTree(p0.wordCount()))
            add("tokens", Gson().toJsonTree(p0.tokens))
            add("words", Gson().toJsonTree(p0.words))
            add("sentences", p2.serialize(p0.sentences))
        }
    }
}