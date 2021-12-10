import java.io.File
import java.net.URL

fun loadResource(path: String): URL {
    val resource = Thread.currentThread().contextClassLoader.getResource(path)
    requireNotNull(resource) { "Resource $path not found" }
    return resource
}

fun main() {
    day01()
    day02()
}

