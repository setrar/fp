-- Simple function composition

f x = x + 1

g y = y - 1

fog x = (f . g)(x)
