const INPUT_PATH = "input.txt";

function sol1(data: string): number {
  const rightStepSize = 3;
  const treeSymbol = "#";
  const lines = data.split("\n");
  let treeCounter = 0;
  for (
    let i = 1, j = rightStepSize; i < lines.length; i++, j += rightStepSize
  ) {
    const currentLine = lines[i];
    if (j >= currentLine.length) {
      j -= currentLine.length;
    }
    if (currentLine[j] == treeSymbol) {
      treeCounter++;
    }
  }
  return treeCounter;
}

function sol2(data: string): number {
  const treeSymbol = "#";
  const lines = data.split("\n");
  let res = 1;

  // first we handle cases when the step down is 1
  const rightStepSizes = [1, 3, 5, 7];
  for (const rightStepSize of rightStepSizes) {
    let treeCounter = 0;
    for (
      let i = 1, j = rightStepSize; i < lines.length; i++, j += rightStepSize
    ) {
      const currentLine = lines[i];
      if (j >= currentLine.length) {
        j -= currentLine.length;
      }
      if (currentLine[j] == treeSymbol) {
        treeCounter++;
      }
    }
    res *= treeCounter;
  }

  // now we handle the case where the step down != 1 cuz I'm lazy
  let treeCounter = 0;
  for (let i = 2, j = 1; i < lines.length; i += 2, j++) {
    const currentLine = lines[i];
    if (j >= currentLine.length) {
      j -= currentLine.length;
    }
    if (currentLine[j] == treeSymbol) {
      treeCounter++;
    }
  }
  res *= treeCounter;

  return res;
}

Deno.readTextFile(INPUT_PATH)
  .then((data) => console.log(sol2(data)));
