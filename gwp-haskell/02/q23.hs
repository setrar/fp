modCalc n = if modulo 
            then n - 2
            else (3 * n) + 1
   where modulo = mod n 2 == 0
