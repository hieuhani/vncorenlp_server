package com.digitaltoken.vncorenlpserver
import com.google.gson.GsonBuilder
import vn.pipeline.*
import vn.pipeline.Annotation
import spark.Spark.*


val annotators = arrayOf("wseg", "pos", "ner", "parse")
val pipeline = VnCoreNLP(annotators)

fun main(args: Array<String>) {
    get("/") {
        request, response ->

        val input = request.queryParams("input")
        if (input.isNullOrBlank()) {
            response.status(201)
            "Please specify query param: input"
        } else {
            val annotation = Annotation(input)
            pipeline.annotate(annotation)

            val output = GsonBuilder()
                    .registerTypeAdapter(Annotation::class.javaObjectType, AnnotationSerializer())
                    .registerTypeAdapter(Sentence::class.javaObjectType, SentenceSerializer())
                    .setPrettyPrinting()
                    .create()

            response.type("application/json")
            output.toJson(annotation)
        }
    }
}

