from collections import defaultdict
input = open("./input/05-in.txt").read().split()

intervals, ingredients = [], set()
for line in input:
    if line.isdigit():
        ingredients.add(int(line))
    else:
        l, _, r = line.partition('-')
        intervals.append([int(l),int(r)])
seen = set()
p1 = 0
for i in ingredients:
    for s,e in intervals:
        if s <= i <= e and i not in seen:
            p1 += 1
            seen.add(i)
def merge(intervals):
        mp = defaultdict(int)
        for start, end in intervals:
            mp[start] += 1
            mp[end] -= 1

        res = []
        interval = []
        have = 0
        for i in sorted(mp):
            if not interval:
                interval.append(i)
            have += mp[i]
            if have == 0:
                interval.append(i)
                res.append(interval)
                interval = []
        return res
intervals = merge(intervals)
p2 = 0
for s,e in intervals: p2 += e - s + 1
print(p1, p2)
