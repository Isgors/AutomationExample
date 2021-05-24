package com.automation.utils

import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import kotlin.system.exitProcess


@Service
class FileService {

    fun createDirectory (parentDirectoryName: String, directoryName: String) : File {
        val directory = File(parentDirectoryName, directoryName)

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    fun createFile(path: String, fileName: String, fileContent: String) {

        val file = File("$path/$fileName")

        try {
            val fw = FileWriter(file.absoluteFile)
            val bw = BufferedWriter(fw)
            bw.write(fileContent)
            bw.close()
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(-1)
        }
    }

}