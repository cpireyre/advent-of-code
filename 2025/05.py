from utils import merge_intervals
xs, I = [], []
for L in open(0).read().split():
    if L.isdigit(): I.append(int(L))
    else:
        l, _, r = L.partition('-')
        xs.append([int(l), int(r)])
F = merge_intervals(xs)
print(sum(s<=i<=e for s,e in F
          for i in I),
      sum(e-s+1 for s,e in F))
