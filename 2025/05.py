xs, I = [], []
for L in open(0).read().split():
    if L.isdigit(): I.append(int(L))
    else:
        l, _, r = L.partition('-')
        xs.append([int(l), int(r)])
xs.sort(); F = [xs[0]]
for s,e in xs:
    if s <= F[-1][1]:
        F[-1][1] = max(F[-1][1], e)
    else: F.append([s,e])
print(sum(s<=i<=e for s,e in F for i in I),
      sum(e-s+1 for s,e in F))
