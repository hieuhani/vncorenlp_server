package com.digitaltoken.vncorenlpserver
import com.google.gson.GsonBuilder
import vn.pipeline.*
import vn.pipeline.Annotation


fun main(args: Array<String>) {
    val annotators = arrayOf("wseg", "pos", "ner", "parse")
    val pipeline = VnCoreNLP(annotators)

    val str = "Ông Nguyễn Khắc Chúc đang làm việc tại Đại học Quốc gia Hà Nội. Bà Lan, vợ ông Chúc, cũng làm việc tại đây."
    val annotation = Annotation(str)
    pipeline.annotate(annotation)

    val output = GsonBuilder().registerTypeAdapter(Annotation::class.javaObjectType, AnnotationSerializer()).create()

    println(output.toJson(annotation))
}

