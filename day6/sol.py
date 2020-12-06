from functools import reduce


def sol1(lines):
    group = set()
    counter = 0
    for line in lines:
        if line == "\n":
            counter += len(group)
            group.clear()
        else:
            group = group.union(set(line.strip()))
    else:
        counter += len(group)
    return counter


def sol2(lines):
    group = []
    counter = 0
    for line in lines:
        if line == "\n":
            counter += len(reduce(lambda a, b: a.intersection(b), group))
            group.clear()
        else:
            group.append(set(line.strip()))
    else:
        counter += len(reduce(lambda a, b: a.intersection(b), group))
    return counter


if __name__ == "__main__":
    INPUT_PATH = "input.txt"

    with open(INPUT_PATH) as f:
        lines = f.readlines()

    print(sol2(lines))
