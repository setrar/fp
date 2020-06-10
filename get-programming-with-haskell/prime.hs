-- take 100 primes 

primes = filterPrime [2..]
   where filterPrime (x:xs) =
          x : filterPrime [y | y <- xs, y `mod` x /= 0]
