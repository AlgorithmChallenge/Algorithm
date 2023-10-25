class Solution {
fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
    var answer = 0


    val arr = Array(N + 1, { IntArray(N + 1) { 500001} })

    road.forEach {
        val start = it[0]
        val end = it[1]
        val weight = it[2]

        arr[start][end] = minOf(arr[start][end], weight)
        arr[end][start] = arr[start][end]

    }
    
    for (i in 1..N) {
        arr[i][i] = 0
    }

    for (z in 1..N) {
        for (i in 1..N) {
            for (j in 1..N) {
                if (i != z && j != z) {
                    arr[i][j] = minOf(arr[i][z] + arr[z][j], arr[i][j])
                }
            }
        }
    }
    
    for(i in 1 .. N){
        if(arr[1][i] <=k){
            answer++
        }
    }

    return answer
}
}
