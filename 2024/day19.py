from sys import argv
# designs = ["r", "wr", "b", "g", "bwu", "rb", "gb", "br"]
def comp(xs, pattern):
    return xs == pattern[:len(xs)]

def compose(pattern, designs):
    res = False
    def backtrack(xs):
        nonlocal res
        if res: return
        if xs == pattern:
            res = True
            print(xs)
            return
        for design in designs:
            if comp(xs + design, pattern):
                backtrack(xs + design)
    backtrack("")
    return res

def compose_slow(pattern, designs):
    res = 0
    def backtrack(xs):
        if xs == pattern:
            nonlocal res; res += 1
            return
        for design in designs:
            if comp(xs + design, pattern):
                backtrack(xs + design)
    backtrack("")
    return res
# print(compose("brwrr"))
# examples = [
#         "brwrr",
#         "bggr",
#         "gbbr",
#         "rrbgbr",
#         "ubwu",
#         "bwurrg",
#         "brgr",
#         "bbrgwb"
#         ]
# print(sum(compose(x) > 0 for x in examples))

lines = open("input/" + argv[0] + ".txt").read().split("\n")
designs = lines[0].split(", ")
patterns = lines[2:]

# print(sum(compose(pattern, designs) > 0 for pattern in patterns))
print(sum(compose_slow(pattern, designs) for pattern in patterns))
