from sys import argv
from math import inf
input = open("input/" + argv[0] + ".txt").read().split("\n")

def I(lines):
  res = 0
  for line in lines:
    sep = "\t" if "\t" in line else " "
    m, M = inf, -inf
    for x in line.split(sep):
      m, M = min(m, int(x)), max(M, int(x))
    res += M - m
  return res

def II(lines):
  res = 0
  for line in lines:
    sep = "\t" if "\t" in line else " "
    xs = list(map(int, line.split(sep)))
    for l in range(len(xs)):
      for r in range(l + 1, len(xs)):
        n, N = xs[l], xs[r]
        if n > N: n, N = N, n
        if N % n == 0: res += N // n
  return res

example  = ["5 1 9 5", "7 5 3", "2 4 6 8"]
example2 = ["5 9 2 8", "9 4 7 3", "3 8 6 5"]
print(II(example2))
print(II(input))
