# Day 3

https://adventofcode.com/2022/day/3

This day has two parts that initially appear different:

- Find an item type that is common in two sub-lists
- Find an item type that is common in three lists

## Full Version

My full implementation is actually pretty minimal in terms of code. The main line count comes from defining
an enum to represent each possible item its token character and priority value. The rest of the code is pretty
much simple functions to parse, partition, search and them sum the priority of the items.

## Minimal Version

The minimal version makes two big changes:

Firstly it removes the enum and just uses ascii character positions to the characters to determine the priority.
This significantly simplifies the code as in most cases we are just working with strings.

The second change is the realisation that both parts are in fact exactly the same in what they need to do.
In both cases we are taking N strings and finding the character that is common to them all. The only real
difference between the parts is that it part 1 we are splitting a single string in two to do this, while in
part 2 we are using three separate strings. Apart from the logic to source the strings, all the other code is
shared between the two parts.

This allows for some pretty neat optimisation of the code where we end up with common processing function that
is passed just the selector to use that creates the input lists.