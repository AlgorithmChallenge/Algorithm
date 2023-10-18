let inputs = require("fs")
  .readFileSync("치킨 배달.txt")
  .toString()
  .trim()
  .split("\n");

const getDistance = (arr1, arr2) => {
  let [x1, y1] = arr1;
  let [x2, y2] = arr2;
  return Math.abs(x1 - x2) + Math.abs(y1 - y2);
};
const getCombination = (selectNum, arr) => {
  let result = [];
  if (selectNum === 1) return arr.map(d => [d]);
  arr.forEach((fixed, idx, origin) => {
    let rest = origin.slice(idx + 1);
    let combination = getCombination(selectNum - 1, rest);
    let attached = combination.map(d => [fixed, ...d]);
    result.push(...attached);
  });
  return result;
};
function solution(input) {
  let [n, m] = input[0].split(" ").map(d => parseInt(d));
  let road = [];
  let house = [];
  let chicken = [];
  for (var i = 1; i < n + 1; i++) {
    let map = input[i].split(" ").map(d => parseInt(d));
    map.forEach((data, idx) => {
      if (data === 1) {
        house.push([i - 1, idx]);
      } else if (data === 2) {
        chicken.push([i - 1, idx]);
      }
    });
  }
  let newArr = [];
  let a = 10000;
  for (var k = 1; k <= m; k++) {
    let results = getCombination(k, chicken);
    results.forEach(data => {
      let sum = 0;
      for (var z = 0; z < house.length; z++) {
        let houses = house[z];
        let min = 1000000;
        for (var j = 0; j < data.length; j++) {
          let chickens = data[j];

          let ans = getDistance(houses, chickens);
          min = Math.min(min, ans);
        }
        sum += min;
      }
      a = Math.min(a, sum);
    });
  }
  console.log(a);
}
solution(inputs);
