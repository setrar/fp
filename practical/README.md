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

* Add an Haskell Language Extension `OverloadedStrings` at the top of `Main.hs` source code 

```Haskell
{-# LANGUAGE OverloadedStrings #-}
```

:pushpin: After the `module Main where` declaration

* Import some packages:

```Haskell
import           Network.HTTP.Simple            ( httpBS, getResponseBody )               
import qualified Data.ByteString.Char8         as BS
```

* Add the `fetchJSON` function declaration to the `Main.hs` source code 

```Haskell
fetchJSON :: IO BS.ByteString
fetchJSON = do
  res <- httpBS "https://api.coindesk.com/v1/bpi/currentprice.json"
  return (getResponseBody res)
```

* Replace the `putStrLn "hello world"` with the below code

```Haskell
  json <- fetchJSON
  BS.putStrLn json
```

:bookmark: Final Result

```Haskell
{-# LANGUAGE OverloadedStrings #-}

module Main where

import           Network.HTTP.Simple            ( httpBS, getResponseBody )
import qualified Data.ByteString.Char8         as BS

fetchJSON :: IO BS.ByteString
fetchJSON = do
  res <- httpBS "https://api.coindesk.com/v1/bpi/currentprice.json"
  return (getResponseBody res)

main :: IO ()
main = do
  json <- fetchJSON
  BS.putStrLn json
```

### Using `Lenses`

:pushpin: After the `module Main where` declaration

* Import some packages:

```Haskell
import           Control.Lens                   ( preview )
import           Data.Aeson.Lens                ( key, _String )
import           Data.Text                      ( Text )
```

* Add the `getRate` function declaration to the `Main.hs` source code 

```Haskell
getRate :: BS.ByteString -> Maybe Text
getRate = preview (key "bpi" . key "USD" . key "rate" . _String)
```

* Replace the `BS.putStrLn json` instruction with the below code

```Haskell
    print (getRate json)
```



:bookmark: Final Result

```Haskell
{-# LANGUAGE OverloadedStrings #-}

module Main where

import           Control.Lens                   ( preview )
import           Data.Aeson.Lens                ( key, _String )
import           Data.Text                      ( Text )
import           Network.HTTP.Simple            ( httpBS, getResponseBody )
import qualified Data.ByteString.Char8         as BS

fetchJSON :: IO BS.ByteString
fetchJSON = do
  res <- httpBS "https://api.coindesk.com/v1/bpi/currentprice.json"
  return (getResponseBody res)

getRate :: BS.ByteString -> Maybe Text
getRate = preview (key "bpi" . key "USD" . key "rate" . _String)

main :: IO ()
main = do
  json <- fetchJSON
  print (getRate json)
```
