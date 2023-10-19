import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max


lateinit var arr: Array<ArrayList<Node>>
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val tc = br.readLine().toInt()

    repeat(tc) {
        val (n, d, c) = br.readLine().split(" ").map { it.toInt() }
        arr = Array(n + 1) { ArrayList() }
        repeat(d) {
            val (a, b, s) = br.readLine().split(" ").map { it.toInt() }
            arr[b].add(Node(a, s))
        }

        val infection = solution(c)
        var count = 0
        var maxTime = Int.MIN_VALUE
        
        infection.forEach {
            if (it != Int.MAX_VALUE) {
                count++
                maxTime = max(maxTime, it)
            }
        }
        println("${count} ${maxTime}")
    }
}

private fun solution(start: Int): IntArray {
    val distance = IntArray(arr.size) { Int.MAX_VALUE }
    val queue = PriorityQueue<Node>()
    queue.add(Node(start, 0))
    distance[start] = 0

    while (!queue.isEmpty()) {
        val cur = queue.poll()
        for (next in arr[cur.num]) {
            val newTime = cur.time + next.time

            if (newTime < distance[next.num]) {
                distance[next.num] = newTime
                queue.add(Node(next.num, newTime))
            }
        }
    }
    return distance
}

data class Node(val num: Int, val time: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return time - other.time
    }

}
