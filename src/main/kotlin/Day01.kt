import java.io.File

fun day01() {
    File(loadResource("Day01.txt").toURI()).bufferedReader().useLines { lines ->
        val answer = lines.map { it.toInt() }.zipWithNext().fold(0, ::compareDepthFold)
        println("Day 1.1 Fold answer: $answer")
    }
    File(loadResource("Day01.txt").toURI()).readLines().let { lines ->
        val answer = compareDepthList(0, 0, lines.map(String::toInt)) - 1
        println("Day 1.1 List answer: $answer")
    }
    File(loadResource("Day01.txt").toURI()).readLines().let { lines ->
        val answer = compareDepthWindowList(0, 0, lines.map(String::toInt)) - 1
        println("Day 1.2 Window list answer: $answer")
    }
}

fun compareDepthFold(count: Int, depthPair: Pair<Int, Int>): Int = count + if (depthPair.second > depthPair.first) 1 else 0

tailrec fun compareDepthList(count: Int, previousDepth: Int, rest: List<Int>): Int {
    return when (val currentDepth = rest.take(1).firstOrNull()) {
        null -> count
        else -> compareDepthList(count + (if (currentDepth > previousDepth) 1 else 0), currentDepth, rest.drop(1))
    }
}

tailrec fun compareDepthWindowList(count: Int, previousDepth: Int, rest: List<Int>): Int {
    val window = rest.take(3)
    return when {
        window.size < 3 -> count
        else -> compareDepthWindowList(count + if (window.sum() > previousDepth) 1 else 0, window.sum(), rest.drop(1))
    }
}