# Haskell Streams

https://www2.seas.gwu.edu/~rhyspj/cs3221/lab9/lab93.html

Create an infinite list

```
ghci> billgates = "dollar" : billgates
```

```
ghci> take 5 billgates
["dollar","dollar","dollar","dollar","dollar"]
```


```
ghci> :type (:)
(:) :: a -> [a] -> [a]
```
