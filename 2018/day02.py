from sys import argv
input = open("input/" + argv[0] + ".txt").read().split("\n")

def I(lines):
    al = lambda c: ord(c) - ord('a')
    twos = threes = 0
    for xs in lines:
        count = [0] * 26
        for c in xs: count[al(c)] += 1
        twos   += any(c == 2 for c in count)
        threes += any(c == 3 for c in count)
    return twos * threes


print(I(input))
