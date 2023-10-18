import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

val chicken = ArrayList<Pair<Int, Int>>()
val house = ArrayList<Pair<Int, Int>>()
var min = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val arr = Array(n, { IntArray(n) })

    for (i in 0 until n) {
        val token = br.readLine().split(" ").map { it.toInt() }
        for (j in 0 until token.size) {
            arr[i][j] = token[j]
            if (token[j] == 1) {
                house.add(Pair(i, j))
            } else if (token[j] == 2) {
                chicken.add(Pair(i, j))
            }
        }
    }

    val checkArr = ArrayList<Int>()
    solution(0, 0, m, checkArr)

    println(min)
}

private fun solution(index: Int, depth: Int, m: Int, checkArr: ArrayList<Int>) {
    if (depth == m) {

        val houseChicken = IntArray(house.size) { Int.MAX_VALUE }

        for (i in checkArr) {
            val chickendRow = chicken[i].first
            val chickenCol = chicken[i].second

            for (j in 0 until house.size) {
                val houseRow = house[j].first
                val houseCol = house[j].second
                val distance = abs(houseRow - chickendRow) + abs(houseCol - chickenCol)

                if (houseChicken[j] > distance) {
                    houseChicken[j] = distance
                }
            }
        }

        if (min > houseChicken.sum()) {
            min = houseChicken.sum()
        }
        return
    }

    for (i in index until chicken.size) {
        checkArr.add(i)
        solution(i + 1, depth + 1, m, checkArr)
        checkArr.removeAt(checkArr.size - 1)
    }
}
