# Day 2

https://adventofcode.com/2022/day/2

This day was primarily about interpreting an instruction file correctly and then applying
the instructions to the game Rock / Paper / Scissors.

## Full Version

I encoded the three game states in an enum class and the rules about what wins and looses to what in
a couple of helper functions.

For part 2 I decided to build a separate implementation that uses part 1 to calculate the scores.
The reason for this choice was to allow part 1 and part 2 to exist together. If this was a real life
example I would have refactored part 1 into part 2 instead.

Note that again my implementation uses a sequence to read the file so that the transformation of
lines into scores and doing the summation doesn't require loading the whole file contents into a data
structure and then iterating it, instead doing the conversions and calculations on each line as it is read.

## Minimal Version

The minimal version uses the same algorithm as the full version. It avoids all the conversion to types
my just running match statements on the characters in the file and converting them directly into scores.
