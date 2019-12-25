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

* In the `main` block replace:

```Haskell
  putStrLn "hello world"
```

  with the below code

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

:pushpin: Turn `Text` to `IO`

* Import `TIO`

```Haskell
import qualified Data.Text.IO                  as TIO
```

* Replace the `print (getRate json)` instruction with the below code


```Haskell
  case getRate json of
    Nothing   -> TIO.putStrLn "Could not find the Bitcoin rate :("
    Just rate -> TIO.putStrLn $ "The current Bitcoin rate is " <> rate <> " $"
```

:bookmark: Final Result

```Haskell
{-# LANGUAGE OverloadedStrings #-}

module Main where

import qualified Data.Text.IO                  as TIO
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
  case getRate json of
    Nothing   -> TIO.putStrLn "Could not find the Bitcoin rate :("
    Just rate -> TIO.putStrLn $ "The current Bitcoin rate is " <> rate <> " $"
```

# :m: Test

* start with stack to get into REPL

```
$ stack exec -- ghci
```

* Load the Main program and run it

```Haskell
ghci> :load src/Main
```

* fill the `json` variable by running `fetchJSON` function 

```Haskell
ghci> json <- fetchJSON
```

* Result

```
ghci> json
"{\"time\":{\"updated\":\"Dec 25, 2019 22:32:00 UTC\",\"updatedISO\":\"2019-12-25T22:32:00+00:00\",\"updateduk\":\"Dec 25, 2019 at 22:32 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"7,197.9167\",\"description\":\"United States Dollar\",\"rate_float\":7197.9167},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"5,555.7624\",\"description\":\"British Pound Sterling\",\"rate_float\":5555.7624},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"6,445.5976\",\"description\":\"Euro\",\"rate_float\":6445.5976}}}"
```

* :warning: Allow `Text` to be parsed instead of `[Char]`


```
<interactive>:5:14: error:
    • Couldn't match expected type ‘Text’ with actual type ‘[Char]’
```

* Extend the language

```
ghci> :set -XOverloadedStrings
```



## Reference:

https://stackoverflow.com/questions/37894987/couldnt-match-expected-type-text-with-actual-type-char
