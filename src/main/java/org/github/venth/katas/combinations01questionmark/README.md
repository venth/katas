# Kata
A string consists of ‘0’, ‘1’ and '?'. The question mark can be either '0' or '1'. Find all possible combinations for a string.

# Notes
Just after reading the description I thought: - OK, any solution I can provide nevertheless will have same results.
So, I wrote two scenarios:
- first with edge and common cases (calculates combinations for: #input with #combinator) and
- the second one to see, if my solution is able to calculate big number of question marks (#combinator calculates big number of question marks).

## First attempt - Combinations01Questionmark_recursion

My initial thought about solution was the issue is very similar to to finding combinations just of 0 and 1.
If non-question marks (0 and 1) are omitted then it's just a combination or 0 and 1 replacing the question mark.
For example: '0?1?' -simplified-> '??' and then it's combinations are `00, 01, 10, 11`

Then I thought to split iteratively the given string by question mark and for the question mark return two streams comprising two
elements: `staticPart` + 0 and `staticPart` + 1 and combine recursively calculated remaining part.

First scenario passed. Second... failed because of the stack overflow.
Obviously I prepared solution that first keeps all in memory, second - can be improved.

THe complexity of this solution seems to be: O(n*2^n)

## Second attempt - Combinations01Questionmark_iteration

For the second attempt I've decided to change recursion for an iteration.

First scenario passed. Second... failed because of the stack overflow.
The solution still isn't elegant and fails for big number of question marks.

THe complexity of this solution seems to be as well: O(n*2^n)

## Third attempt - Combinations01Questionmark_stream

For the third attempt I decided to use streams along with the iterator through string parts separated by question marks.

The solution looked better and first scenario worked. Second scenario failed with stack overflow.

Even on the matter of complexity I didn't improved.

It seems that I have to change the approach radically.

## Fourth attempt - Combinations01Questionmark_treeTraverse

The idea is based on observations as follows:
- each combination is represented as path to the tree leaf
                            *
                           / \
                          0   1
                         / \ / \
                        0  1 0  1
- there is no need to build the tree, I have to just to iterate through paths from
  a leaf to the root, so results for each iteration step could look like:
  - 00
                            *
                           /
                          0
                         /
                        0

  - 10
                            *
                           /
                          0
                           \
                            1
  - 01
                            *
                             \
                              1
                             /
                            0   
  - 11
                            *
                             \
                              1
                               \
                                1
  Note that traversing happens from a leaf to the root
- I can simplify text: `00101?0001111??` to `???` and for each calculated combination of `???`
  replace iteratively question marks with a single combination result

