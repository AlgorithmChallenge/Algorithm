import java.util.*

class Solution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = intArrayOf()
        val queue: Queue<Int> = LinkedList()

        for (i in 0 until progresses.size) {
            var day = 0
            while (true) {
                if (progresses[i] >= 100) {
                    break
                }
                progresses[i] += speeds[i]
                day++
            }
            queue.add(day)
        }

        val arr = ArrayList<Int>()

        var count = 0
        var curMax = 0
        while (!queue.isEmpty()) {
            val cur = queue.poll()
            count++

            if (curMax < cur) {
                curMax = cur
            }

            if (queue.isEmpty()) {
                arr.add(count)
            } else if (curMax < queue.peek()) {
                arr.add(count)
                count = 0
                curMax = queue.peek()
            }
        }

        answer = arr.toIntArray()

        return answer
    }
}
