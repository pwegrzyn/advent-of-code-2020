package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

const inputPath = "input.txt"

func sol1(lines []string) int {
	validPasswordsCount := 0
	for _, line := range lines {
		splitByColon := strings.Split(line, ":")
		splitBySpace := strings.Split(splitByColon[0], " ")
		specialChar := splitBySpace[1]
		splitByDash := strings.Split(splitBySpace[0], "-")
		minOccurrence, errMinOcc := strconv.Atoi(splitByDash[0])
		maxOccurrence, errMaxOcc := strconv.Atoi(splitByDash[1])
		if errMinOcc != nil || errMaxOcc != nil {
			log.Fatal("Wrong number format!")
		}
		password := splitByColon[1][1:len(splitByColon[1])]

		occurrenceCount := 0
		for _, char := range password {
			if specialChar == fmt.Sprintf("%c", char) {
				occurrenceCount++
			}
		}

		if occurrenceCount >= minOccurrence && occurrenceCount <= maxOccurrence {
			validPasswordsCount++
		}
	}
	return validPasswordsCount
}

func sol2(lines []string) int {
	validPasswordsCount := 0
	for _, line := range lines {
		splitByColon := strings.Split(line, ":")
		splitBySpace := strings.Split(splitByColon[0], " ")
		specialChar := splitBySpace[1]
		splitByDash := strings.Split(splitBySpace[0], "-")
		firstOccurence, errMinOcc := strconv.Atoi(splitByDash[0])
		secondOccurence, errMaxOcc := strconv.Atoi(splitByDash[1])
		if errMinOcc != nil || errMaxOcc != nil {
			log.Fatal("Wrong number format!")
		}
		password := splitByColon[1][1:len(splitByColon[1])]

		seenChar := false
		for idx, char := range password {
			if specialChar == fmt.Sprintf("%c", char) {
				if idx + 1 == firstOccurence || idx + 1 == secondOccurence {
					if seenChar {
						// if we have already seen the special char then this password is invalid
						seenChar = false
						break
					}
					seenChar = true
				}
			}
		}
		if seenChar {
			validPasswordsCount++
		}
	}
	return validPasswordsCount
}

func main() {
	file, err := os.Open(inputPath)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	lines := []string{}
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	fmt.Println(sol2(lines))
}
