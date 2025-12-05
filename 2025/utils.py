def extract(mat, pred):
    return {(r, c)
            for r in range(len(mat))
            for c in range(len(mat[0]))
            if pred(r, c)}

def mooren(r,c):
    return {(r+i, c+j)
            for i in (-1,0,1)
            for j in (-1,0,1)
            if i|j}

def merge_intervals(xs):
    xs.sort(); F = [xs[0]]
    for s,e in xs:
        if s <= F[-1][1]:
            F[-1][1] = max(F[-1][1], e)
        else: F.append([s,e])
    return F
