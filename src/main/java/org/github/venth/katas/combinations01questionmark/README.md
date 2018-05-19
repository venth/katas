# Kata
A string consists of ‘0’, ‘1’ and '?'. The question mark can be either '0' or '1'. Find all possible combinations for a string.

# Notes
Just after reading the description I thought: - OK, any solution I can provide nevertheless will have same results.
So, I wrote two scenarios:
- first with edge and common cases (calculates combinations for: #input with #combinator) and
- the second one to see, if my solution is able to calculate big number of question marks (#combinator calculates big number of question marks).


My initial thought about solution was the issue is very similar to to finding combinations just of 0 and 1.
If non-question marks (0 and 1) are omitted then it's just a combination or 0 and 1 replacing the question mark.
For example: '0?1?' -simplified-> '??' and then it's combinations are `00, 01, 10, 11`
