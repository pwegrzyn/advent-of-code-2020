INPUT_FILE = "input.txt"
TARGET = 2020


def main1():
    with open(INPUT_FILE) as f:
        lines = f.readlines()

    residuals = {int(value): TARGET - int(value) for value in lines}
    
    for val, res in residuals.items():
        if res in residuals:
            print(val * res)
            return


def main2():
    with open(INPUT_FILE) as f:
        lines = f.readlines()

    values = {int(value) for value in lines}

    for value in values:
        residual = TARGET - value
        new_values = set(values) - {value}
        for new_value in new_values:
            if residual - new_value in new_values:
                print(value * new_value * (residual - new_value))
                return


if __name__ == "__main__":
    main2()
