# Bitcoin Converter

https://functional.christmas/2019/23


```
$ stack new bitcoin simple-hpack && cd bitcoin
```

```
$ stack build
```

```
$ stack exec bitcoin
``` 

:pushpin: Add packages to `package.yaml` dependencies

 * add lens - lens-aeson - bytestring - text - http-conduit

```
dependencies:
  - base >= 4.7 && < 5
  - lens
  - lens-aeson
  - bytestring
  - text
  - http-conduit
```

* Build 

```
stack build --exec bitcoin --file-watch --fast
```

* Add an Haskell Language Extension `OverloadedStrings` in `Main.hs` source code

```Haskell
{-# LANGUAGE OverloadedStrings #-}
```

* Add the fetchJSON function to the `Main.hs` source code

```Haskell
fetchJSON :: IO BS.ByteString
fetchJSON = do
  res <- httpBS "https://api.coindesk.com/v1/bpi/currentprice.json"
  return (getResponseBody res)
```

