subseq a b aList = take (b - a) (drop a aList)
-- Prelude> subseq 2 5 [1 .. 10]
-- Prelude> subseq 2 7 "a puppy"
