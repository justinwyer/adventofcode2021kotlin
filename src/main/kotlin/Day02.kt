import Command.*
import java.io.File

enum class Command {
    FORWARD,
    UP,
    DOWN
}

data class Instruction(val command: Command, val units: Int)
data class Coordinates(val position: Int = 0, val depth: Int = 0, val aim: Int = 0)

fun day02() {
    processInstructions("Day02.txt", ::plotCourse)
        .run { println("Day 2.1 answer: ${position * depth}") }

    processInstructions("Day02.txt", ::plotCourseWithAim)
        .run { println("Day 2.2 aim answer: ${position * depth}") }
}

private fun processInstructions(file: String, reducer: (Coordinates, Instruction) -> Coordinates): Coordinates {
    return File(loadResource(file).toURI()).bufferedReader().lineSequence()
        .map { line -> line.split(" ") }
        .map { Instruction(valueOf(it[0].uppercase()), it[1].toInt()) }
        .fold(Coordinates(), reducer)
}

fun plotCourse(coordinates: Coordinates, instruction: Instruction): Coordinates {
    return when (instruction.command) {
        FORWARD -> coordinates.copy(position = coordinates.position + instruction.units)
        UP -> coordinates.copy(depth = coordinates.depth - instruction.units)
        DOWN -> coordinates.copy(depth = coordinates.depth + instruction.units)
    }
}

fun plotCourseWithAim(coordinates: Coordinates, instruction: Instruction): Coordinates {
    return when (instruction.command) {
        FORWARD -> coordinates.copy(
            position = coordinates.position + instruction.units,
            depth = coordinates.depth + (coordinates.aim * instruction.units)
        )
        UP -> coordinates.copy(aim = coordinates.aim - instruction.units)
        DOWN -> coordinates.copy(aim = coordinates.aim + instruction.units)
    }
}
