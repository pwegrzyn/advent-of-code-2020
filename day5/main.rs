use std::io::{BufRead, BufReader};
use std::fs::File;
use std::convert::TryInto;

fn sol1(lines: std::io::Lines<BufReader<File>>) -> i32 {
    let row_scale_factor = 8;
    let mut highest_seat_id = -1;
    for line in lines.filter_map(|result| result.ok()) {
        let (row, col) = calculate_seat(&line);
        let seat_id = row * row_scale_factor + col;
        if seat_id > highest_seat_id {
            highest_seat_id = seat_id;
        }
    }
    highest_seat_id
}

fn calculate_seat(line: &str) -> (i32, i32) {
    (decode_row(&line[..8]), decode_col(&line[8..]))
}

fn decode_row(encoded_row: &str) -> i32 {
    let decoded_row = encoded_row
        .to_string()
        .chars()
        cloned()
        .flat_map(|c| match c {
            'F' => "0",
            'B' => "1"
        })
        .chars()
        .collect::<String>();
    isize::from_str_radix(decoded_row, 2).unwrap().try_into().unwrap()
}

fn decode_col(encoded_col: &str) -> i32 {
    let decoded_col = encoded_col
        .to_string()
        .chars()
        .cloned()
        .flat_map(|c| match c {
            'R' => "1",
            'L' => "0"
        })
        .chars()
        .collect::<String>();
    isize::from_str_radix(decoded_col, 2).unwrap().try_into().unwrap()
}

fn main() {
    const INPUT_PATH: &str = "input.txt";
    let file = File::open(INPUT_PATH).expect("Cannot open file");
    let file = BufReader::new(file);
    let result = sol1(file.lines());
    println!("{}", result);
}

// TODO: finish cuz it's not working now :/