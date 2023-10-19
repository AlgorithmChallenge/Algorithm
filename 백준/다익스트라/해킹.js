let inputs = require("fs")
  .readFileSync("해킹.txt")
  .toString()
  .trim()
  .split("\n");

const getMin = (weight, visited) => {
  let min = Infinity;
  let idx = 0;
  for (var i = 1; i < weight.length; i++) {
    if (weight[i] < min && !visited[i]) {
      min = weight[i];
      idx = i;
    }
  }
  return [min, idx];
};

const dijkstra = (board, start, num) => {
  let weight = board[start];
  let visited = new Array(num + 1).fill(false);
  let count = 0;
  visited[start] = true;
  while (count < num) {
    let [min, idx] = getMin(weight, visited);
    let selectArr = board[idx];
    for (var i = 1; i <= num; i++) {
      if (weight[i] > min + selectArr[i] && !visited[i]) {
        weight[i] = min + selectArr[i];
      }
    }
    visited[idx] = true;
    count++;
  }
  return weight;
};
function solution(input) {
  let testCaseNum = +input[0];
  let start = 1;
  for (var i = 0; i < testCaseNum; i++) {
    let [n, d, c] = input[start].split(" ").map(d => +d);
    let board = Array.from(Array(n + 1), () => Array(n + 1).fill(Infinity));
    for (var k = start + 1; k < d + start + 1; k++) {
      let [a, b, s] = input[k].split(" ").map(d => +d);
      board[b][a] = s;
    }
    let w = dijkstra(board, c, n);
    let rest = w.filter(data => data !== Infinity);
    console.log(rest.length + 1, Math.max(...rest));
    start = k;
  }
}
solution(inputs);
