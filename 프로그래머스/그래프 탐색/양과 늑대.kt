import kotlin.math.max

class Solution {
    private lateinit var arr:Array<ArrayList<Int>>
    private var answer = 0
    fun solution(info: IntArray, edges: Array<IntArray>): Int {

        var sheep = 0
        var wolf = 0

        arr = Array(info.size){ArrayList<Int>()}
        for(edge in edges){
            arr[edge[0]].add(edge[1])
        }
        val checkList = ArrayList<Int>()
        checkList.add(0)
        dfs(info,checkList,0,0,0)
        return answer
    }

    private fun dfs(info:IntArray, checkList:ArrayList<Int>, node:Int, sheep:Int, wolf:Int){
        var sheepCount = sheep
        var wolfCount = wolf
        
        if(info[node] == 0){
            sheepCount++
        }else{
            wolfCount++
        }

        if(sheepCount <= wolfCount){
            return
        }

        answer = max(answer,sheepCount)
        
        val newCheckList = ArrayList<Int>(checkList)
        if(!arr.get(node).isEmpty()){
            newCheckList.addAll(arr.get(node))
        }
        newCheckList.remove(node)
        for(next in newCheckList){
            dfs(info,newCheckList,next, sheepCount, wolfCount)
        }
    }
}
