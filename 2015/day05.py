from sys import argv
from collections import defaultdict
input = open("input/" + argv[0] + ".txt").read().split("\n")

def nice(xs):
    vowels = set("aeiou")
    bad = set([('a', 'b'), ('c', 'd'), ('p', 'q'), ('x', 'y')])
    prev, V, D = None, 0, False
    for c in xs:
        if (prev, c) in bad: return False
        V += c in vowels
        if prev == c: D = True
        prev = c
    return V >= 3 and D

def nice2(xs):
    seen = defaultdict(int)
    dupe = False
    for i, (p, q) in enumerate(zip(xs[::1], xs[1::1])):
        if (p, q) in seen and seen[(p, q)] <= i - 2:
            print((p,q))
            dupe = True
            break
        if (p, q) not in seen: seen[(p, q)] = i
    triple = False
    for p, q, r in zip(xs[::1], xs[1::1], xs[2::1]):
        if p == r:
            # print((p,q,r))
            triple = True
            break
    return dupe and triple

# Part I:
# print(sum(nice(xs) for xs in input))
example = ["qjhvhtzxzqqjkmpb", "xxyxx", "uurcxstgmygtbstg",
           "ieodomkazucvgmuy", "aaa"]
# example = ["aaaa"]
for xs in example:
    print(xs + ": " + str(nice2(xs)))
print(sum(nice2(xs) for xs in input))
