input = open("./input/05-in.txt").read().split()

intervals, ingredients = [], set()
for line in input:
    if line.isdigit():
        ingredients.add(int(line))
    else:
        l, _, r = line.partition('-')
        intervals.append([int(l),int(r)])
intervals.sort()
I = [intervals[0]]
for s,e in intervals:
    if s <= I[-1][1]:
        I[-1][1] = max(I[-1][1], e)
    else: I.append([s,e])
p1 = sum(s<=i<=e for s,e in I for i in ingredients)
p2 = sum(e-s+1 for s,e  in I)
print(p1, p2)
