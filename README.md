# Advent of Code 2022

Welcome to my solutions for the 2022 Advent of Code. This is a yearly event where each day
requires writing some code to solve a particular problem and then submitting the answer.
Full details can be found at: https://adventofcode.com/2022

I've developed these solutions in kotlin and will be using a TDD approach for each.

## Day 1 (https://adventofcode.com/2022/day/1)

This day was primarily a problem around processing sets of numbers from a file, summing them up
and selecting maximum values.

My initial approach started out by loading all the contents into memory (as a List of Lists of Long)
and then using map and sort functions to obtain the output. However after the first iteration I
realised this was unnecessary as it was possible to track the required state on the fly as each
line in the file is processed.

The final solution therefore uses a fold operation over the lines and just accumulates the current
elf's running total and the max values encountered. Advantages of this approach:

* Only a single iteration over the input list to produce a result
* No need to hold the entire input list contents in memory
* No need to do any summation or sorting of data

Addition: also added a minimalist implementation that uses a single iteration with a chain of functions
over a sequence of lines. Not quite as efficient as it builds a list of the total for each elf and then
sorts it, but minimal in terms of the code needed.
