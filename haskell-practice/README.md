# TicTacToe

## Without using `cabal`

https://gist.github.com/adwhit/db18f3fbfdb61bc56357bce25aa2d19d

```
$ cabal install optparse-applicative --lib
```

```
$ cabal v1-install random
```

```
$ ghc Main.hs -package random
```

- [x] Run

```
$ ./Main User Minimax
```

## Using `cabal`

```
$ cabal configure
```

```
$ cabal build
```

- [x] Run

```
$ cabal run noughts User Random
```

## Playing

```
Pick from 1 to 9 (left to right, top to bottom)
. . . 
. . . 
. . . 

Make your move!
```

