
## Without using `cabal`

```
$ cabal install optparse-applicative --lib
```

```
$ cabal v1-install random
```

```
$ ghc Main.hs -package random
```

## Play 

```
$ ./Main User Minimax
```

or  using `cabal`

```
$ cabal run noughts User Random
```


```
Pick from 1 to 9 (left to right, top to bottom)
. . . 
. . . 
. . . 

Make your move!
```

