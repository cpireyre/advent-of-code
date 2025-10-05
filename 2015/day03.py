import sys
import collections
input = open("input/" + sys.argv[0] + ".txt").read()

def I(input):
    x,y = 0, 0
    visited = collections.defaultdict(int)
    for c in input:
        match c:
            case "^": y += 1
            case "v": y -= 1
            case ">": x += 1
            case "<": x -= 1
        visited[(x, y)] += 1
    return len(visited)

def II(input):
    sx = sy = rx = ry = 0
    visited = collections.defaultdict(int)
    for s, r in zip(input[::2], input[1::2]):
        match s:
            case "^": sy += 1
            case "v": sy -= 1
            case ">": sx += 1
            case "<": sx -= 1
        match r:
            case "^": ry += 1
            case "v": ry -= 1
            case ">": rx += 1
            case "<": rx -= 1
        visited[(sx, sy)] += 1
        visited[(rx, ry)] += 1
    return len(visited)

print(I(input))
print(II(input))
