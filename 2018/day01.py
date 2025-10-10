from sys import argv
from itertools import cycle
input = open("input/" + argv[0] + ".txt").read().split("\n")

def freq(lines):
    f = 0
    for delta in cycle(map(int, lines)):
        yield (f := f + delta)

def I(input):
    xs = freq(input)
    for _ in range(len(input)): res = next(xs)
    return res

def II(input):
    seen = {0}
    for x in freq(input):
        if x in seen: return x
        seen.add(x)

print(I(input))
print(II(input))
