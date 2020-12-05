def sol1(lines):
    mapped_lines = map_to_seats(lines)
    return seat_key(max(mapped_lines, key=seat_key))


def sol2(lines):
    mapped_lines = map_to_seats(lines)
    mapped_seat_ids = [seat_key(k) for k in sorted(mapped_lines, key=seat_key)]
    for i in range(mapped_seat_ids[0], mapped_seat_ids[-1]):
        if i not in mapped_seat_ids:
            return i
    return -1


def seat_key(value):
    row, col = value
    return row * 8 + col


def map_to_seats(lines):
    return [calculate_seat(line.strip()) for line in lines]


def calculate_seat(line):
    def mapper_rows(sym):
        if sym == "F":
            return "0"
        elif sym == "B":
            return "1"
        raise ValueError(f"Coudn't map symbol {sym}!")

    def mapper_cols(sym):
        if sym == "R":
            return "1"
        elif sym == "L":
            return "0"
        raise ValueError(f"Coudn't map symbol {sym}!")

    return (decode_syms(line[:7], mapper_rows), decode_syms(line[7:], mapper_cols))


def decode_syms(syms, mapper):
    decoded = "".join([mapper(s) for s in syms])
    return int(decoded, 2)


if __name__ == "__main__":
    INPUT_PATH = "input.txt"

    with open(INPUT_PATH) as f:
        lines = f.readlines()

    print(sol2(lines))
