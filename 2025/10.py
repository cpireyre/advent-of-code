from collections import deque
data = open("./input/10-in.txt").read().splitlines()

def toggle(light, button):
    res = light[:]
    for i in button: res[i] ^= 1
    return res

p1 = 0
for line in data:
    diagram, *buttons, joltage = line.split()
    buttons = list(map(eval, buttons))
    diagram = tuple(map(lambda c: int(c == '#'), diagram[1:-1]))
    light = [0] * len(diagram)
    Q = deque([(light, 0)])
    while True:
        l, steps = Q.popleft()
        if tuple(l) == diagram:
            p1 += steps
            break
        for button in buttons:
            if type(button) == int: button = tuple((button,)) # lol
            Q.append((toggle(l, button), steps + 1))
print(p1)
