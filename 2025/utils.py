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
